package com.david.mvpframework.ui.launch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.david.mvpframework.R;
import com.david.mvpframework.app.SmartSDNApplication;
import com.david.mvpframework.app.SmartSDNComponent;
import com.david.mvpframework.common.Constant;
import com.david.mvpframework.ui.base.MvpActivity;
import com.david.mvpframework.ui.launch.been.request.AppUpdateRequest;
import com.david.mvpframework.ui.launch.been.response.AppUpdateResponse;
import com.david.mvpframework.ui.launch.component.DaggerLaunchComponent;
import com.david.mvpframework.ui.launch.module.LaunchModule;
import com.david.mvpframework.ui.launch.module.LaunchServiceModule;
import com.david.mvpframework.ui.launch.presenter.AppVersionUpdatePresenter;
import com.david.mvpframework.ui.launch.view.AppUpdateView;
import com.david.mvpframework.ui.user.activitys.UserLoginActivity;
import com.david.mvpframework.utils.FileUtils;
import com.david.mvpframework.utils.MySharedPreferences;
import com.david.mvpframework.utils.PermissionUtils;
import com.david.mvpframework.utils.SmartSDNLoger;
import com.david.mvpframework.utils.SystemTool;
import com.david.mvpframework.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 启动页面，主要功能
 */
public class LauncherActivity extends MvpActivity<AppVersionUpdatePresenter> implements AppUpdateView {

    int fileSize;
    int downLoadFileSize;
    String fileEx, fileNa, filename;
    private AlertDialog dialog;
    private AlertDialog downloadDialog;

    //@Inject
    //AppVersionUpdatePresenter presenter;

    @Inject
    MySharedPreferences preferences;

    ////////////////////////////////////////////////
//    @InjectView(R.id.progressBar)
//    ProgressBar progressBar;
//    @InjectView(R.id.textView)
//    TextView textView;

    ProgressBar progress_bar;
    TextView text_view;
    Button app_update_progress_confirm;

    //申请权限
    private static final int REQUEST_CODE = 1000; // 请求码
    private static final int PERMISSION_REQUEST_CODE = 10001;        // 系统权限返回码
    private boolean isRequireCheck = true; // 是否需要系统权限检测
    //危险权限（运行时权限）
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    //用来接收线程发送来的文件下载量，进行UI界面的更新
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {//定义一个Handler，用于处理下载线程与UI间通讯
            if (!Thread.currentThread().isInterrupted()) {
                switch (msg.what) {
                    case 0:
                        //progressBar.setMax(fileSize);
                        progress_bar.setMax(fileSize);
                    case 1:
                        //progressBar.setProgress(downLoadFileSize);
                        progress_bar.setProgress(downLoadFileSize);
                        int result = downLoadFileSize * 100 / fileSize;
                        //textView.setText(result + "%");
                        text_view.setText(result + "%");
                        break;
                    case 2:
                        SmartSDNLoger.debug("===============" + "文件下载完成");
                        //安卓APP
                        SystemTool.installApk(getApplicationContext(), new File(FileUtils.getSavePath(Constant.SmartSDNFile.APP_PATH) + filename));
                        break;
                    case -1:
                        String error = msg.getData().getString("error");
                        Toast.makeText(LauncherActivity.this, error, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        SmartSDNComponent component = ((SmartSDNApplication) getApplication()).getSmartSDNComponent();
        DaggerLaunchComponent.builder().smartSDNComponent(component)
                .launchModule(new LaunchModule(this))
                .launchServiceModule(new LaunchServiceModule())
                .build()
                .inject(this);
        preferences.putString("xuxu", "xuqinjun");
        SmartSDNLoger.debug("=================" + preferences.getString("xuxu", null));
        //TODO APP升级
        queryAppUpdate();

        //判断版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查系统是否有照相机权限
            PermissionUtils permissionUtils = new PermissionUtils(this);
            if (isRequireCheck) {
                //权限没有授权，进入授权界面
                if (permissionUtils.judgePermissions(PERMISSIONS)) {
                    ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
                }
            } else {
                isRequireCheck = true;
            }
        }
    }

    @Override
    public void initViewData() {

    }

    @Override
    public void showLoading() {
        SmartSDNLoger.debug("=======开始dialog======");
    }

    @Override
    public void hideLoading() {
        SmartSDNLoger.debug("=======关闭dialog======");
    }

    @Override
    public void onSuccess(AppUpdateResponse response) {
        if (response.getUpdateInfo() == null) {//判断是否为null
            startOperationAct();
            return;
        }
        switch (response.getUpdateInfo().getState()) {
            case Constant.AppUpdate.UPDATE_NO://没有升级
                startOperationAct();
                break;
            case Constant.AppUpdate.UPDATE_ORDINARY://一般升级
                //showUpdateDialog(Constant.HttpURL.BASE_URL, response.getUpdateInfo().getAppPath(), response.getUpdateInfo().getState());
                break;
            case Constant.AppUpdate.UPDATE_COERCE://强制升级
                //showUpdateDialog(Constant.HttpURL.BASE_URL, response.getUpdateInfo().getAppPath(), response.getUpdateInfo().getState());
                break;
            default:
        }
    }

    @Override
    public void onFailure(String message) {
        startOperationAct();
    }

    private void queryAppUpdate() {
        AppUpdateRequest request = new AppUpdateRequest();
        request.setAppType("android");
        request.setVersion(SystemTool.getAppVersionCode(this));
        request.setVersionName(SystemTool.getAppVersionName(this));
        presenter.queryAppUpdae(request);
    }

    /**
     * 升级对话框
     */
    private void showAppUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_app_update_progress, null);
        //TODO
        progress_bar = (ProgressBar) contentView.findViewById(R.id.progress_bar);
        text_view = (TextView) contentView.findViewById(R.id.text_view);
        app_update_progress_confirm = (Button) contentView.findViewById(R.id.app_update_progress_confirm);
        builder.setView(contentView);
        builder.setCancelable(false);
        //builder.setMessage("下载进度");
        downloadDialog = builder.create();
        downloadDialog.show();
        app_update_progress_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(-1);
            }
        });
    }

    /**
     * APP升级提示框
     *
     * @param url
     * @param appName
     * @param state
     */
    private void showUpdateDialog(final String url, final String appName, final int state) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.add_dialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_app_update, null);
        TextView app_update_describe_text = (TextView) contentView.findViewById(R.id.app_update_describe_text);
        Button app_update_cancel_btn = (Button) contentView.findViewById(R.id.app_update_cancel_btn);
        Button app_update_confirm_btn = (Button) contentView.findViewById(R.id.app_update_confirm_btn);
        builder.setView(contentView);
        builder.setCancelable(false);
        app_update_describe_text.setText(state == 1 ? "APP已有新版本,请升级到最新使用" : "此版本已经不能使用，请升级");
        app_update_cancel_btn.setText(state == 1 ? "取消" : "退出");
        app_update_confirm_btn.setText("确认");
        app_update_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //退出系统
                if (state == 1) {
                    startOperationAct();
                } else {
                    System.exit(-1);
                }
            }
        });
        app_update_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //弹出文件下载进度条
                    showAppUpdate();
                    //启动线程，开始下载文件
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                downFile(url, FileUtils.getSavePath(Constant.SmartSDNFile.APP_PATH), appName);  //在下面的代码段
                            } catch (IOException e) {
                                e.printStackTrace();
                                ToastUtils.show(LauncherActivity.this, "未找到升级文件，请取消升级");
                            }
                        }
                    }).start();
                } else {
                    ToastUtils.show(LauncherActivity.this, "SD卡不可用，请插入SD卡");
                }
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    /**
     * 跳转到下一个页面
     */
    private void startOperationAct() {
        Intent intent = new Intent(LauncherActivity.this, UserLoginActivity.class);
        startActivity(intent);
        LauncherActivity.this.finish();
    }

    /**
     * 文件下载
     *
     * @param url：文件的下载地址
     * @param path：文件保存到本地的地址
     * @throws IOException
     */
    public void downFile(String url, String path, String appName) throws IOException {
        //下载函数
        filename = appName; // + ".apk";//url.substring(url.lastIndexOf("/") + 1);
        url = url + appName;
        SmartSDNLoger.debug("=======URL=url=======" + url);
        //获取文件名
        URL myURL = new URL(url);
        URLConnection conn = myURL.openConnection();
        conn.connect();
        InputStream is = conn.getInputStream();
        this.fileSize = conn.getContentLength();//根据响应获取文件大小
        if (this.fileSize <= 0) throw new RuntimeException("无法获知文件大小 ");
        if (is == null) throw new RuntimeException("stream is null");
        File file1 = new File(path);
        File file2 = new File(path + filename);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(path + filename);
        //把数据存入路径+文件名
        byte buf[] = new byte[1024];
        downLoadFileSize = 0;
        sendMsg(0);
        do {
            //循环读取
            int numread = is.read(buf);
            if (numread == -1) {
                break;
            }
            fos.write(buf, 0, numread);
            downLoadFileSize += numread;
            sendMsg(1);//更新进度条
        } while (true);

        sendMsg(2);//通知下载完成

        try {
            is.close();
        } catch (Exception ex) {
            Log.e("tag", "error: " + ex.getMessage(), ex);
        }

    }

    //在线程中向Handler发送文件的下载量，进行UI界面的更新
    private void sendMsg(int flag) {
        Message msg = new Message();
        msg.what = flag;
        handler.sendMessage(msg);
    }


    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            isRequireCheck = true;
        } else {
            isRequireCheck = false;
            startAppSettings();
        }
    }

    // 含有全部的权限
    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SmartSDNLoger.debug("requestCode: " + requestCode);
        SmartSDNLoger.debug("resultCode: " + resultCode);
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= 23) {
                //检查系统是否有照相机权限
                PermissionUtils permissionUtils = new PermissionUtils(this);
                SmartSDNLoger.debug("======PERMISSIONS====" + permissionUtils.judgePermissions(PERMISSIONS));
                if (permissionUtils.judgePermissions(PERMISSIONS)) {
                    ToastUtils.show(this, "您没有开启存储卡读写权限，部分功能可能无法正常使用");
                }
            }
        }
    }

    // 启动应用的设置
    private void startAppSettings() {
        //Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE);
    }
}
