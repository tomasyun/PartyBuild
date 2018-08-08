package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.VoteListAdapter;
import www.dico.cn.partybuild.bean.VoteListBean;
import www.dico.cn.partybuild.modleview.VoteManagerView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.VoteManagerPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

@CreatePresenter(VoteManagerPresenter.class)
public class VoteManagerActivity extends AbstractMvpActivity<VoteManagerView, VoteManagerPresenter> implements VoteManagerView {
    @FieldView(R.id.rv_vote)
    RecyclerView rv_vote;
    private VoteListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votemanager);
        ViewFind.bind(this);
        rv_vote.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VoteListAdapter(this, R.layout.item_vote, votes());
        rv_vote.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(VoteDetailActivity.class, null);
            }
        });
    }

    public void goback(View view) {
        this.finish();
    }

    public List<VoteListBean> votes() {
        List<VoteListBean> list = new ArrayList<>();
        VoteListBean bean = new VoteListBean();
        bean.setTitle("党委书记党内初选投票");
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
