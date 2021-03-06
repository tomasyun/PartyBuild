package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.InfoAdapter;
import www.dico.cn.partybuild.bean.InfoBean;
import www.dico.cn.partybuild.bean.InfodetailForm;
import www.dico.cn.partybuild.modleview.PublicPromiseView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.PublicPromisePresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshLoadmoreListener;

@CreatePresenter(PublicPromisePresenter.class)
public class PublicPromiseActivity extends AbstractMvpActivity<PublicPromiseView, PublicPromisePresenter> implements PublicPromiseView {
    @BindView(R.id.rg_integrity_build)
    RadioGroup rg_integrity_build;
    @BindView(R.id.srl_public_promise)
    SmartRefreshLayout srl_public_promise;
    @BindView(R.id.rv_public_promise)
    RecyclerView rv_public_promise;
    @BindView(R.id.public_promise_empty_data)
    View public_promise_empty_data;
    @BindView(R.id.public_promise_net_error)
    View public_promise_net_error;
    private int start = 0;
    private int position = 0;
    private List<InfoBean.DataBeanX.DataBean> list;
    private InfoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicpromise);
        ButterKnife.bind(this);
        rv_public_promise.setLayoutManager(new LinearLayoutManager(this));
        rg_integrity_build.check(R.id.rbt_master_public_promise);
        rg_integrity_build.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.rbt_master_public_promise://党委承诺
                    position = 0;
                    start = 0;
                    createRequest(position, start);
                    break;
                case R.id.rbt_branch_public_promise://支部承诺
                    position = 1;
                    start = 0;
                    createRequest(position, start);
                    break;
                case R.id.rbt_member_public_promise://党员承诺
                    position = 2;
                    start = 0;
                    createRequest(position, start);
                    break;
            }
        });
        srl_public_promise.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                start = start + 10;
                createRequest(position, start);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start = 0;
                createRequest(position, start);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        createRequest(position, start);
    }

    public void goBackPublicPromise(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_public_promise.finishLoadmore();
        srl_public_promise.finishRefresh();
        InfoBean bean = new Gson().fromJson(result, InfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                if (start == 0) {
                    list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        srl_public_promise.setVisibility(View.VISIBLE);
                        public_promise_empty_data.setVisibility(View.GONE);
                        public_promise_net_error.setVisibility(View.GONE);
                        adapter = new InfoAdapter(this, R.layout.item_info, list);
                        rv_public_promise.setAdapter(adapter);
                        adapter.setOnItemClickListener((view, holder, position) -> {
                            InfodetailForm form = new InfodetailForm();
                            form.id = list.get(position).getId();
                            form.type = 1;
                            goTo(InfodetailsActivity.class, form);
                        });
                    } else {
                        srl_public_promise.setVisibility(View.GONE);
                        public_promise_empty_data.setVisibility(View.VISIBLE);
                        public_promise_net_error.setVisibility(View.GONE);
                    }
                } else {
                    List<InfoBean.DataBeanX.DataBean> list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        this.list.addAll(list);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener((view, holder, position) -> {
                            InfodetailForm form = new InfodetailForm();
                            form.id = PublicPromiseActivity.this.list.get(position).getId();
                            form.type = 1;
                            goTo(InfodetailsActivity.class, form);
                        });
                    } else {

                    }
                }
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
        srl_public_promise.finishLoadmore();
        srl_public_promise.finishRefresh();
    }

    @Override
    public void netWorkUnAvailable() {
        srl_public_promise.setVisibility(View.GONE);
        public_promise_empty_data.setVisibility(View.GONE);
        public_promise_net_error.setVisibility(View.VISIBLE);
        public_promise_net_error.setOnClickListener(view -> createRequest(position, start));
    }

    public void createRequest(int position, int start) {
        switch (position) {
            case 0:
                getMvpPresenter().doPublicPromiseRequest(dialog, "13", "44", "0", start, 10);
                break;
            case 1:
                getMvpPresenter().doPublicPromiseRequest(dialog, "13", "45", "0", start, 10);
                break;
            case 2:
                getMvpPresenter().doPublicPromiseRequest(dialog, "13", "46", "0", start, 10);
                break;
        }
    }
}
