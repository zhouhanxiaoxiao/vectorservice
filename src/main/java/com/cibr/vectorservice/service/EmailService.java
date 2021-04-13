package com.cibr.vectorservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cibr.vectorservice.dao.CibrSysEmailMapper;
import com.cibr.vectorservice.entity.CibrSysEmail;
import com.cibr.vectorservice.entity.CibrSysUser;
import com.cibr.vectorservice.util.CommonUtil;
import com.cibr.vectorservice.util.EmailUtil;
import com.cibr.vectorservice.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private CibrSysEmailMapper emailMapper;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public CibrSysEmail sendMail(CibrSysEmail mailVo) {
        try {
            //2.发送邮件
            CibrSysEmail cibrSysEmail = sendMimeMail(mailVo, false);
            if (!"ok".equals(cibrSysEmail.getEmailStatus())){
                return cibrSysEmail;
            }
            //3.保存邮件
            return saveMail(mailVo);
        } catch (Exception e) {
            return saveMail(mailVo);
        }
    }

    @Async
    public CibrSysEmail sendHtmlMail(CibrSysEmail mailVo) {
        try {
            //2.发送邮件
            CibrSysEmail email = sendMimeMail(mailVo,true);
            if (!"ok".equals(email.getEmailStatus())){
                return email;
            }
            //3.保存邮件
            return saveMail(mailVo);
        } catch (Exception e) {
            mailVo.setEmailStatus("fail");
            return saveMail(mailVo);
        }
    }

    //构建复杂邮件信息类
    private CibrSysEmail sendMimeMail(CibrSysEmail mailVo,boolean html) throws Exception {
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人从配置项读取
            mailVo.setEmailFrom(from);
            //邮件发信人
            messageHelper.setFrom(mailVo.getEmailFrom());
            //邮件收信人
            messageHelper.setTo(mailVo.getEmailTo().split(","));
            //邮件主题
            messageHelper.setSubject(mailVo.getEmailSubject());
            //邮件内容
            messageHelper.setText(mailVo.getEmailText(),html);
            //抄送
            if (!StringUtils.isEmpty(mailVo.getEmailCc())) {
                messageHelper.setCc(mailVo.getEmailCc().split(","));
            }
            //密送
            if (!StringUtils.isEmpty(mailVo.getEmailBcc())) {
                messageHelper.setCc(mailVo.getEmailBcc().split(","));
            }
            //添加邮件附件
            if (mailVo != null && mailVo.getMultipartFiles() != null) {
                for(MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            //发送时间
            if (StringUtils.isEmpty(mailVo.getEmailSentdate())) {
                mailVo.setEmailSentdate(new Date());
                messageHelper.setSentDate(mailVo.getEmailSentdate());
            }
            //正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            mailVo.setEmailStatus("ok");
        } catch (Exception e) {
            mailVo.setEmailStatus("user.noAddr");
            mailVo.setEmailError(e.getMessage());
        }
        return mailVo;
    }


    public CibrSysEmail createCibrSysEmail(String emailTo, String emailText,String emailSubject,String cc) {
        CibrSysEmail email = new CibrSysEmail();
        email.setEmailId(CommonUtil.UUID());
        email.setEmailFrom(from);
        email.setEmailTo(emailTo);
        email.setEmailText(emailText);
        email.setEmailSubject(emailSubject);
        email.setEmailSentdate(new Date());
        email.setEmailCc(cc);
        return email;
    }

    //保存邮件
    private CibrSysEmail saveMail(CibrSysEmail mailVo) {
        emailMapper.insert(mailVo);
        return mailVo;
    }

    public CibrSysEmail simpleSendEmailByUserId(String context, String userId,String sub){
        ReturnData userInfo = userService.getUserInfo(userId);
        String userStr = userInfo.getRetMap().get("user").toString();
        CibrSysUser user = JSONObject.parseObject(userStr, CibrSysUser.class);

        context = user.getName() + "老师，您好！ \n\n" + context + EmailUtil.EMAIL_SUFFIX;

        return simpleSendEmail(context,user.getEmail(),sub);
    }

    public CibrSysEmail simpleSendEmailByUserId(String context, String userId,String sub, String emailCcUserId){
        ReturnData userInfo = userService.getUserInfo(userId);
        String userStr = userInfo.getRetMap().get("user").toString();
        CibrSysUser user = JSONObject.parseObject(userStr, CibrSysUser.class);

        ReturnData ccUserInfo = userService.getUserInfo(emailCcUserId);
        String ccUserStr = ccUserInfo.getRetMap().get("user").toString();
        CibrSysUser ccUser = JSONObject.parseObject(ccUserStr, CibrSysUser.class);

        return simpleSendEmail(context,user.getEmail(),sub, ccUser.getEmail());
    }

    public CibrSysEmail simpleSendEmail(String context,String addr,String sub){
        CibrSysEmail cibrSysEmail = createCibrSysEmail(addr, context, sub,null);
        return sendMail(cibrSysEmail);
    }

    public CibrSysEmail simpleSendEmail(String context,String addr,String sub,String emailCc){
        CibrSysEmail cibrSysEmail = createCibrSysEmail(addr, context, sub, emailCc);
        return sendMail(cibrSysEmail);
    }

    public void simpleSendEmail(String context, List<String> addrs, String sub){
        for (String addr : addrs){
            CibrSysEmail cibrSysEmail = createCibrSysEmail(addr, context, sub,null);
            sendMail(cibrSysEmail);
        }
    }

    public CibrSysEmail simpleSendHtmlEmail(String context,String addr,String sub){
        CibrSysEmail cibrSysEmail = createCibrSysEmail(addr, context, sub,null);
        return sendHtmlMail(cibrSysEmail);
    }
}
