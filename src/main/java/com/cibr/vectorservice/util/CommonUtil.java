package com.cibr.vectorservice.util;

import com.cibr.vectorservice.entity.CibrSysRole;
import com.cibr.vectorservice.entity.CibrSysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CommonUtil {

    public static String UUID() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }


    /**
     * 是否是Ajax异步请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request)
    {

        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1)
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        {
            return true;
        }

        return false;
    }

    public static String replaceSpecialChar(String name) {
        if (name == null){
            return "";
        }
        return name.replace("(","")
                .replace(")","")
                .replace("（","")
                .replace("）","");
    }

    /**
     * 判断用户角色
     *
     * @param user
     * @return
     *  normal : 普通组员用户
     *  systemAdmin : 系统管理员
     *  pi : 实验室任务处理人
     */
    public static String isGroupViewer(CibrSysUser user) {
        String isAdmin = "normal";
        for (CibrSysRole role : user.getRoles()){
            if ("999999".equals(role.getRoletype())){
                isAdmin = "systemAdmin";
                break;
            }
            if ("21".equals(role.getRoletype()) || "40".equals(role.getRoletype())){
                isAdmin = "pi";
            }
        }
        return isAdmin;
    }
}
