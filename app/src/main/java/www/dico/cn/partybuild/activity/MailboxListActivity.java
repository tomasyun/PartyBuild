package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.MailboxListAdapter;
import www.dico.cn.partybuild.bean.MailboxListBean;
import www.dico.cn.partybuild.modleview.MailboxListView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MailboxListPresenter;

@CreatePresenter(MailboxListPresenter.class)
public class MailboxListActivity extends AbstractMvpActivity<MailboxListView, MailboxListPresenter> implements MailboxListView {
    @BindView(R.id.rv_mailbox_list)
    RecyclerView rv_mailbox_list;
    private MailboxListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailboxlist);
        ButterKnife.bind(this);
        rv_mailbox_list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().doMailboxListRequest();
    }

    public void goBackMailboxList(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        MailboxListBean bean = new Gson().fromJson(result, MailboxListBean.class);
        if (bean.code.equals("0000")){
            if (null!=bean.getData()){
                List<MailboxListBean.DataBean> list=bean.getData();
                for (int i=0;i<list.size();i++){
                    adapter = new MailboxListAdapter(this, R.layout.item_mailbox, list);
                    rv_mailbox_list.setAdapter(adapter);
                }
            }
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
