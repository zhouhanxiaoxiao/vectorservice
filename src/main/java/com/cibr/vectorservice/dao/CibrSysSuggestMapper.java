package com.cibr.vectorservice.dao;

import com.cibr.vectorservice.entity.CibrSysSuggest;
import com.cibr.vectorservice.entity.CibrSysSuggestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CibrSysSuggestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int countByExample(CibrSysSuggestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int deleteByExample(CibrSysSuggestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int insert(CibrSysSuggest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int insertSelective(CibrSysSuggest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    List<CibrSysSuggest> selectByExample(CibrSysSuggestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    CibrSysSuggest selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CibrSysSuggest record, @Param("example") CibrSysSuggestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CibrSysSuggest record, @Param("example") CibrSysSuggestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CibrSysSuggest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cibr_sys_suggest
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CibrSysSuggest record);

    List<CibrSysSuggest> findComments(@Param("detailId") String detailId);
}