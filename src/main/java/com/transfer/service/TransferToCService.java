package com.transfer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * @author tian.gao
 **/
@Service
public class TransferToCService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferToCService.class);
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String ABSOLUTE_FILE_PATH = FILE_SEPARATOR + "Users" + FILE_SEPARATOR + "sortinn" + FILE_SEPARATOR;
    private static final String RETURN_0 = "return 0";
    private static final String RETURN_1 = "return 1";
    private static final String NEXT = "\n\t";
    private static final String H = ".h";
    private static final String C = ".c";
    private String xmlFilePath;
    private String jsonString;
    private JSONObject aadlPublic;
    private List<File> fileList = Lists.newArrayList();


    public void transer(String xmlFilePath, String desDirPath) {
        setFiles(xmlFilePath);
        try {
            createZipFile(desDirPath);
        } catch (IOException e) {
            LOGGER.error("createZipFile error, xmlFilePath:{}, desDirPath:{}", xmlFilePath, desDirPath);
        }
    }

    private void setFiles(String xmlFilePath) {
        if ("".equals(xmlFilePath)) {
            return;
        }
        this.xmlFilePath = xmlFilePath;
        setJsonString(xmlFilePath);
        aadlPublic = getAadlPublic();
    }

    private void createZipFile(String desDirPath) throws IOException {
        File desDir = new File(desDirPath);
        if (!desDir.isDirectory()) {
            desDir.mkdir();
        }
        createCompleteHeaderFile(desDirPath);
        createCompleteCFile(desDirPath);
        createControlHeaderFile(desDirPath);
        createControlCFile(desDirPath);
        createThreadReadDataHeaderFile(desDirPath);
        createThreadReadDataCFile(desDirPath);
        createThreadControlLawsHeaderFile(desDirPath);
        createThreadControlLawsCFile(desDirPath);

        FileUtils.copyToDirectory(fileList, desDir);

    }

    private void setJsonString(String filePath) {
        jsonString = parseXmlToJson(filePath);
    }

    private static String parseXmlToJson(String xmlFilePath) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(xmlFilePath)));
            BufferedReader br = new BufferedReader(reader);
            String readLine;
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
        } catch (IOException e) {
            System.out.println("read file error");
        }
        org.json.JSONObject jsonObject = XML.toJSONObject(sb.toString());
        return jsonObject.toString();
    }

    private File createCompleteHeaderFile(String desDirPath) {
        String completeHeaderContent = "#include<stdio.h>\n#include<stdlib.h>\n\nint fun_control();";
        if (null == aadlPublic) {
            return null;
        }
        String systemTypeName = getSystemName(aadlPublic);
        File completeHeader = createFileAndWrite(systemTypeName, completeHeaderContent, H, desDirPath);
        fileList.add(completeHeader);
        return completeHeader;
    }


    private File createCompleteCFile(String desDirPath) {
        if (null == aadlPublic) {
            return null;
        }
        String processSubcomponentName = aadlPublic.getObject("systemImpl", JSONObject.class).getObject("subcomponents", JSONObject.class).getObject("processSubcomponent", JSONObject.class).getString("name");
        String completeCContent = "#include<Complete.h>\n\nint main(int argc, char const *argv[]){" + NEXT + "process();\n\t" + RETURN_0 + ";\n}\n\n" + "int process(){" + NEXT + "fun_control();\n\t" + RETURN_0 + ";\n}\n\nint control(){" + NEXT + "fun_thread();\n}";
        File completeC = createFileAndWrite(getSystemName(aadlPublic), completeCContent, C, desDirPath);
        fileList.add(completeC);
        return completeC;
    }

    private File createControlHeaderFile(String desDirPath) {
        String controlHeaderContent = "#include<Complete.h>\n\nint fun_control();";
        if (null == aadlPublic) {
            return null;
        }
        String processTypeName = getProcessTypeName();
        File controlHeader = createFileAndWrite(processTypeName, controlHeaderContent, H, desDirPath);
        fileList.add(controlHeader);
        return controlHeader;

    }

    private File createControlCFile(String desDirPath) {
        JSONArray threadSubcomponentArray = aadlPublic.getObject("processImpl", JSONObject.class).getObject("subcomponents", JSONObject.class).getJSONArray("threadSubcomponent");
        JSONObject scaleSpeedData = (JSONObject) threadSubcomponentArray.get(0);
        if (scaleSpeedData != null) {
            String scaleSpeedDataName = scaleSpeedData.getString("name");
        }
        JSONObject speedControlLaws = (JSONObject) threadSubcomponentArray.get(1);
        String speedControlLawsName = speedControlLaws.getString("name");
        String controlCContent = "#include<control.h>\n\nint fun_control(){\n\tread_data();" + NEXT + "control_laws();" + NEXT + RETURN_1 + ";\n}";
        File controlC = createFileAndWrite(getProcessTypeName(), controlCContent, C, desDirPath);
        fileList.add(controlC);
        return controlC;
    }

    private File createThreadReadDataHeaderFile(String desDirPath) {
        if (aadlPublic == null) {
            return null;
        }
        String threadReadDataName = getThreadReadDataName();
        String threadReadDataHeaderContent = "#include<control.h>\n\nint " + threadReadDataName + "();";
        File threadReadDataH = createFileAndWrite(threadReadDataName, threadReadDataHeaderContent, H, desDirPath);
        fileList.add(threadReadDataH);
        return threadReadDataH;
    }

    private File createThreadControlLawsHeaderFile(String desDirPath) {
        if (aadlPublic == null) {
            return null;
        }
        String threadControlLawsName = getThreadControlLawsName();
        String threadReadControlLawsContent = "#include<control.h>\n\nint " + threadControlLawsName + "();";
        File threadControlLawsH = createFileAndWrite(threadControlLawsName, threadReadControlLawsContent, H, desDirPath);
        fileList.add(threadControlLawsH);
        return threadControlLawsH;
    }

    private File createThreadReadDataCFile(String desDirPath) {
        if (aadlPublic == null) {
            return null;
        }
        String threadReadDataName = getThreadReadDataName();
        String content = "#include<" + threadReadDataName + ".h>\n\nint " + threadReadDataName + "(){" + NEXT + "fun_" + threadReadDataName + "();" + NEXT + RETURN_1 + ";\n}";
        File threadReadDataC = createFileAndWrite(threadReadDataName, content, C, desDirPath);
        fileList.add(threadReadDataC);
        return threadReadDataC;
    }

    private File createThreadControlLawsCFile(String desDirPath) {
        if (aadlPublic == null) {
            return null;
        }
        String threadControlLawsName = getThreadControlLawsName();
        String content = "#include<" + threadControlLawsName + ".h>\n\nint " + threadControlLawsName + "(){" + NEXT + "fun_" + threadControlLawsName + "();" + NEXT + RETURN_1 + ";\n}";
        File threadControlLawsC = createFileAndWrite(threadControlLawsName, content, C, desDirPath);
        fileList.add(threadControlLawsC);
        return threadControlLawsC;
    }


    private String getThreadReadDataName() {
        JSONArray threadType = aadlPublic.getJSONArray("threadType");
        JSONObject threadReadData = (JSONObject) threadType.get(0);
        return threadReadData.getString("name");
    }

    private String getProcessTypeName() {
        return aadlPublic.getObject("processType", JSONObject.class).getString("name");
    }

    private String getThreadControlLawsName() {
        JSONArray threadType = aadlPublic.getJSONArray("threadType");
        JSONObject threadControlLaws = (JSONObject) threadType.get(1);
        return threadControlLaws.getString("name");
    }

    private String getSystemName(JSONObject aadlPublic) {
        return aadlPublic.getObject("systemType", JSONObject.class).getString("name");
    }

    private JSONObject getAadlPublic() {
        if ("".equals(jsonString)) {
            return null;
        }
        final JSONObject jsonObject = JSONObject.parseObject(jsonString);
        final JSONObject core = jsonObject.getObject("core:AadlSpec", JSONObject.class);
        final JSONObject aadlPackage = core.getObject("aadlPackage", JSONObject.class);
        if (aadlPackage != null) {
            return aadlPackage.getObject("aadlPublic", JSONObject.class);
        }
        return null;
    }

    private File createFileAndWrite(String TypeName, String content, String fileType, String desDirPath) {
        try {
            File file = new File(desDirPath, TypeName + fileType);
            PrintWriter printWriter = new PrintWriter(file, "UTF-8");
            printWriter.append(content);
            printWriter.close();
            return file;
        } catch (FileNotFoundException e) {
            System.out.println("file not found.");
        } catch (UnsupportedEncodingException e) {
            System.out.println("unsupported encoding.");
        }
        return null;
    }
}