package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.OrgActAdapter;
import www.dico.cn.partybuild.bean.OrgActBean;
import www.dico.cn.partybuild.modleview.OrgActView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OrgActPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

@CreatePresenter(OrgActPresenter.class)
public class OrgActActivity extends AbstractMvpActivity<OrgActView, OrgActPresenter> implements OrgActView {
    private OrgActAdapter adapter;
    @BindView(R.id.rv_org_act)
    RecyclerView rv_org_act;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgact);
        ButterKnife.bind(this);
        rv_org_act.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrgActAdapter(this, R.layout.item_meeting, orgActs());
        rv_org_act.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(OrgActBriefActivity.class, null);
            }
        });
    }

    public void goBackOrgAct(View view) {
        this.finish();
    }

    public List<OrgActBean> orgActs() {
        List<OrgActBean> list = new ArrayList<>();
        OrgActBean bean = new OrgActBean();
        bean.setState("");
        bean.setDate("2018-7-29 13:00");
        bean.setTitle("");
        list.add(bean);
        return list;
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
