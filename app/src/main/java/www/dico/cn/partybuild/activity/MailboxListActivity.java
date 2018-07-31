package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.MailboxListView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MailboxListPresenter;

@CreatePresenter(MailboxListPresenter.class)
public class MailboxListActivity extends AbstractMvpActivity<MailboxListView, MailboxListPresenter> implements MailboxListView {
    @FieldView(R.id.rv_mailbox_list)
    RecyclerView rv_mailbox_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailboxlist);
        ViewFind.bind(this);
        rv_mailbox_list.setLayoutManager(new LinearLayoutManager(this));
    }
    public void goback(View view){
        this.finish();
    }
}
