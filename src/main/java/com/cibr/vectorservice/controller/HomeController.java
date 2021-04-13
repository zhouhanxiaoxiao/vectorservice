package com.cibr.vectorservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cibr.vectorservice.entity.CibrConfigSelect;
import com.cibr.vectorservice.entity.CibrSysTask;
import com.cibr.vectorservice.entity.CibrSysUserGroup;
import com.cibr.vectorservice.service.ConfigService;
import com.cibr.vectorservice.service.TaskService;
import com.cibr.vectorservice.service.UserService;
import com.cibr.vectorservice.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/home/user", method = RequestMethod.GET)
    public String getUser(HttpServletRequest request,
                          HttpServletResponse response
    ){
        ReturnData ret = new ReturnData();
        try {
            Map<String, Object> retMap = new HashMap<>();
            String userId = (String) request.getAttribute("userId");
            ReturnData userInfo = userService.getUserInfo(userId);
            ret=userInfo;
        }catch (Exception e){
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/home/getGroupInfo", method = RequestMethod.GET)
    public String getGroupInfo(HttpServletRequest request,
                          HttpServletResponse response
    ){
        ReturnData ret = new ReturnData();
        try {
            Map<String, Object> retMap = new HashMap<>();
            String userId = (String) request.getAttribute("userId");
            CibrSysUserGroup groupInfo = userService.getGroupInfo(userId);
            retMap.put("group", groupInfo);
            ret.setRetMap(retMap);
            ret.setCode("success");
        }catch (Exception e){
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/home/getTask", method = RequestMethod.GET)
    public String getTask(HttpServletRequest request,
                               HttpServletResponse response
    ){
        ReturnData ret = new ReturnData();
        try {
            Map<String, Object> retMap = new HashMap<>();
            String userId = (String) request.getAttribute("userId");
            List<CibrSysTask> allTask = taskService.getAllTask(userId);
            List<CibrConfigSelect> options = configService.getAllOptions();
            retMap.put("tasks", allTask);
            retMap.put("options", options);
            ret.setRetMap(retMap);
            ret.setCode("success");
        }catch (Exception e){
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

}
