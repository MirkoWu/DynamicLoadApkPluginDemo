package com.mirkowu.plugin_a;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mirkowu.lib_plugin.DLBasePluginActivity;
import com.mirkowu.lib_plugin.internal.DLIntent;

public class MainActivity extends DLBasePluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        that.setContentView(R.layout.activity_main);
        that.setContentView(generateContentView(that));

     //   initView();
    }

    private View generateContentView(final Context context) {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Button button = new Button(context);
        button.setText("Toast");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "这是插件Activity", Toast.LENGTH_SHORT).show();
            }
        });

        layout.addView(button, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Button button2 = new Button(context);
        button2.setText("Jump");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpFragmentActivity(v);
            }
        });

        layout.addView(button2, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return layout;


    }

//    private void initView() {
//        Button btnToast = (Button) findViewById(R.id.btnToast);
//        Button btnJumpFragmentAct = (Button) findViewById(R.id.btnJumpFragmentAct);
//        btnToast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                clickToast(v);
//                Toast.makeText(that, "这是插件Activity", Toast.LENGTH_SHORT).show();
//            }
//        });
//        btnJumpFragmentAct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jumpFragmentActivity(v);
//            }
//        });
//
//    }

    public void clickToast(View view) {
        Toast.makeText(that, "这是插件Activity", Toast.LENGTH_SHORT).show();
    }

    public void jumpFragmentActivity(View view) {
        startPluginActivity(new DLIntent(getPackageName(), TestFragmentActivity.class));
    }

    public void jumpActivity(View view) {
        startPluginActivity(new DLIntent(getPackageName(), TestActivity.class));
//        startActivity(new Intent(getBaseContext(),TestActivity.class));
    }
}