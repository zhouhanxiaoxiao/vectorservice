package com.cibr.vectorservice.dao;

import com.cibr.vectorservice.entity.CibrConfigGroupsetting;
import com.cibr.vectorservice.entity.CibrConfigGroupsettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CibrConfigGroupsettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int countByExample(CibrConfigGroupsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int deleteByExample(CibrConfigGroupsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int insert(CibrConfigGroupsetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int insertSelective(CibrConfigGroupsetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    List<CibrConfigGroupsetting> selectByExample(CibrConfigGroupsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    CibrConfigGroupsetting selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CibrConfigGroupsetting record, @Param("example") CibrConfigGroupsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CibrConfigGroupsetting record, @Param("example") CibrConfigGroupsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CibrConfigGroupsetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_config_groupsetting
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CibrConfigGroupsetting record);
}