package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.SettingView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.SettingPresenter;

@CreatePresenter(SettingPresenter.class)
public class SettingActivity extends AbstractMvpActivity<SettingView, SettingPresenter> implements SettingView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ViewFind.bind(this);
    }
}
