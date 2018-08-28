package www.dico.cn.partybuild.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.MainActivity;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.ExamRuleActivity;
import www.dico.cn.partybuild.adapter.ExamOkAdapter;
import www.dico.cn.partybuild.adapter.ExamOnAdapter;
import www.dico.cn.partybuild.bean.ExamResultForm;
import www.dico.cn.partybuild.bean.ExamsBean;
import www.dico.cn.partybuild.modleview.ExamView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.ExamPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

//考试列表
@CreatePresenter(ExamPresenter.class)
public class ExamFragment extends AbstractFragment<ExamView, ExamPresenter> implements ExamView, MainActivity.FragmentRefreshInterface {
    @BindView(R.id.rg_exam)
    RadioGroup rg_exam;
    @BindView(R.id.rv_exam)
    RecyclerView rv_exam;
    @BindView(R.id.exam_empty_data)
    View exam_empty_data;
    @BindView(R.id.exam_net_error)
    View exam_net_error;
    private ExamOnAdapter onAdapter;
    private ExamOkAdapter okAdapter;
    private int position = 0;
    private MainActivity activity;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.activity = (MainActivity) context;
        activity.setExamListRefresh(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, null);
        ButterKnife.bind(this, view);
        rg_exam.check(R.id.rbt_exam_on);
        rg_exam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_exam_on:
                        //待考
                        position = 0;
                        getMvpPresenter().examsOnRequest("0");
                        break;
                    case R.id.rbt_exam_ok:
                        //已考
                        position = 1;
                        getMvpPresenter().examsOkRequest("1");
                        break;
                }
            }
        });
        rv_exam.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void examOnResultSuccess(String result) {
        final ExamsBean bean = new Gson().fromJson(result, ExamsBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData() && bean.getData().size() > 0) {
                rv_exam.setVisibility(View.VISIBLE);
                exam_empty_data.setVisibility(View.GONE);
                exam_net_error.setVisibility(View.GONE);
                onAdapter = new ExamOnAdapter(getActivity(), R.layout.item_exam_on, bean.getData());
                rv_exam.setAdapter(onAdapter);
                onAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        ExamResultForm form=new ExamResultForm();
                        form.examId = bean.getData().get(position).getId();
                        form.state = "0";//0：待考 1：已考
                        form.isPass=bean.getData().get(position).getIsPass();
                        form.examScore=bean.getData().get(position).getExamScore();
                        goTo(ExamRuleActivity.class, form);
                    }
                });
            } else {
                rv_exam.setVisibility(View.GONE);
                exam_empty_data.setVisibility(View.VISIBLE);
                exam_net_error.setVisibility(View.GONE);
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void examOnResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void examOkResultSuccess(String result) {
        final ExamsBean bean = new Gson().fromJson(result, ExamsBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData() && bean.getData().size() > 0) {
                rv_exam.setVisibility(View.VISIBLE);
                exam_empty_data.setVisibility(View.GONE);
                okAdapter = new ExamOkAdapter(getActivity(), R.layout.item_exam_ok, bean.getData());
                rv_exam.setAdapter(okAdapter);
                okAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        ExamResultForm form=new ExamResultForm();
                        form.examId = bean.getData().get(position).getId();
                        form.state = "1";//0：待考 1：已考
                        form.isPass=bean.getData().get(position).getIsPass();
                        form.examScore=bean.getData().get(position).getExamScore();
                        goTo(ExamRuleActivity.class, form);
                    }
                });
            } else {
                rv_exam.setVisibility(View.GONE);
                exam_empty_data.setVisibility(View.VISIBLE);
            }
        } else {

        }
    }

    @Override
    public void examOkResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        rv_exam.setVisibility(View.GONE);
        exam_empty_data.setVisibility(View.GONE);
        exam_net_error.setVisibility(View.VISIBLE);
        exam_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        getMvpPresenter().examsOnRequest("0");
                        break;
                    case 1:
                        getMvpPresenter().examsOkRequest("1");
                        break;
                }
            }
        });
    }

    @Override
    public void preventPreLoad() {
        super.preventPreLoad();
        switch (position) {
            case 0:
                getMvpPresenter().examsOnRequest("0");
                break;
            case 1:
                getMvpPresenter().examsOkRequest("1");
                break;
        }
    }

    @Override
    public void Refresh() {
        switch (position) {
            case 0:
                getMvpPresenter().examsOnRequest("0");
                break;
            case 1:
                getMvpPresenter().examsOkRequest("1");
                break;
        }
    }
}
