package com.david.mvpframework.baseBeen;

/**
 * Created by Josh on 8/28/16.
 */
public class BaseResponse {
    public boolean status = false;
    public String errorCode;
    public String message;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
