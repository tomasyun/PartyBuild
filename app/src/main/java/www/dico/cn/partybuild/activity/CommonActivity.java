package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.CommonView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CommonPresenter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;

@CreatePresenter(CommonPresenter.class)
public class CommonActivity extends AbstractMvpActivity<CommonView, CommonPresenter> implements CommonView{
    @BindView(R.id.srl_common)
    SmartRefreshLayout srl_common;
    @BindView(R.id.rv_common)
    RecyclerView rv_common;
    @BindView(R.id.common_empty_data)
    View common_empty_data;
    @BindView(R.id.common_net_error)
    View common_net_error;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);

        rv_common.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {

    }

    @Override
    public void netWorkUnAvailable() {

    }
}
