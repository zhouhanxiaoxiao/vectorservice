package com.cibr.vectorservice.dao;

import com.cibr.vectorservice.entity.CibrSysEvaluation;
import com.cibr.vectorservice.entity.CibrSysEvaluationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CibrSysEvaluationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int countByExample(CibrSysEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int deleteByExample(CibrSysEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int insert(CibrSysEvaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int insertSelective(CibrSysEvaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    List<CibrSysEvaluation> selectByExample(CibrSysEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    CibrSysEvaluation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CibrSysEvaluation record, @Param("example") CibrSysEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CibrSysEvaluation record, @Param("example") CibrSysEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CibrSysEvaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_evaluation
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CibrSysEvaluation record);
}