package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.BaseInfoView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.BaseInfoBean;
import www.dico.cn.partybuild.presenter.BaseInfoPresenter;
//党员个人基本信息
@CreatePresenter(BaseInfoPresenter.class)
public class BaseInfoActivity extends AbstractMvpActivity<BaseInfoView, BaseInfoPresenter> implements BaseInfoView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseinfo);
        ViewFind.bind(this);
    }

    @Override
    public void resultSuccess(BaseInfoBean result) {

    }

    @Override
    public void resultFailure(String result) {

    }
}
