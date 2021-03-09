package com.mirkowu.lib_plugin.internal;

import com.mirkowu.lib_plugin.DLPlugin;

public interface DLAttachable {
    /**
     * when the proxy impl ( {@see DLProxyImpl#launchTargetActivity()} ) launch
     * the plugin activity , dl will call this method to attach the proxy activity
     * and pluginManager to the plugin activity. the proxy activity will load
     * the plugin's resource, so the proxy activity is a resource delegate for
     * plugin activity.
     *
     * @param proxyActivity a instance of DLPlugin, {@see DLBasePluginActivity}
     *            and {@see DLBasePluginFragmentActivity}
     * @param pluginManager DLPluginManager instance, manager the plugins
     */
    public void attach(DLPlugin proxyActivity, DLPluginManager pluginManager);
}