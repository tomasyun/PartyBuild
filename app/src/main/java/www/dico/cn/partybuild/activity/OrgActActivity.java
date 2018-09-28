package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.OrgActAdapter;
import www.dico.cn.partybuild.bean.OrgActBean;
import www.dico.cn.partybuild.bean.OrgActForm;
import www.dico.cn.partybuild.modleview.OrgActView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OrgActPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshListener;

@CreatePresenter(OrgActPresenter.class)
public class OrgActActivity extends AbstractMvpActivity<OrgActView, OrgActPresenter> implements OrgActView {
    @BindView(R.id.rv_org_act)
    RecyclerView rv_org_act;
    @BindView(R.id.org_act_empty_data)
    View org_act_empty_data;
    @BindView(R.id.org_act_net_error)
    View org_act_net_error;
    @BindView(R.id.srl_org_act)
    SmartRefreshLayout srl_org_act;
    private OrgActAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgact);
        ButterKnife.bind(this);
        rv_org_act.setLayoutManager(new LinearLayoutManager(this));
        srl_org_act.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMvpPresenter().doOrgActRequest(dialog);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().doOrgActRequest(dialog);
    }

    public void goBackOrgAct(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_org_act.finishRefresh();
        OrgActBean bean = new Gson().fromJson(result, OrgActBean.class);
        if (bean.code.equals("0000")) {
            final List<OrgActBean.DataBean> list = bean.getData();
            if (null != list && list.size() > 0) {
                srl_org_act.setVisibility(View.VISIBLE);
                org_act_empty_data.setVisibility(View.GONE);
                org_act_net_error.setVisibility(View.GONE);
                adapter = new OrgActAdapter(this, R.layout.item_meeting, list);
                rv_org_act.setAdapter(adapter);
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        OrgActForm form = new OrgActForm();
                        form.orgActId = list.get(position).getId();
                        form.state = list.get(position).getState();
                        goTo(OrgActBriefActivity.class, form);
                    }
                });
            } else {
                //空白
                srl_org_act.setVisibility(View.GONE);
                org_act_empty_data.setVisibility(View.VISIBLE);
                org_act_net_error.setVisibility(View.GONE);
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        srl_org_act.finishRefresh();
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        srl_org_act.setVisibility(View.GONE);
        org_act_empty_data.setVisibility(View.GONE);
        org_act_net_error.setVisibility(View.VISIBLE);
        org_act_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMvpPresenter().doOrgActRequest(dialog);
            }
        });
    }
}
