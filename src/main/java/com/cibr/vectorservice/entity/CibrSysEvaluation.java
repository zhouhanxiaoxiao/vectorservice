package com.cibr.vectorservice.entity;

import java.util.Date;

public class CibrSysEvaluation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_evaluation.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_evaluation.detailId
     *
     * @mbggenerated
     */
    private String detailid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_evaluation.score
     *
     * @mbggenerated
     */
    private Double score;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_evaluation.evaluation
     *
     * @mbggenerated
     */
    private String evaluation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_evaluation.creater
     *
     * @mbggenerated
     */
    private String creater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cibr_sys_evaluation.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_evaluation.id
     *
     * @return the value of cibr_sys_evaluation.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_evaluation.id
     *
     * @param id the value for cibr_sys_evaluation.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_evaluation.detailId
     *
     * @return the value of cibr_sys_evaluation.detailId
     *
     * @mbggenerated
     */
    public String getDetailid() {
        return detailid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_evaluation.detailId
     *
     * @param detailid the value for cibr_sys_evaluation.detailId
     *
     * @mbggenerated
     */
    public void setDetailid(String detailid) {
        this.detailid = detailid == null ? null : detailid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_evaluation.score
     *
     * @return the value of cibr_sys_evaluation.score
     *
     * @mbggenerated
     */
    public Double getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_evaluation.score
     *
     * @param score the value for cibr_sys_evaluation.score
     *
     * @mbggenerated
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_evaluation.evaluation
     *
     * @return the value of cibr_sys_evaluation.evaluation
     *
     * @mbggenerated
     */
    public String getEvaluation() {
        return evaluation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_evaluation.evaluation
     *
     * @param evaluation the value for cibr_sys_evaluation.evaluation
     *
     * @mbggenerated
     */
    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation == null ? null : evaluation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_evaluation.creater
     *
     * @return the value of cibr_sys_evaluation.creater
     *
     * @mbggenerated
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_evaluation.creater
     *
     * @param creater the value for cibr_sys_evaluation.creater
     *
     * @mbggenerated
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cibr_sys_evaluation.createTime
     *
     * @return the value of cibr_sys_evaluation.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cibr_sys_evaluation.createTime
     *
     * @param createtime the value for cibr_sys_evaluation.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}