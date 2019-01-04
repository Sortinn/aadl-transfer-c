package com.transfer.controller;

import com.google.common.collect.Maps;
import com.transfer.service.TransferToCService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author tian.gao
 **/

@Controller
@RequestMapping("/upload")
public class UploadFileController {

    private static final String BASE_PATH = "/Users/sortinn/";
    private static Logger LOGGER = LoggerFactory.getLogger(UploadFileController.class);

    @Resource
    private TransferToCService transferToCService;

    @RequestMapping("/index")
    public String showIndex() {
        return "file";
    }

    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public String uploadAndTransfer(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty() && FilenameUtils.getExtension(file.getOriginalFilename()).equals("xml")) {
            String acceptedFilename = file.getOriginalFilename() + System.currentTimeMillis();
            String filePath = BASE_PATH + acceptedFilename;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(BASE_PATH, acceptedFilename));
            transferToCService.transer(filePath);
            Map<String, Object> data = Maps.newHashMap();
            data.put("name", file.getOriginalFilename());
            data.put("filePath",filePath);
            LOGGER.info("data : {}, ", data);
            return "success";
        }
        return "fail";
    }
}
