package com.cibr.vectorservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cibr.vectorservice.dao.CibrSysTaskMapper;
import com.cibr.vectorservice.entity.CibrSysTask;
import com.cibr.vectorservice.entity.CibrSysUser;
import com.cibr.vectorservice.entity.CibrSysUserGroup;
import com.cibr.vectorservice.util.HttpRequestUtils;
import com.cibr.vectorservice.util.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${cibr.user.url}")
    private String urlPrefix;

    @Value("${cibr.user.defaultId}")
    private String defautUserId;

    @Autowired
    private CibrSysTaskMapper taskMapper;

    public ReturnData getUserInfo(String userId) {
        Map<String,String> params = new HashMap<>();
        params.put("userId", userId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/getUserInfo", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        return returnData;
    }

    public List<CibrSysUser> selectUserByRoleType(String roleTyp, String userId) {
        logger.info("根据角色类型获取用户列表： roleType = {}, userId = {}", roleTyp, userId);
        Map<String,String> params = new HashMap<>();
        if (userId == null){
            userId = defautUserId;
        }
        params.put("roleType", roleTyp);
        params.put("userId", userId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/selectUserByRoleType", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        List<CibrSysUser> usersByRole = JSONObject.parseArray(returnData.getRetMap().get("usersByRole").toString(), CibrSysUser.class);
        return usersByRole;
    }

    public CibrSysUserGroup getGroupByName(String groupName, String userId) {
        logger.info("根据部门名称获取部门信息： groupName={}, userId={}", groupName, userId);
        Map<String,String> params = new HashMap<>();
        if (userId == null){
            userId = defautUserId;
        }
        params.put("groupName", groupName);
        params.put("userId", userId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/getGroupByName", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        CibrSysUserGroup group = JSONObject.parseObject(returnData.getRetMap().get("group").toString(), CibrSysUserGroup.class);
        return group;
    }

    public List<CibrSysUser> selectAllUserWithDesc() {
        logger.info("获取所有用户详细信息！");
        Map<String,String> params = new HashMap<>();
        params.put("userId", defautUserId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/selectAllUserWithDesc", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        List<CibrSysUser> usersByRole = JSONObject.parseArray(returnData.getRetMap().get("selectAllUserWithDesc").toString(), CibrSysUser.class);
        return usersByRole;
    }

    public CibrSysUser getGroupAdmin(String userId) {
        logger.info("获取部门负责人信息： userId = {}", userId);
        Map<String,String> params = new HashMap<>();
        if (userId == null){
            userId = defautUserId;
        }
        params.put("userId", userId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/getGroupAdmin", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        CibrSysUser admin = JSONObject.parseObject(returnData.getRetMap().get("admin").toString(), CibrSysUser.class);
        return admin;
    }


    public CibrSysUser getUserObject(String userId) {
        logger.info("获取用户信息： userid ======= " + userId);
        ReturnData userInfo = this.getUserInfo(userId);
        String userStr = userInfo.getRetMap().get("user").toString();
        CibrSysUser user = JSONObject.parseObject(userStr, CibrSysUser.class);
        return user;
    }

    public List<CibrSysUser> findUserByRoleAndGroup(String roleTypeReviewer, String groupName, String userId) {
        logger.info("根据角色和组别获取用户列表： roleTypeReviewer = {},groupName = {}, userId= {}", roleTypeReviewer, groupName, userId);
        Map<String,String> params = new HashMap<>();
        if (userId == null){
            userId = defautUserId;
        }
        params.put("userId", userId);
        params.put("roleTypeReviewer", roleTypeReviewer);
        params.put("groupName", groupName);
        String s = HttpRequestUtils.doPost(urlPrefix + "/findUserByRoleAndGroup", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        List<CibrSysUser> users = JSONObject.parseArray(returnData.getRetMap().get("users").toString(), CibrSysUser.class);
        return users;
    }

    public List<CibrSysUser> findGroupReviewer(String userId){
        logger.info("获取部门审核人信息： userId = {}" + userId);
        Map<String,String> params = new HashMap<>();
        if (userId == null){
            userId = defautUserId;
        }
        params.put("userId", userId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/findGroupReviewer", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        List<CibrSysUser> reviews = JSONObject.parseArray(returnData.getRetMap().get("reviews").toString(), CibrSysUser.class);
        return reviews;
    }

    public List<CibrSysUserGroup> getAllGroups(String userId) {
        logger.info("获取所有部门信息！");
        Map<String,String> params = new HashMap<>();
        if (userId == null){
            userId = defautUserId;
        }
        params.put("userId", userId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/getAllGroups", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        List<CibrSysUserGroup> groups = JSONObject.parseArray(returnData.getRetMap().get("groups").toString(), CibrSysUserGroup.class);
        return groups;
    }

    public CibrSysUserGroup getGroupInfo(String userId) {
        logger.info("根据用户Id获取部门信息： userId = {}" + userId);
        Map<String,String> params = new HashMap<>();
        if (userId == null){
            userId = defautUserId;
        }
        params.put("userId", userId);
        String s = HttpRequestUtils.doPost(urlPrefix + "/getGroupInfo", params);
        ReturnData returnData = JSONObject.parseObject(s, ReturnData.class);
        CibrSysUserGroup group = JSONObject.parseObject(returnData.getRetMap().get("group").toString(), CibrSysUserGroup.class);
        return group;
    }

}
