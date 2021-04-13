package com.cibr.vectorservice.entity;

import java.util.Date;
import java.util.List;

public class CibrSysSuggest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.creater
     *
     * @mbggenerated
     */
    private String creater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.comments
     *
     * @mbggenerated
     */
    private String comments;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.replyto
     *
     * @mbggenerated
     */
    private String replyto;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.flag
     *
     * @mbggenerated
     */
    private String flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.userName
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.userHead
     *
     * @mbggenerated
     */
    private String userhead;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_suggest.enName
     *
     * @mbggenerated
     */
    private String enname;

    private List<CibrSysFile> files;

    public List<CibrSysFile> getFiles() {
        return files;
    }

    public void setFiles(List<CibrSysFile> files) {
        this.files = files;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.id
     *
     * @return the value of cibr_sys_suggest.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.id
     *
     * @param id the value for cibr_sys_suggest.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.creater
     *
     * @return the value of cibr_sys_suggest.creater
     *
     * @mbggenerated
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.creater
     *
     * @param creater the value for cibr_sys_suggest.creater
     *
     * @mbggenerated
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.createTime
     *
     * @return the value of cibr_sys_suggest.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.createTime
     *
     * @param createtime the value for cibr_sys_suggest.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.comments
     *
     * @return the value of cibr_sys_suggest.comments
     *
     * @mbggenerated
     */
    public String getComments() {
        return comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.comments
     *
     * @param comments the value for cibr_sys_suggest.comments
     *
     * @mbggenerated
     */
    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.replyto
     *
     * @return the value of cibr_sys_suggest.replyto
     *
     * @mbggenerated
     */
    public String getReplyto() {
        return replyto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.replyto
     *
     * @param replyto the value for cibr_sys_suggest.replyto
     *
     * @mbggenerated
     */
    public void setReplyto(String replyto) {
        this.replyto = replyto == null ? null : replyto.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.flag
     *
     * @return the value of cibr_sys_suggest.flag
     *
     * @mbggenerated
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.flag
     *
     * @param flag the value for cibr_sys_suggest.flag
     *
     * @mbggenerated
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.userName
     *
     * @return the value of cibr_sys_suggest.userName
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.userName
     *
     * @param username the value for cibr_sys_suggest.userName
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.userHead
     *
     * @return the value of cibr_sys_suggest.userHead
     *
     * @mbggenerated
     */
    public String getUserhead() {
        return userhead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.userHead
     *
     * @param userhead the value for cibr_sys_suggest.userHead
     *
     * @mbggenerated
     */
    public void setUserhead(String userhead) {
        this.userhead = userhead == null ? null : userhead.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_suggest.enName
     *
     * @return the value of cibr_sys_suggest.enName
     *
     * @mbggenerated
     */
    public String getEnname() {
        return enname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_suggest.enName
     *
     * @param enname the value for cibr_sys_suggest.enName
     *
     * @mbggenerated
     */
    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }
}