package com.mirkowu.dynamicloadapkplugindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mirkowu.lib_plugin.DLProxyActivity;
import com.mirkowu.lib_plugin.internal.DLIntent;
import com.mirkowu.lib_plugin.internal.DLPluginManager;
import com.mirkowu.lib_plugin.utils.DLUtils;

import java.io.File;

public class ProxyActivity extends DLProxyActivity {

    private LinearLayout llList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        llList = findViewById(R.id.llList);
    }

    public void loadApk(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
            return;
        }

        File fileDir = new File(Environment.getExternalStorageDirectory() + File.separator + "PlanLife");
        File[] files = fileDir.listFiles();
        llList.removeAllViews();
        for (final File file : files) {
            String pluginPath = file.getAbsolutePath();
            if (file.getName().endsWith(".apk")) {
                TextView textView = new TextView(getBaseContext());
                final PackageInfo packageInfo = DLUtils.getPackageInfo(this, pluginPath);
                //加载 apk
                DLPluginManager.getInstance(getBaseContext()).loadApk(file.getAbsolutePath());

                textView.setText(file.getName());
                llList.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                        DLPluginManager pluginManager = DLPluginManager.getInstance(getBaseContext());

                        pluginManager.startPluginActivity(getBaseContext(), new DLIntent(packageInfo.packageName, packageInfo.activities[0].name));
                    }
                });
            }
        }


    }

    public void jumpMainActivity(View view) {

    }

    public void jumpTestFragmentActivity(View view) {
    }
}