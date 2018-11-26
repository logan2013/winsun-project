package com.winsun.item.core.util.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

public class ExportExcel {
    /**
     * 返回输出流
     * @param workBook
     * @param request
     * @param response
     * @param FileName
     * @throws Exception
     */
    public static ResponseEntity<byte[]> toDownLoad(XSSFWorkbook workBook, HttpServletRequest request, HttpServletResponse response, String FileName) throws Exception{
    	ResponseEntity<byte[]> entity = null;
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {

            workBook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String realFileName = FileName + ".xlsx";

        // 浏览器兼容
        String agent = request.getHeader("User-Agent");
        if (null != agent) {
            agent = agent.toLowerCase();
            if (agent.indexOf("firefox") != -1) {
                realFileName = new String(URLDecoder.decode(realFileName, "UTF-8").getBytes(), "iso-8859-1");
            } else {
                realFileName = java.net.URLEncoder.encode(realFileName, "UTF-8");
            }
        }

        byte[] content = os.toByteArray();

        InputStream is = new ByteArrayInputStream(content);
        
        

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + realFileName);
        ServletOutputStream out = response.getOutputStream();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/vnd.ms-excel;charset=utf-8");
        headers.set("Content-Disposition", "attachment;filename=" + realFileName);
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            entity = new ResponseEntity<byte[]>(buff, headers, HttpStatus.OK);
            return entity;
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

}
