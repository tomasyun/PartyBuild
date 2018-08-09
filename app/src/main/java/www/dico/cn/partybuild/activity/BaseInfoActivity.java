package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseInfoBean;
import www.dico.cn.partybuild.modleview.BaseInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.BaseInfoPresenter;

//党员个人基本信息
@CreatePresenter(BaseInfoPresenter.class)
public class BaseInfoActivity extends AbstractMvpActivity<BaseInfoView, BaseInfoPresenter> implements BaseInfoView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseinfo);
        ButterKnife.bind(this);
        // getMvpPresenter().getBaseInfoRequest("");
    }

    public void goBackBaseInfo(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        BaseInfoBean bean = new Gson().fromJson(result, BaseInfoBean.class);
        if (bean.code.equals("0000")) {

        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
