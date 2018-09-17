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
import www.dico.cn.partybuild.modleview.BranchParksView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.BranchParksPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshLoadmoreListener;

@CreatePresenter(BranchParksPresenter.class)
public class BranchParksActivity extends AbstractMvpActivity<BranchParksView, BranchParksPresenter> implements BranchParksView {
    @BindView(R.id.rg_branch_parks)
    RadioGroup rg_branch_parks;
    @BindView(R.id.srl_branch_parks)
    SmartRefreshLayout srl_branch_parks;
    @BindView(R.id.rv_branch_parks)
    RecyclerView rv_branch_parks;
    private int start = 0;
    private int length = 10;
    private int position = 0;
    private List<InfoBean.DataBeanX.DataBean> list;
    private InfoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branchparks);
        ButterKnife.bind(this);
        rg_branch_parks.check(R.id.rbt_notice_branch_parks);
        rg_branch_parks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_notice_branch_parks://通知公告
                        position = 0;
                        start = 0;
                        getMvpPresenter().doBranchParksRequest("31", "", "0", start, length);
                        break;
                    case R.id.rbt_activity_branch_parks://支部活动
                        position = 1;
                        start = 0;
                        getMvpPresenter().doBranchParksRequest("32", "", "0", start, length);
                        break;
                    case R.id.rbt_develop_branch_parks://发展党员
                        position = 2;
                        start = 0;
                        getMvpPresenter().doBranchParksRequest("33", "", "0", start, length);
                        break;
                    case R.id.rbt_logbook_branch_parks://各种台账
                        position = 3;
                        start = 0;
                        getMvpPresenter().doBranchParksRequest("34", "", "0", start, length);
                        break;
                }
            }
        });
        rv_branch_parks.setLayoutManager(new LinearLayoutManager(this));

        srl_branch_parks.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                start = start + length;
                switch (position) {
                    case 0:
                        getMvpPresenter().doBranchParksRequest("31", "", "0", start, length);
                        break;
                    case 1:
                        getMvpPresenter().doBranchParksRequest("32", "", "0", start, length);
                        break;
                    case 2:
                        getMvpPresenter().doBranchParksRequest("33", "", "0", start, length);
                        break;
                    case 3:
                        getMvpPresenter().doBranchParksRequest("34", "", "0", start, length);
                        break;
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start = 0;
                switch (position) {
                    case 0:
                        getMvpPresenter().doBranchParksRequest("31", "", "0", start, length);
                        break;
                    case 1:
                        getMvpPresenter().doBranchParksRequest("32", "", "0", start, length);
                        break;
                    case 2:
                        getMvpPresenter().doBranchParksRequest("33", "", "0", start, length);
                        break;
                    case 3:
                        getMvpPresenter().doBranchParksRequest("34", "", "0", start, length);
                        break;
                }
            }
        });

        getMvpPresenter().doBranchParksRequest("31", "", "0", start, length);
    }

    public void goBackBranchParks(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_branch_parks.finishLoadmore();
        srl_branch_parks.finishRefresh();
        InfoBean bean = new Gson().fromJson(result, InfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                if (start == 0) {
                    list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        adapter = new InfoAdapter(this, R.layout.item_info, list);
                        rv_branch_parks.setAdapter(adapter);
                        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                            }
                        });
                    } else {
                    }
                } else {
                    List<InfoBean.DataBeanX.DataBean> list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        this.list.addAll(list);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                            }
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
        srl_branch_parks.finishLoadmore();
        srl_branch_parks.finishRefresh();
        showToast(result);
    }
}