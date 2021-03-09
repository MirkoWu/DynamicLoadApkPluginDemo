package com.mirkowu.lib_plugin.internal;

import com.mirkowu.lib_plugin.DLServicePlugin;

public interface DLServiceAttachable {

    public void attach(DLServicePlugin remoteService, DLPluginManager pluginManager);
}
