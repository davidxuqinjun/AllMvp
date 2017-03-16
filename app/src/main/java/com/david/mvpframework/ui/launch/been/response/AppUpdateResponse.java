package com.david.mvpframework.ui.launch.been.response;

import com.david.mvpframework.baseBeen.BaseResponse;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */

public class AppUpdateResponse extends BaseResponse {
    private AppUpdateInfo updateInfo;

    public AppUpdateResponse() {

    }

    public AppUpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(AppUpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
    }

    public static class AppUpdateInfo {
        private int state;//返回APP更新状态  0：不跟新，版本相同， 1：可更新  2：强制更新
        private String appPath;//新APP路径

        public AppUpdateInfo() {
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getAppPath() {
            return appPath;
        }

        public void setAppPath(String appPath) {
            this.appPath = appPath;
        }

        @Override
        public String toString() {
            return "AppUpdateResponse{" +
                    "state=" + state +
                    ", appPath='" + appPath + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppUpdateResponse{" +
                "updateInfo=" + updateInfo +
                '}';
    }
}
