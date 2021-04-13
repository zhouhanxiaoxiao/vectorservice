package com.cibr.vectorservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cibr.vectorservice.dao.*;
import com.cibr.vectorservice.entity.*;
import com.cibr.vectorservice.util.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.*;

@Service
public class TaskService {

    @Value("${vector.fontSimsun}")
    private String FONT_SIMSUN;

    @Value("${vector.filePath}")
    private String FILE_PATH;

    @Autowired
    private CibrTaskRaavMapper raavMapper;

    @Autowired
    private CibrSysTaskMapper taskMapper;

    @Autowired
    private CibrConfigGroupsettingMapper groupsettingMapper;

    @Autowired
    private CibrConfigSelectMapper selectMapper;

    @Autowired
    private CibrTaskFailMapper failMapper;

    @Autowired
    private CibrSysEvaluationMapper evaluationMapper;

    @Autowired
    private CibrSysSuggestMapper suggestMapper;

    @Autowired
    private CibrSysFileMapper fileMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;

    public List<CibrSysTask> getAllTask(String userId) {
        CibrSysUser user = userService.getUserObject(userId);

        String isAdmin = CommonUtil.isGroupViewer(user);

        List<String> userIds = new ArrayList<>();
        if ("systemAdmin".equals(isAdmin)){
            userIds = new ArrayList<>();
        } else if ("pi".equals(isAdmin)){
            CibrSysUserGroup groupInfo = userService.getGroupInfo(user.getId());
            for (CibrSysUser tmp : groupInfo.getUsers()){
                userIds.add(tmp.getId());
            }
        }else {
            userIds.add(user.getId());
        }
        if ("pi".equals(isAdmin) && "载体工程中心".equals(user.getGroup().getGroupname())){
            userIds = new ArrayList<>();
        }
        List<CibrSysTask> tasks = taskMapper.getAllTask(userIds);
        List<CibrSysUser> userList = userService.selectAllUserWithDesc();
        Map<String, CibrSysUser> uuid_user = new HashMap<>();
        userList.forEach(item -> uuid_user.put(item.getId(), item));
        if (tasks != null && tasks.size() > 0){
            tasks.forEach(item ->{
                if (item != null){
                    item.setGroupAdmin(uuid_user.get(item.getCreater()).getGroup().getGroupadmin());
                    if ("00".equals(item.getTaskstatu()) || "01".equals(item.getTaskstatu())){
                        CibrSysTaskExample taskExample = new CibrSysTaskExample();
                        taskExample.createCriteria().andCreatetimeLessThan(item.getCreatetime())
                                .andTaskstatuIn(Arrays.asList("00", "01"));
                        int count = taskMapper.countByExample(taskExample);
                        item.setBefore(count);
                    }else {
                        item.setBefore(0);
                    }
                }
            });
        }
        return tasks;
    }

    @Transactional(rollbackFor = Exception.class)
    public void commitraav(List<CibrTaskRaav> raavs, String taskRemark, String userId) throws IOException, TemplateException {

        CibrSysUserGroup group = userService.getGroupInfo(userId);
        CibrSysUser user = userService.getUserObject(userId);

        CibrConfigGroupsettingExample groupsettingExample = new CibrConfigGroupsettingExample();
        groupsettingExample.createCriteria().andGroupidEqualTo(group.getId());
        List<CibrConfigGroupsetting> groupsettings = groupsettingMapper.selectByExample(groupsettingExample);
        CibrConfigGroupsetting dbsetting = new CibrConfigGroupsetting();
        if (groupsettings == null || groupsettings.size() == 0){
            CibrConfigGroupsetting groupsetting = new CibrConfigGroupsetting();
            groupsetting.setId(CommonUtil.UUID());
            groupsetting.setGroupid(group.getId());
            groupsetting.setEmail(true);
            groupsetting.setGroupname(group.getGroupname());
            groupsettingMapper.insert(groupsetting);
            dbsetting = groupsetting;
        }else {
            dbsetting = groupsettings.get(0);
        }
        CibrSysTask task = new CibrSysTask();
        task.setId(CommonUtil.UUID());
        task.setTasktype(TaskUtil.TASK_TYPE_RAAV);
        if (dbsetting.getEmail()){
            task.setTaskstatu(TaskUtil.TASK_STATUS_PI_CONFIRM);
        }else {
            task.setTaskstatu(TaskUtil.TASK_STATUS_PROCESSING);
        }

        task.setTaskname(group.getGroupname() + "-" + user.getName() + "-"
                + TimeUtil.date2str(new Date(),"yyyyMMdd") + "-" + taskRemark);
        task.setCreater(userId);
        task.setCreatetime(new Date());
        task.setRemark(taskRemark);
        String groupViewer = CommonUtil.isGroupViewer(user);
        if ("pi".equals(groupViewer)){
            task.setTaskstatu(TaskUtil.TASK_STATUS_PROCESSING);
        }
        taskMapper.insert(task);
        for (CibrTaskRaav raav : raavs){
            raav.setId(CommonUtil.UUID());
            raav.setCreater(userId);
            raav.setCreatetime(new Date());
            raav.setCurstatu(TaskUtil.RAAV_TASK_STATUS_WAIT);
            raav.setTaskid(task.getId());
        }
        raavMapper.batchInsert(raavs);

        // PI确认邮件
        if (dbsetting.getEmail() && !"pi".equals(groupViewer)){
            List<CibrConfigSelect> selects = selectMapper.selectByExample(new CibrConfigSelectExample());
            Map<String,String> uuid_name = new HashMap<>();
            for (CibrConfigSelect select :selects){
                uuid_name.put(select.getId(), select.getName());
            }
            List<Map<String,String>> list = new ArrayList<>();
            for (CibrTaskRaav raav : raavs){
                Map<String,String> item = new HashMap<>();
                item.put("constructname",raav.getConstructname());
                item.put("materialprovided",uuid_name.get(raav.getMaterialprovided()));
                item.put("generisk",uuid_name.get(raav.getGenerisk()));
                item.put("serotype",uuid_name.get(raav.getSerotype()));
                item.put("sharing",raav.getSharing() ? "yes" : "no");
                item.put("requestnum",raav.getRequestnum().toString());
                item.put("remark",raav.getRemark());
                list.add(item);
            }
            Map map = new HashMap();
            map.put("list", list);
            map.put("projectName", task.getTaskname());
            map.put("confirmUrl", EmailUtil.ACTION_PREFIX + "raav/action?flag=agree&taskId=" + task.getId() );
            map.put("refuseUrl", EmailUtil.ACTION_PREFIX + "raav/action?flag=refuse&taskId=" + task.getId());
            Configuration configuration = freeMarkerConfigurationFactory.createConfiguration();
            configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
            Template template = configuration.getTemplate("piConfirm.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            emailService.simpleSendHtmlEmail(html, group.getAdmin().getEmail(), EmailUtil.SUB_PI_CONFIRM);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateRaav(CibrTaskRaav raav, String userId) {
        CibrTaskRaav raavdb = raavMapper.selectByPrimaryKey(raav.getId());
        if (Integer.parseInt(raavdb.getCurstatu()) > 1){
            return "task.statusErr";
        }
        raavdb.setConstructname(raav.getConstructname());
        raavdb.setGenerisk(raav.getGenerisk());
        raavdb.setMaterialprovided(raav.getMaterialprovided());
        raavdb.setRemark(raav.getRemark());
        raavdb.setRequestnum(raav.getRequestnum());
        raavdb.setSerotype(raav.getSerotype());
        raavdb.setSharing(raav.getSharing());

        raavMapper.updateByPrimaryKey(raavdb);
        return "success";
    }


    public String updateTaskStatus(String taskId, String flag,String reason, String userId) {
        CibrSysTask task = taskMapper.selectByPrimaryKey(taskId);
        if ("agree".equals(flag)){
            task.setTaskstatu(TaskUtil.TASK_STATUS_PROCESSING);
        }else if ("refuse".equals(flag)){
            task.setTaskstatu(TaskUtil.TASK_STATUS_PI_REFUSE);
            ReturnData userInfo = userService.getUserInfo(task.getCreater());
            String userStr = userInfo.getRetMap().get("user").toString();
            CibrSysUser user = JSONObject.parseObject(userStr, CibrSysUser.class);

            ReturnData groupAdminRet = userService.getUserInfo(userId);
            String groupAdminStr = groupAdminRet.getRetMap().get("user").toString();
            CibrSysUser groupAdmin = JSONObject.parseObject(userStr, CibrSysUser.class);

            String context = user.getName() + "老师，您好：\n\n";
            context += "您提交的服务申请已被PI拒绝，原因：【" + reason + "】，详情联系【" + groupAdmin.getEmail() + "】";
            context += EmailUtil.EMAIL_SUFFIX;

            CibrTaskFail fail = new CibrTaskFail();
            fail.setId(CommonUtil.UUID());
            fail.setCreatetime(new Date());
            fail.setHandler(userId);
            fail.setDetailid(taskId);
            fail.setReason(reason);
            failMapper.insert(fail);

            emailService.simpleSendEmail(context,user.getEmail(), EmailUtil.SUB_REFUSE);
        }else if ("taskComplete".equals(flag)){
            task.setTaskstatu(TaskUtil.TASK_STATUS_CLIENT_CONFIRM);
        }else if ("delete".equals(flag)){
            task.setTaskstatu(TaskUtil.TASK_STATUS_DELETE);

            CibrTaskFail fail = new CibrTaskFail();
            fail.setId(CommonUtil.UUID());
            fail.setCreatetime(new Date());
            fail.setHandler(userId);
            fail.setDetailid(taskId);
            fail.setReason(reason);
            failMapper.insert(fail);
        }
        taskMapper.updateByPrimaryKey(task);
        return "success";
    }

    public void PiConfirm(String flag, String taskId) {
        CibrSysTask task = taskMapper.selectByPrimaryKey(taskId);
        if (task != null && TaskUtil.TASK_STATUS_PI_CONFIRM.equals(task.getTaskstatu())){
            if ("agree".equals(flag)){
                task.setTaskstatu(TaskUtil.TASK_STATUS_PROCESSING);
                taskMapper.updateByPrimaryKey(task);
            }else if ("refuse".equals(flag)){
                task.setTaskstatu(TaskUtil.TASK_STATUS_PI_REFUSE);
                taskMapper.updateByPrimaryKey(task);
                ReturnData userInfo = userService.getUserInfo(task.getCreater());
                String userStr = userInfo.getRetMap().get("user").toString();
                CibrSysUser user = JSONObject.parseObject(userStr, CibrSysUser.class);

                CibrSysUserGroup group = userService.getGroupInfo(task.getCreater());
                CibrTaskFail fail = new CibrTaskFail();
                fail.setId(CommonUtil.UUID());
                fail.setReason("邮件拒绝，详情请咨询PI");
                fail.setDetailid(taskId);
                fail.setHandler(group.getGroupadmin());
                fail.setCreatetime(new Date());
                failMapper.insert(fail);
                String context = user.getName() + "老师，您好：\n\n";
                context += "您提交的服务申请已被PI拒绝，详情联系【" + group.getAdmin().getEmail() + "】";
                context += EmailUtil.EMAIL_SUFFIX;
                emailService.simpleSendEmail(context,user.getEmail(), EmailUtil.SUB_REFUSE);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void raavUpdateStatu(CibrTaskRaav cibrTaskRaav, String userId) {
        CibrTaskRaav taskRaav = raavMapper.selectByPrimaryKey(cibrTaskRaav.getId());
        taskRaav.setCurstatu(cibrTaskRaav.getCurstatu());
        if (TaskUtil.RAAV_TASK_STATUS_FINISH.equals(taskRaav.getCurstatu())){
            taskRaav.setTiter(cibrTaskRaav.getTiter());
            taskRaav.setVol(cibrTaskRaav.getVol());
            CibrTaskRaavExample raavExample = new CibrTaskRaavExample();
            raavExample.createCriteria().andTaskidEqualTo(taskRaav.getTaskid());
            List<CibrTaskRaav> cibrTaskRaavs = raavMapper.selectByExample(raavExample);
            boolean flag = true;
            for (CibrTaskRaav raav : cibrTaskRaavs){
                if (!raav.getId().equals(taskRaav.getId()) && !TaskUtil.RAAV_TASK_STATUS_FINISH.equals(raav.getCurstatu())){
                    flag = false;
                }
            }
            if (flag){
                //更新任务状态
                CibrSysTask task = taskMapper.selectByPrimaryKey(taskRaav.getTaskid());
                task.setTaskstatu(TaskUtil.TASK_STATUS_COMPLETE);
                taskMapper.updateByPrimaryKey(task);
                String context = "您的rAAV包装服务【" + task.getTaskname() + "】 已完成! 请及时确认。";
                emailService.simpleSendEmailByUserId(context, task.getCreater(), EmailUtil.SUB_TASK_COMPLETED);
            }
        }
        raavMapper.updateByPrimaryKey(taskRaav);
    }

    @Transactional(rollbackFor = Exception.class)
    public void evaluationCommit(double stars, String evaluation, String taskId, String userId) {
        CibrSysTask task = taskMapper.selectByPrimaryKey(taskId);
        task.setTaskstatu(TaskUtil.TASK_STATUS_ALL_DONE);

        CibrSysEvaluation sysEvaluation = new CibrSysEvaluation();
        sysEvaluation.setId(CommonUtil.UUID());
        sysEvaluation.setEvaluation(evaluation);
        sysEvaluation.setCreater(userId);
        sysEvaluation.setCreatetime(new Date());
        sysEvaluation.setScore(stars);
        sysEvaluation.setDetailid(taskId);

        taskMapper.updateByPrimaryKey(task);
        evaluationMapper.insert(sysEvaluation);

    }

    public List<CibrSysSuggest> findComments(String detailId) {
//        CibrSysSuggestExample suggestExample = new CibrSysSuggestExample();
//        suggestExample.createCriteria().andFlagEqualTo(detailId);
//        suggestExample.setOrderByClause("createtime");
//        List<CibrSysSuggest> cibrSysSuggests = suggestMapper.selectByExample(suggestExample);
//        if (cibrSysSuggests == null){
//            return  new ArrayList<>();
//        }
        List<CibrSysSuggest> cibrSysSuggests = suggestMapper.findComments(detailId);
        if (cibrSysSuggests == null){
            return  new ArrayList<>();
        }
        return cibrSysSuggests;
    }

    @Transactional(rollbackFor = Exception.class)
    public void submitSuggest(String detailId, String reply, String userId, List<String> fileIds) {
        CibrSysUser user = userService.getUserObject(userId);
        CibrSysSuggest suggest = new CibrSysSuggest();
        suggest.setId(CommonUtil.UUID());
        suggest.setCreater(userId);
        suggest.setUsername(user.getName());
        suggest.setEnname(user.getEnglishname());
        suggest.setCreatetime(new Date());
        suggest.setFlag(detailId);
        suggest.setComments(reply);
        suggest.setUserhead(user.getUserhead());
        suggestMapper.insert(suggest);
        if (fileIds != null && fileIds.size() >0){
            CibrSysFileExample fileExample = new CibrSysFileExample();
            fileExample.createCriteria().andIdIn(fileIds);
            List<CibrSysFile> cibrSysFiles = fileMapper.selectByExample(fileExample);
            if (cibrSysFiles != null && cibrSysFiles.size() >0){
                cibrSysFiles.forEach(item -> {
                    item.setDetailid(suggest.getId());
                    fileMapper.updateByPrimaryKey(item);
                });
            }
        }
    }

    public byte[] getServiceTable(String taskId) {
        Map<String, Object> dataMap = new HashMap<>();
        CibrSysTask task = taskMapper.selectByPrimaryKey(taskId);
        CibrSysUser creater = userService.getUserObject(task.getCreater());
        CibrSysUser groupAdmin = userService.getGroupAdmin(creater.getId());

        CibrTaskRaavExample raavExample = new CibrTaskRaavExample();
        raavExample.createCriteria().andTaskidEqualTo(task.getId());
        List<CibrTaskRaav> raavs = raavMapper.selectByExample(raavExample);

        List<CibrConfigSelect> selects = selectMapper.selectByExample(new CibrConfigSelectExample());
        Map<String, String> uuid_option = new HashMap<>();
        selects.forEach(opt -> uuid_option.put(opt.getId(),opt.getName()));

        raavs.forEach(item->{
            item.setMaterialprovided(uuid_option.get(item.getMaterialprovided()));
            item.setGenerisk(uuid_option.get(item.getGenerisk()));
            item.setSerotype(uuid_option.get(item.getSerotype()));
        });

        dataMap.put("creater", creater);
        dataMap.put("groupAdmin", groupAdmin);
        dataMap.put("raavs", raavs);
        dataMap.put("liftSize", 6 - raavs.size());
        dataMap.put("creatTime",TimeUtil.date2str(task.getCreatetime(), "yyyy-MM-dd"));
        // ftl 转换为 html
        String htmlStr = PDFUtil.freemarkerRender(dataMap, PDFUtil.RAAV_SERVICE_TABLE_FTL);
        // html 转为 pdf
        byte[] pdfBytes = PDFUtil.createPDF(htmlStr, FONT_SIMSUN, FILE_PATH);

        return pdfBytes;
    }
}
