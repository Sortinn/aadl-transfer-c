package com.transfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 返回前端json结构
 *
 * @author zww.zhang
 */
public class JsonResult<T> implements Serializable {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 是否成功
     */
    private Boolean ret = Boolean.TRUE;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * 错误码
     */
    private Integer errcode;
    /**
     * 版本
     */
    private Integer ver;
    /**
     * 数据
     */
    private T data;

    /**
     * 当前服务器时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp currentTime = new Timestamp(System.currentTimeMillis());

    public JsonResult() {
        this.errmsg = "";
        this.errcode = 0;
        this.ver = 1;
    }

    public static <T> JsonResult successJsonResult(T data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult successJsonResult() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setRet(true);
        return jsonResult;
    }

    public static <T> JsonResult failureJsonResult(String errmsg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setErrmsg(errmsg);
        jsonResult.setRet(Boolean.FALSE);
        return jsonResult;
    }

    public static <T> JsonResult failureJsonResult(Integer errcode,String errmsg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setErrmsg(errmsg);
        jsonResult.setErrcode( errcode );
        jsonResult.setRet(Boolean.FALSE);
        return jsonResult;
    }
    public JsonResult(Boolean ret, String errmsg, T data) {
        super();
        this.ret = ret;
        this.errmsg = errmsg;
        this.data = data;
    }

    public JsonResult(Boolean ret, String errmsg, Integer errcode, T data) {
        super();
        this.ret = ret;
        this.errmsg = errmsg;
        this.errcode = errcode;
        this.data = data;
    }

    public Boolean getRet() {
        return ret;
    }

    public void setRet(Boolean ret) {
        this.ret = ret;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
