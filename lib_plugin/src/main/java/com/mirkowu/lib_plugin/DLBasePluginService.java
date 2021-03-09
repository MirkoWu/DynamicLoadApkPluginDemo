package com.mirkowu.lib_plugin;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

import com.mirkowu.lib_plugin.internal.DLPluginPackage;
import com.mirkowu.lib_plugin.utils.DLConstants;
import com.mirkowu.lib_plugin.utils.LOG;

public class DLBasePluginService extends Service implements DLServicePlugin {

    public static final String TAG = "DLBasePluginService";
    private Service mProxyService;
    private DLPluginPackage mPluginPackage;
    protected Service that = this;
    protected int mFrom = DLConstants.FROM_INTERNAL;

    @Override
    public void attach(Service proxyService, DLPluginPackage pluginPackage) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " attach");
        mProxyService = proxyService;
        mPluginPackage = pluginPackage;
        that = mProxyService;
        mFrom = DLConstants.FROM_EXTERNAL;
    }

    protected boolean isInternalCall() {
        return mFrom == DLConstants.FROM_INTERNAL;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onBind");
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onStartCommand");
        return 0;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onTrimMemory");

    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onUnbind");
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onRebind");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        // TODO Auto-generated method stub
        LOG.d(TAG, TAG + " onTaskRemoved");
    }

}