package cn.rongcloud.live.util.base;

/**
 * Created by quchwe on 2017/4/24 0024.
 *
 * http请求返回基类
 */


public class BaseResponse<T> {

    public static final String SUCCESS_CODE = "0";
    public static final String ERR_CODE = "-1";
    private String errCode;
    private String errMsg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResponse(){
        this.errCode = ERR_CODE;
        this.errMsg = "error";
    }
    public BaseResponse(T data){
        this.errCode = SUCCESS_CODE;
        this.errMsg = "success";
        this.data = data;
    }
}
