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

@CreatePresenter(OrgActPresenter.class)
public class OrgActActivity extends AbstractMvpActivity<OrgActView, OrgActPresenter> implements OrgActView {
    @BindView(R.id.rv_org_act)
    RecyclerView rv_org_act;
    private OrgActAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgact);
        ButterKnife.bind(this);
        rv_org_act.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().doOrgActRequest();
    }

    public void goBackOrgAct(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        OrgActBean bean = new Gson().fromJson(result, OrgActBean.class);
        if (bean.code.equals("0000")) {
            final List<OrgActBean.DataBean> list = bean.getData();
            if (null != list && list.size() > 0) {
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
            }
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
