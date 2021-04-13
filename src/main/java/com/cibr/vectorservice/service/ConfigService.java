package com.cibr.vectorservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cibr.vectorservice.dao.CibrConfigGroupsettingMapper;
import com.cibr.vectorservice.dao.CibrConfigSelectMapper;
import com.cibr.vectorservice.entity.*;
import com.cibr.vectorservice.util.CommonUtil;
import com.cibr.vectorservice.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConfigService {

    @Autowired
    private CibrConfigSelectMapper selectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CibrConfigGroupsettingMapper groupsettingMapper;


    public String addNewOption(CibrConfigSelect option) {

        CibrConfigSelectExample selectExample = new CibrConfigSelectExample();
        selectExample.createCriteria().andNameEqualTo(option.getName()).andSelecttypeEqualTo(option.getSelecttype());
        List<CibrConfigSelect> dbList = selectMapper.selectByExample(selectExample);

        if (dbList != null && dbList.size() > 0){
            return "raav.alreadyExist";
        }

        option.setCurstatu("00");
        option.setId(CommonUtil.UUID());
        option.setCreatetime(new Date());
        selectMapper.insert(option);
        return "success";
    }

    public Map<String, List<CibrConfigSelect>> initRaavSelect() {
        Map<String, List<CibrConfigSelect>> retMap = new HashMap<>();

        CibrConfigSelectExample mpExample = new CibrConfigSelectExample();
        mpExample.createCriteria().andSelecttypeEqualTo("materialProvided")
                .andPagetypeEqualTo("raav").andCurstatuNotEqualTo("09");
        mpExample.setOrderByClause("name");
        List<CibrConfigSelect> mpOptions = selectMapper.selectByExample(mpExample);
        retMap.put("mpOptions", mpOptions);

        CibrConfigSelectExample grExample = new CibrConfigSelectExample();
        grExample.createCriteria().andSelecttypeEqualTo("geneRisk")
                .andPagetypeEqualTo("raav").andCurstatuNotEqualTo("09");;
        grExample.setOrderByClause("name");
        List<CibrConfigSelect> grOptions = selectMapper.selectByExample(grExample);
        retMap.put("grOptions", grOptions);

        CibrConfigSelectExample stExample = new CibrConfigSelectExample();
        stExample.createCriteria().andSelecttypeEqualTo("seroType")
                .andPagetypeEqualTo("raav").andCurstatuNotEqualTo("09");;
        stExample.setOrderByClause("name");
        List<CibrConfigSelect> stOptions = selectMapper.selectByExample(stExample);
        retMap.put("stOptions", stOptions);

        return retMap;
    }

    public void deleteOption(String id) {
        CibrConfigSelect option = selectMapper.selectByPrimaryKey(id);
        option.setCurstatu("09");
        selectMapper.updateByPrimaryKey(option);
    }

    public List<CibrConfigSelect>  getAllOptions() {
        List<CibrConfigSelect> cibrConfigSelects = selectMapper.selectByExample(new CibrConfigSelectExample());
        return cibrConfigSelects;
    }

    public List<CibrConfigGroupsetting> getAllEmailConfig(String userId) {
        List<CibrSysUserGroup> groups = userService.getAllGroups(userId);

        List<Map<String,Object>> groupMaps = new ArrayList<>();

        List<CibrConfigGroupsetting> groupsettings = groupsettingMapper.selectByExample(new CibrConfigGroupsettingExample());

        Map<String,CibrConfigGroupsetting> groupId_setting = new HashMap<>();
        for (CibrConfigGroupsetting groupsetting : groupsettings){
            groupId_setting.put(groupsetting.getGroupid(), groupsetting);
        }

        List<CibrConfigGroupsetting> retList = new ArrayList<>();
        for (CibrSysUserGroup group : groups){
            Map<String,Object> map = new HashMap<>();
            CibrConfigGroupsetting groupsetting = groupId_setting.get(group.getId());
            if (groupsetting != null){
                map.put("settingId", groupsetting.getId());
                map.put("email", groupsetting.getEmail());
                retList.add(groupsetting);
            }else {
                CibrConfigGroupsetting newSet = new CibrConfigGroupsetting();
                newSet.setEmail(true);
                newSet.setGroupname(group.getGroupname());
                newSet.setGroupid(group.getId());
                newSet.setId(CommonUtil.UUID());
                groupsettingMapper.insert(newSet);
                retList.add(newSet);
            }
        }
        return retList;
    }

    public void updateGroupSetting(CibrConfigGroupsetting groupsetting) {
        groupsettingMapper.updateByPrimaryKey(groupsetting);
    }
}


























