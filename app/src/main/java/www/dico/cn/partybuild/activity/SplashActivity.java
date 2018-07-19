package www.dico.cn.partybuild.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leo618.mpermission.AfterPermissionGranted;
import com.leo618.mpermission.MPermission;

import java.util.List;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.MainActivity;
import www.dico.cn.partybuild.R;

public class SplashActivity extends AppCompatActivity implements MPermission.PermissionCallbacks {
    private static final int PERMISSION = 123;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (AppConfig.getSpUtils().getInt("isLoginOk") == 1) {
                        startActivity(new Intent().setClass(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
//                        startActivity(new Intent().setClass(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        permissionTask();
    }

    @AfterPermissionGranted(PERMISSION)
    public void permissionTask() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (MPermission.hasPermissions(this,perms)) {//检查是否已经授权
            mHandler.sendEmptyMessageDelayed(0, 3000);
        } else {
            MPermission.requestPermissions(this, "###", PERMISSION,perms);//请求获取权限
        }
    }

    /**
     * 请求权限结果处理
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("SplashActivity", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("SplashActivity", "onPermissionsDenied:" + requestCode + ":" + perms.size());
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }
}
