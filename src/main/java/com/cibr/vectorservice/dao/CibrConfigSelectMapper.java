package com.cibr.vectorservice.dao;

import com.cibr.vectorservice.entity.CibrConfigSelect;
import com.cibr.vectorservice.entity.CibrConfigSelectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CibrConfigSelectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int countByExample(CibrConfigSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int deleteByExample(CibrConfigSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int insert(CibrConfigSelect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int insertSelective(CibrConfigSelect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    List<CibrConfigSelect> selectByExample(CibrConfigSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    CibrConfigSelect selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CibrConfigSelect record, @Param("example") CibrConfigSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CibrConfigSelect record, @Param("example") CibrConfigSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CibrConfigSelect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_select
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CibrConfigSelect record);
}