package com.transfer.controller;

import com.google.common.collect.Maps;
import com.transfer.service.CreateZipService;
import com.transfer.service.DownLoadZipService;
import com.transfer.service.TransferToCService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @author tian.gao
 **/

@Controller
@RequestMapping("/show")
public class UploadFileController {

    private static final String BASE_PATH = "/Users/sortinn/";
    private static final String DES_DIR_PATH = "/Users/sortinn/tranfer-result";
    public static final String DOWNLOAD_PATH = "/Users/sortinn/Download";
    private static Logger LOGGER = LoggerFactory.getLogger(UploadFileController.class);


    @Resource
    private TransferToCService transferToCService;
    @Resource
    private CreateZipService createZipService;
    @Resource
    private DownLoadZipService downLoadZipService;

    @RequestMapping("/index")
    public String showIndex() {
        return "file";
    }

    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public String uploadAndTransfer(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty() && FilenameUtils.getExtension(file.getOriginalFilename()).equals("xml")) {
            String acceptedFilename = System.nanoTime() + file.getOriginalFilename();
            String filePath = BASE_PATH + acceptedFilename;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(BASE_PATH, acceptedFilename));
            transferToCService.transer(filePath, DES_DIR_PATH);
            Map<String, Object> data = Maps.newHashMap();
            data.put("name", file.getOriginalFilename());
            data.put("filePath", filePath);
            LOGGER.info("data : {}, ", data);
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam("filename") String filename) throws IOException {

        createZipService.createZip(DES_DIR_PATH, BASE_PATH + filename + ".zip");
        File file = new File(BASE_PATH + filename + ".zip");
        LOGGER.info("file name:{}", file.getAbsolutePath());
        downLoadZipService.downLoad(file, response);
    }
}
