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
import www.dico.cn.partybuild.adapter.MailboxListAdapter;
import www.dico.cn.partybuild.bean.MailboxListBean;
import www.dico.cn.partybuild.modleview.MailboxListView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
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
        adapter = new MailboxListAdapter(this, R.layout.item_mailbox, mailboxs());
        rv_mailbox_list.setAdapter(adapter);
    }

    public void goBackMailboxList(View view) {
        this.finish();
    }

    public List<MailboxListBean> mailboxs() {
        List<MailboxListBean> list = new ArrayList<>();
        MailboxListBean bean = new MailboxListBean();
        bean.setName("席沛锋");
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
