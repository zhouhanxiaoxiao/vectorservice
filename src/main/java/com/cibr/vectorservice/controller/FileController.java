package com.cibr.vectorservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cibr.vectorservice.service.FileService;
import com.cibr.vectorservice.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/file/commonUpload",method = RequestMethod.POST)
    public String uploadProcessFile(HttpServletRequest request,
                                    HttpServletResponse response) {

        ReturnData ret = new ReturnData();
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            String userId = (String) request.getAttribute("userId");
            String detailId = request.getParameter("detailId");
            List<String> fileIds = fileService.saveFile(files, detailId, userId);
            if (fileIds.size() == 0){
                response.setStatus(500);
                ret.setCode("E500");
            }else {
                result.put("fileIds", fileIds);
                ret.setCode("200");
                ret.setRetMap(result);
            }
        } catch (Exception e) {
            response.setStatus(500);
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/file/download",method = RequestMethod.POST)
    public void downloadSampleInput(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody Map requestBody) throws IOException {
        String fileId = (String) requestBody.get("id");
        File file = fileService.getFileById(fileId);
        response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
        response.setContentType("application/txt");
        OutputStream outputStream = response.getOutputStream();
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            outputStream.write(buff, 0, index);
            outputStream.flush();
        }
        outputStream.close();
        input.close();
    }
}
