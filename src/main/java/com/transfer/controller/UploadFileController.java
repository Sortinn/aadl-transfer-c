package com.transfer.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author tian.gao
 * @create 2019.01.04 4:10 PM
 **/

@Controller
@RequestMapping("/upload")
public class UploadFileController {

    private static Logger LOGGER = LoggerFactory.getLogger(UploadFileController.class);

    @RequestMapping("/index")
    public String showIndex() {
        return "file";
    }

    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public String uploadSingleFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
                LOGGER.info("uploading file...");
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/Users/sortinn/Desktop", System.currentTimeMillis() + file.getOriginalFilename()));
                LOGGER.info("upload file success.");
                return "success";
        }
        return "fail";
    }
}
