package com.david.mvpframework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/22.
 * 邮箱：342211385@qq.com
 */

public class MySharedPreferences {

    private Context context;
    private SharedPreferences preferences;
    private static final String CONFIG_MANAGER = "config_manager";

    @Inject
    public MySharedPreferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(CONFIG_MANAGER, Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     *
     * @param key    保存数据的标识
     * @param values 所保存数据的值
     */
    public void putString(String key, String values) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, values);
        editor.commit();
    }

    /**
     * 获取数据
     *
     * @param key       获取数据的key值
     * @param defValues 默认值
     * @return
     */
    public String getString(String key, String defValues) {
        preferences = context.getSharedPreferences(CONFIG_MANAGER, Context.MODE_PRIVATE);
        return preferences.getString(key, defValues);
    }

    /**
     * 保存数据
     *
     * @param key    保存数据的标识
     * @param values 所保存数据的值
     */
    public void putInt(String key, int values) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, values);
        editor.commit();
    }

    /**
     * 获取数据
     *
     * @param key       获取数据的key值
     * @param defValues 默认值
     * @return
     */
    public int getInt(String key, int defValues) {
        preferences = context.getSharedPreferences(CONFIG_MANAGER, Context.MODE_PRIVATE);
        return preferences.getInt(key, defValues);
    }

    /**
     * 保存数据
     *
     * @param key    保存数据的标识
     * @param values 所保存数据的值
     */
    public void putBoolean(String key, boolean values) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, values);
        editor.commit();
    }

    /**
     * 获取数据
     *
     * @param key       获取数据的key值
     * @param defValues 默认值
     * @return
     */
    public boolean getBoolean(String key, boolean defValues) {
        preferences = context.getSharedPreferences(CONFIG_MANAGER, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defValues);
    }
}
