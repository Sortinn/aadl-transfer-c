package com.transfer;

import com.google.common.collect.Maps;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 返回前端json结构
 * 
 * @author chenggangxu@sohu-inc.com Date: 2014/7/21 Time: 12:06
 */
public class ResultData implements Serializable {
    private static final long serialVersionUID = -2557899737321175514L;

    private boolean ret;
    private String errCode;
    private String errMsg;
    private Date currentTime = new Date();
    private Map<String, Object> data = Maps.newHashMap();

    public static ResultData builderSuccess() {
        ResultData resultData = new ResultData();
        resultData.setRet(Boolean.TRUE);
        return resultData;
    }

    public static ResultData builderFailure() {
        ResultData resultData = new ResultData();
        resultData.setRet(Boolean.FALSE);
        return resultData;
    }

    public static ResultData builderFailure(String errCode, String errMsg) {
        ResultData resultData = new ResultData();
        resultData.setRet(Boolean.FALSE);
        resultData.setErrCode(errCode);
        resultData.setErrMsg(errMsg);
        return resultData;
    }

    public static ResultData builderSuccess(Map<String, Object> data) {
        ResultData resultData = new ResultData();
        resultData.setRet(Boolean.TRUE);
        resultData.setData(data);
        return resultData;
    }

    public ResultData putItem(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
