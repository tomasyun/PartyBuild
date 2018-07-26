package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.SettingView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.SettingPresenter;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

//设置
@CreatePresenter(SettingPresenter.class)
public class SettingActivity extends AbstractMvpActivity<SettingView, SettingPresenter> implements SettingView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ViewFind.bind(this);
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
                        goTo(SplashActivity.class, null);
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
        goTo(PwdupdateActivity.class, null);
    }
}
