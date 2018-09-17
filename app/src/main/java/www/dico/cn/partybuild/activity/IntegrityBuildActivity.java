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
import www.dico.cn.partybuild.modleview.IntegrityBuildView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.IntegrityBuildPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshLoadmoreListener;

@CreatePresenter(IntegrityBuildPresenter.class)
public class IntegrityBuildActivity extends AbstractMvpActivity<IntegrityBuildView, IntegrityBuildPresenter> implements IntegrityBuildView {
    @BindView(R.id.rg_integrity_build)
    RadioGroup rg_integrity_build;
    private int start = 0;
    private int length = 10;
    private int position = 0;
    private List<InfoBean.DataBeanX.DataBean> list;
    private InfoAdapter adapter;
    @BindView(R.id.rv_integrity_build)
    RecyclerView rv_integrity_build;
    @BindView(R.id.srl_integrity_build)
    SmartRefreshLayout srl_integrity_build;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integritybuild);
        ButterKnife.bind(this);
        rg_integrity_build.check(R.id.rbt_edu_integrity_build);
        rg_integrity_build.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_edu_integrity_build://廉政教育
                        position = 0;
                        start = 0;
                        getMvpPresenter().doIntegrityBuildRequest("9", "", "0", start, length);
                        break;
                    case R.id.rbt_prevent_integrity_build://监督预防
                        position = 1;
                        start = 0;
                        getMvpPresenter().doIntegrityBuildRequest("10", "", "0", start, length);
                        break;
                    case R.id.rbt_case_integrity_build://案例
                        position = 2;
                        start = 0;
                        getMvpPresenter().doIntegrityBuildRequest("11", "", "0", start, length);
                        break;
                }
            }
        });
        rv_integrity_build.setLayoutManager(new LinearLayoutManager(this));

        srl_integrity_build.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                start = start + length;
                switch (position) {
                    case 0:
                        getMvpPresenter().doIntegrityBuildRequest("9", "", "0", start, length);
                        break;
                    case 1:
                        getMvpPresenter().doIntegrityBuildRequest("10", "", "0", start, length);
                        break;
                    case 2:
                        getMvpPresenter().doIntegrityBuildRequest("11", "", "0", start, length);
                        break;
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start = 0;
                switch (position) {
                    case 0:
                        getMvpPresenter().doIntegrityBuildRequest("9", "", "0", start, length);
                        break;
                    case 1:
                        getMvpPresenter().doIntegrityBuildRequest("10", "", "0", start, length);
                        break;
                    case 2:
                        getMvpPresenter().doIntegrityBuildRequest("11", "", "0", start, length);
                        break;
                }
            }
        });

        getMvpPresenter().doIntegrityBuildRequest("9", "", "0", start, length);
    }

    public void goBackIntegrityBuild(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_integrity_build.finishLoadmore();
        srl_integrity_build.finishRefresh();
        InfoBean bean = new Gson().fromJson(result, InfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                if (start == 0) {
                    list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        adapter = new InfoAdapter(this, R.layout.item_info, list);
                        rv_integrity_build.setAdapter(adapter);
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
        srl_integrity_build.finishLoadmore();
        srl_integrity_build.finishRefresh();
        showToast(result);
    }
}
