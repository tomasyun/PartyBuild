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
import www.dico.cn.partybuild.adapter.StudyTaskAdapter;
import www.dico.cn.partybuild.bean.StudyTaskBean;
import www.dico.cn.partybuild.bean.StudyTaskForm;
import www.dico.cn.partybuild.modleview.StudyTaskView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.StudyTaskPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshListener;

@CreatePresenter(StudyTaskPresenter.class)
public class StudyTaskActivity extends AbstractMvpActivity<StudyTaskView, StudyTaskPresenter> implements StudyTaskView {
    @BindView(R.id.rv_study_task)
    RecyclerView rv_study_task;
    @BindView(R.id.study_task_empty_data)
    View study_task_empty_data;
    @BindView(R.id.study_task_net_error)
    View study_task_net_error;
    @BindView(R.id.srl_study_task)
    SmartRefreshLayout srl_study_task;
    private StudyTaskAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studytask);
        ButterKnife.bind(this);
        rv_study_task.setLayoutManager(new LinearLayoutManager(this));
        srl_study_task.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMvpPresenter().doStudyTaskRequest();
            }
        });
        getMvpPresenter().doStudyTaskRequest();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        getMvpPresenter().doStudyTaskRequest();
//    }

    public void goBackStudyTask(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_study_task.finishRefresh();
        final StudyTaskBean bean = new Gson().fromJson(result, StudyTaskBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                final List<StudyTaskBean.DataBean> beans = bean.getData();
                if (null != beans && beans.size() > 0) {
                    srl_study_task.setVisibility(View.VISIBLE);
                    study_task_empty_data.setVisibility(View.GONE);
                    study_task_net_error.setVisibility(View.GONE);
                    adapter = new StudyTaskAdapter(this, R.layout.item_study_task, beans);
                    rv_study_task.setAdapter(adapter);
                    adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            StudyTaskForm form = new StudyTaskForm();
                            form.taskId = beans.get(position).getId();
                            form.taskState=beans.get(position).getTaskState();
                            goTo(TaskBriefActivity.class, form);
                        }
                    });
                } else {
                    srl_study_task.setVisibility(View.GONE);
                    study_task_empty_data.setVisibility(View.VISIBLE);
                    study_task_net_error.setVisibility(View.GONE);
                }
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        srl_study_task.finishRefresh();
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        srl_study_task.setVisibility(View.GONE);
        study_task_empty_data.setVisibility(View.GONE);
        study_task_net_error.setVisibility(View.VISIBLE);
        study_task_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMvpPresenter().doStudyTaskRequest();
            }
        });
    }
}
