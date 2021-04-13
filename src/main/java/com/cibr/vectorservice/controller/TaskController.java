package com.cibr.vectorservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cibr.vectorservice.entity.*;
import com.cibr.vectorservice.service.ConfigService;
import com.cibr.vectorservice.service.FileService;
import com.cibr.vectorservice.service.TaskService;
import com.cibr.vectorservice.service.UserService;
import com.cibr.vectorservice.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/task/initRaavTask", method = RequestMethod.GET)
    public String initRaavTask(HttpServletRequest request,
                               HttpServletResponse response
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            Map<String, List<CibrConfigSelect>> options = configService.initRaavSelect();
            retMap.putAll(options);
            ret.setRetMap(retMap);
            ret.setCode("success");
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/commitraav", method = RequestMethod.POST)
    public String commitraav(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody Map requestBody
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String list = (String) requestBody.get("list");
            String taskRemark = (String) requestBody.get("taskRemark");
            List<CibrTaskRaav> raavs = JSONObject.parseArray(list, CibrTaskRaav.class);
            String userId = (String) request.getAttribute("userId");
            taskService.commitraav(raavs, taskRemark, userId);
            ret.setRetMap(retMap);
            ret.setCode("success");
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/updateRaav", method = RequestMethod.POST)
    public String updateRaav(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody Map requestBody
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String raavStr = (String) requestBody.get("raav");
            String userId = (String) request.getAttribute("userId");
            CibrTaskRaav raav = JSONObject.parseObject(raavStr, CibrTaskRaav.class);
            String result = taskService.updateRaav(raav, userId);
            ret.setRetMap(retMap);
            ret.setCode(result);
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/updateStatus", method = RequestMethod.POST)
    public String updateStatus(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestBody Map requestBody
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String taskId = (String) requestBody.get("taskId");
            String flag = (String) requestBody.get("flag");
            String reason = (String) requestBody.get("reason");
            String userId = (String) request.getAttribute("userId");
            taskService.updateTaskStatus(taskId, flag, reason, userId);
            ret.setRetMap(retMap);
            ret.setCode("success");
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/raav/updateStatu", method = RequestMethod.POST)
    public String raavUpdateStatu(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestBody Map requestBody
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String raavStr = (String) requestBody.get("raav");
            CibrTaskRaav cibrTaskRaav = JSONObject.parseObject(raavStr, CibrTaskRaav.class);
            String userId = (String) request.getAttribute("userId");
            taskService.raavUpdateStatu(cibrTaskRaav, userId);
            ret.setRetMap(retMap);
            ret.setCode("success");
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/evaluation/commit", method = RequestMethod.POST)
    public String evaluationCommit(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody Map requestBody
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            double stars = (double) requestBody.get("stars");
            String evaluation = (String) requestBody.get("evaluation");
            String taskId = (String) requestBody.get("taskId");
            String userId = (String) request.getAttribute("userId");

            taskService.evaluationCommit(stars, evaluation, taskId, userId);
            ret.setRetMap(retMap);
            ret.setCode("success");
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/evaluation/details", method = RequestMethod.POST)
    public String evaluationDetails(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody Map requestBody
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String detailId = (String) requestBody.get("detailId");
            String creater = (String) requestBody.get("creater");
            List<CibrSysSuggest> comments = taskService.findComments(detailId);
            CibrSysUser createrObj = userService.getUserObject(creater);
            List<CibrSysFile> files = fileService.findFiles(detailId);
            retMap.put("comments", comments);
            retMap.put("creater", createrObj);
            retMap.put("files", files);
            ret.setRetMap(retMap);
            ret.setCode("success");
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/suggest/commit", method = RequestMethod.POST)
    public String suggestCommit(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestBody Map requestBody
    ) {
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String detailId = (String) requestBody.get("detailId");
            String reply = (String) requestBody.get("reply");
            String userId = (String) request.getAttribute("userId");
            String uploadFiles = (String) requestBody.get("uploadFiles");
            List<String> fileIds = JSONObject.parseArray(uploadFiles, String.class);
            taskService.submitSuggest(detailId, reply, userId, fileIds);
            ret.setRetMap(retMap);
            ret.setCode("success");
        } catch (Exception e) {
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/task/downLoadServiceTable", method = RequestMethod.POST)
    public void downLoadServiceTable(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody Map requestBody
    ) throws IOException {
        Map retMap = new HashMap();
        String taskId = (String) requestBody.get("taskId");
        byte[] serviceTable = taskService.getServiceTable(taskId);
        response.setHeader("Content-disposition", "attachment; filename=");
        response.setContentType("application/txt");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(serviceTable);
        outputStream.flush();
        outputStream.close();
    }


    @RequestMapping(value = "/emailAction/raav/action")
    public String PiConfirm(HttpServletRequest request,
                            HttpServletResponse response
    ) {
        try {
            String flag = request.getParameter("flag");
            String taskId = request.getParameter("taskId");
            taskService.PiConfirm(flag, taskId);
            return "操作成功！";
        } catch (Exception e) {
            return "系统异常！";
        }
    }
}
