package com.cibr.vectorservice.util;

import java.util.Map;

public class ReturnData {
    private String code;
    private Map retMap;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map getRetMap() {
        return retMap;
    }

    public void setRetMap(Map retMap) {
        this.retMap = retMap;
    }
}
