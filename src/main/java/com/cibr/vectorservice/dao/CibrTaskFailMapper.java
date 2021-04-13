package com.cibr.vectorservice.dao;

import com.cibr.vectorservice.entity.CibrTaskFail;
import com.cibr.vectorservice.entity.CibrTaskFailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CibrTaskFailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int countByExample(CibrTaskFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int deleteByExample(CibrTaskFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int insert(CibrTaskFail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int insertSelective(CibrTaskFail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    List<CibrTaskFail> selectByExample(CibrTaskFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    CibrTaskFail selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CibrTaskFail record, @Param("example") CibrTaskFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CibrTaskFail record, @Param("example") CibrTaskFailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CibrTaskFail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_task_fail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CibrTaskFail record);
}