package com.david.mvpframework.ui.launch.been.request;

import com.david.mvpframework.baseBeen.BaseRequest;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */

public class AppUpdateRequest extends BaseRequest {
    private int version;
    private String versionName;
    private String appType;


    public AppUpdateRequest() {
        super();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    @Override
    public String toString() {
        return "AppUpdateRequest [version=" + version + ", versionName=" + versionName + "]";
    }
}
