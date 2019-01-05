package com.transfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author tian.gao
 **/
@Service
public class DownLoadZipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownLoadZipService.class);

    public void downLoad(File file, HttpServletResponse response) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(buffer);
            bufferedInputStream.close();

            response.reset();
            BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            LOGGER.error("download error.");
        }
    }
}
