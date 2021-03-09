package com.mirkowu.lib_plugin.internal;

import android.content.Intent;
import android.os.Parcelable;

import com.mirkowu.lib_plugin.utils.DLConfigs;

import java.io.Serializable;

public class DLIntent extends Intent {



    private String mPluginPackage;
    private String mPluginClass;

    public DLIntent() {
        super();
    }

    public DLIntent(String pluginPackage) {
        super();
        this.mPluginPackage = pluginPackage;
    }

    public DLIntent(String pluginPackage, String pluginClass) {
        super();
        this.mPluginPackage = pluginPackage;
        this.mPluginClass = pluginClass;
    }

    public DLIntent(String pluginPackage, Class<?> clazz) {
        super();
        this.mPluginPackage = pluginPackage;
        this.mPluginClass = clazz.getName();
    }

    public String getPluginPackage() {
        return mPluginPackage;
    }

    public void setPluginPackage(String pluginPackage) {
        this.mPluginPackage = pluginPackage;
    }

    public String getPluginClass() {
        return mPluginClass;
    }

    public void setPluginClass(String pluginClass) {
        this.mPluginClass = pluginClass;
    }

    public void setPluginClass(Class<?> clazz) {
        this.mPluginClass = clazz.getName();
    }

    @Override
    public Intent putExtra(String name, Parcelable value) {
        setupExtraClassLoader(value);
        return super.putExtra(name, value);
    }

    @Override
    public Intent putExtra(String name, Serializable value) {
        setupExtraClassLoader(value);
        return super.putExtra(name, value);
    }

    private void setupExtraClassLoader(Object value) {
        ClassLoader pluginLoader = value.getClass().getClassLoader();
        DLConfigs.sPluginClassloader = pluginLoader;
        setExtrasClassLoader(pluginLoader);
    }

}