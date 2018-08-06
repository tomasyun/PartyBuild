package www.dico.cn.partybuild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

//设置
public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void goback(View view) {
        this.finish();
    }

    public void logout(View view) {
        new AlertDialog(this).builder()
                .setCancelable(true)
                .setTitle("退出登录")
                .setMsg("退出登录可能会使你现有记录归零，确定退出?")
                .setPositiveButton("确定退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppManager.getManager().finishAllActivity();
                        Intent intent = new Intent(SettingActivity.this, SplashActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    //版本更新
    public void checkUpdate(View view) {

    }

    //密码更新
    public void passwordUpdate(View view) {
        Intent intent = new Intent(this, PwdupdateActivity.class);
        startActivity(intent);
    }
}
