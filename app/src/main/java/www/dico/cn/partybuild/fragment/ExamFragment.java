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
import www.dico.cn.partybuild.bean.ExamRuleForm;
import www.dico.cn.partybuild.bean.ExamsBean;
import www.dico.cn.partybuild.modleview.ExamView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.ExamPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

//考试列表
@CreatePresenter(ExamPresenter.class)
public class ExamFragment extends AbstractFragment<ExamView, ExamPresenter> implements ExamView, MainActivity.ExamFragmentInterface {
    @BindView(R.id.rg_exam)
    RadioGroup rg_exam;
    @BindView(R.id.rv_exam)
    RecyclerView rv_exam;
    private ExamOnAdapter onAdapter;
    private ExamOkAdapter okAdapter;
    private int position = 0;
    private MainActivity activity;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.activity = (MainActivity) context;
        activity.setFragmentInterface(this);
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
            if (null != bean.getData() && !bean.getData().isEmpty()) {
                onAdapter = new ExamOnAdapter(getActivity(), R.layout.item_exam_on, bean.getData());
                rv_exam.setAdapter(onAdapter);
                onAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        ExamRuleForm form = new ExamRuleForm();
                        form.examId = bean.getData().get(position).getId();
                        goTo(ExamRuleActivity.class, form);
                    }
                });
            }
        } else {

        }
    }

    @Override
    public void examOnResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void examOkResultSuccess(String result) {
        ExamsBean bean = new Gson().fromJson(result, ExamsBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData() && bean.getData().size() > 0) {
                okAdapter = new ExamOkAdapter(getActivity(), R.layout.item_exam_ok, bean.getData());
                rv_exam.setAdapter(okAdapter);
                okAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        goTo(ExamRuleActivity.class, null);
                    }
                });
            } else {

            }
        } else {

        }
    }

    @Override
    public void examOkResultFailure(String result) {
        showToast(result);
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
    public void notifyRefresh() {
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
