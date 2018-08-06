package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.StudyTaskAdapter;
import www.dico.cn.partybuild.bean.StudyTaskBean;
import www.dico.cn.partybuild.modleview.StudyTaskView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.StudyTaskPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

@CreatePresenter(StudyTaskPresenter.class)
public class StudyTaskActivity extends AbstractMvpActivity<StudyTaskView, StudyTaskPresenter> implements StudyTaskView {
    @FieldView(R.id.rv_study_task)
    RecyclerView rv_study_task;
    private StudyTaskAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studytask);
        ViewFind.bind(this);
        rv_study_task.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudyTaskAdapter(this, R.layout.item_study_task, tasks());
        rv_study_task.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(TaskBriefActivity.class, null);
            }
        });
    }

    public void goback(View view) {
        finish();
    }


    public List<StudyTaskBean> tasks() {
        List<StudyTaskBean> list = new ArrayList<>();
        StudyTaskBean bean = new StudyTaskBean();
        bean.setTitle("新形势下党内政治生活");
        list.add(bean);
        return list;
    }

    @Override
    public void resultSuccess(StudyTaskBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
