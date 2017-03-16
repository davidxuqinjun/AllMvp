package com.david.mvpframework.common;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */
public interface Constant {
    interface HttpURL {
        //Base 地址
        String BASE_URL = "http://xxxxxxxxxxxxx";
    }

    interface HttpTime {
        int READ_TIME_OUT = 10000;
        int CONNECT_TIME_OUT = 10000;
        int WRITE_TIME_OUT = 10000;
    }

    interface SmartSDNFile {
        //存储APP的地址
        String APP_PATH = "/smart/app/";
    }

    /**
     * APP更新常量
     */
    interface AppUpdate {
        int UPDATE_NO = 0;//没有新版本
        int UPDATE_ORDINARY = 1;//一般更新 ordinary
        int UPDATE_COERCE = 2;//强制更新
    }

    interface EncodePassword {
        String passwordKye = "123456";
    }
}
