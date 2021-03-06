package com.mirkowu.lib_plugin.internal;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.mirkowu.lib_plugin.DLServicePlugin;
import com.mirkowu.lib_plugin.utils.DLConfigs;
import com.mirkowu.lib_plugin.utils.DLConstants;

import java.lang.reflect.Constructor;

public class DLServiceProxyImpl {

    private static final String TAG = "DLServiceProxyImpl";
    private Service mProxyService;
    private DLServicePlugin mRemoteService;

    public DLServiceProxyImpl(Service service) {
        mProxyService = service;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void init(Intent intent) {
        // set the extra's class loader
        intent.setExtrasClassLoader(DLConfigs.sPluginClassloader);

        String packageName = intent.getStringExtra(DLConstants.EXTRA_PACKAGE);
        String clazz = intent.getStringExtra(DLConstants.EXTRA_CLASS);
        Log.d(TAG, "clazz=" + clazz + " packageName=" + packageName);

        DLPluginManager pluginManager = DLPluginManager.getInstance(mProxyService);
        DLPluginPackage pluginPackage = pluginManager.getPackage(packageName);

        try {
            Class<?> localClass = pluginPackage.classLoader.loadClass(clazz);
            Constructor<?> localConstructor = localClass.getConstructor(new Class[] {});
            Object instance = localConstructor.newInstance(new Object[] {});
            mRemoteService = (DLServicePlugin) instance;
            ((DLServiceAttachable) mProxyService).attach(mRemoteService, pluginManager);
            Log.d(TAG, "instance = " + instance);
            // attach the proxy activity and plugin package to the
            // mPluginActivity
            mRemoteService.attach(mProxyService, pluginPackage);

            Bundle bundle = new Bundle();
            bundle.putInt(DLConstants.FROM, DLConstants.FROM_EXTERNAL);
            mRemoteService.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
