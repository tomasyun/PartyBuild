package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.MeetingAdapter;
import www.dico.cn.partybuild.bean.MeetingBean;
import www.dico.cn.partybuild.modleview.MeetingView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MeetingPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

@CreatePresenter(MeetingPresenter.class)
public class MeetingActivity extends AbstractMvpActivity<MeetingView, MeetingPresenter> implements MeetingView {
    @FieldView(R.id.rv_meeting)
    RecyclerView rv_meeting;
    private MeetingAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ViewFind.bind(this);
        rv_meeting.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingAdapter(this, R.layout.item_meeting, meetings());
        rv_meeting.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(MeetingBriefActivity.class, null);
            }
        });
    }

    public void goback(View view) {
        this.finish();
    }

    private List<MeetingBean> meetings() {
        List<MeetingBean> list = new ArrayList<>();
        MeetingBean bean = new MeetingBean();
        bean.setState("");
        bean.setDate("2018-7-29 13:00");
        bean.setTitle("");
        list.add(bean);
        return list;
    }

    @Override
    public void resultSuccess(MeetingBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
