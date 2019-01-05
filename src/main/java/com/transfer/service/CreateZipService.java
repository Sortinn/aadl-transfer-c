package com.transfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author tian.gao
 **/

@Service
public class CreateZipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateZipService.class);

    public void createZip(String inputFileName, String zipFileName) {
        try {
            createZip(zipFileName, new File(inputFileName));
        } catch (FileNotFoundException e) {
            LOGGER.error("file not found.");
        } catch (IOException e) {
            LOGGER.error("IO error.");
        }
    }

    private static void createZip(String zipFileName, File inputFile) throws IOException {
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
        } catch (FileNotFoundException e) {
            LOGGER.error("create zip file not found : inputFile : {}", inputFile);
        }
        createZip(zipOutputStream, inputFile, "");
        try {
            zipOutputStream.close();
            LOGGER.info("create zip file");
        } catch (IOException e) {
            LOGGER.error("I/O error has occurred. close error.");
        }
    }

    private static void createZip(ZipOutputStream zipOutputStream, File inputFile, String path) throws IOException {
        if (inputFile.isDirectory()) {
            File[] fileList = inputFile.listFiles();
            try {
                zipOutputStream.putNextEntry(new ZipEntry(path + "/"));
            } catch (IOException e) {
                LOGGER.error("is directory zip put next entry error.");
            }
            path = path.length() == 0 ? "" : path + "/";
            for (int i = 0; i < fileList.length; i++) {
                createZip(zipOutputStream, fileList[i], path + fileList[i].getName());
                LOGGER.info("fileList, file[{}]:{}", i, fileList[i]);
            }
        } else {
            try {
                zipOutputStream.putNextEntry(new ZipEntry(path));
            } catch (IOException e) {
                LOGGER.error("is not directory zip put next entry error.");
            }

            FileInputStream fileInputStream = new FileInputStream(inputFile);

            int b;
            while ((b = fileInputStream.read()) != -1) {
                zipOutputStream.write(b);
            }
            LOGGER.info("zip file:{}", zipOutputStream);
            fileInputStream.close();
        }
    }
}
