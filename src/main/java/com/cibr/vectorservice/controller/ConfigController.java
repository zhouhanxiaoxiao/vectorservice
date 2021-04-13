package com.cibr.vectorservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cibr.vectorservice.entity.CibrConfigGroupsetting;
import com.cibr.vectorservice.entity.CibrConfigSelect;
import com.cibr.vectorservice.service.ConfigService;
import com.cibr.vectorservice.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/config/addNewOption", method = RequestMethod.POST)
    public String addNewOption(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestBody Map requestBody
    ){
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String optionStr = (String) requestBody.get("option");
            CibrConfigSelect option = JSONObject.parseObject(optionStr, CibrConfigSelect.class);
            String result = configService.addNewOption(option);
            ret.setRetMap(retMap);
            ret.setCode(result);
        }catch (Exception e){
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/config/initRaavSelect", method = RequestMethod.GET)
    public String initRaavSelect(HttpServletRequest request,
                               HttpServletResponse response
    ){
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            Map<String, List<CibrConfigSelect>> options = configService.initRaavSelect();
            retMap.putAll(options);
            ret.setRetMap(retMap);
            ret.setCode("success");
        }catch (Exception e){
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/config/getAllEmailConfig", method = RequestMethod.GET)
    public String getAllEmailConfig(HttpServletRequest request,
                                 HttpServletResponse response
    ){
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String userId = (String) request.getAttribute("userId");
            List<CibrConfigGroupsetting> allEmailConfig = configService.getAllEmailConfig(userId);
            retMap.put("groupSettings", allEmailConfig);
            ret.setRetMap(retMap);
            ret.setCode("success");
        }catch (Exception e){
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/config/changePiConfirm", method = RequestMethod.POST)
    public String changePiConfirm(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestBody Map requestBody
    ){
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String setting = (String) requestBody.get("setting");
            CibrConfigGroupsetting groupsetting = JSONObject.parseObject(setting, CibrConfigGroupsetting.class);
            configService.updateGroupSetting(groupsetting);
            ret.setRetMap(retMap);
            ret.setCode("success");
        }catch (Exception e){
            ret.setCode("E500");
            ret.setErrMsg("系统异常！");
            e.printStackTrace();
        }
        return JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/config/deleteOption", method = RequestMethod.POST)
    public String deleteOption(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestBody Map requestBody
    ){
        ReturnData ret = new ReturnData();
        try {
            Map retMap = new HashMap();
            String id = (String) requestBody.get("id");
            configService.deleteOption(id);
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
