package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.ExamRuleActivity;
import www.dico.cn.partybuild.adapter.ExamOkAdapter;
import www.dico.cn.partybuild.adapter.ExamOnAdapter;
import www.dico.cn.partybuild.bean.ExamOkBean;
import www.dico.cn.partybuild.bean.ExamOnBean;
import www.dico.cn.partybuild.bean.ExamRuleBean;
import www.dico.cn.partybuild.bean.ExamRuleForm;
import www.dico.cn.partybuild.modleview.ExamView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.bean.ExamsBean;
import www.dico.cn.partybuild.presenter.ExamPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

//考试列表
@CreatePresenter(ExamPresenter.class)
public class ExamFragment extends AbstractFragment<ExamView, ExamPresenter> implements ExamView {
    private RadioGroup rg_exam;
    private RecyclerView rv_exam;
    private ExamOnAdapter onAdapter;
    private ExamOkAdapter okAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, null);
        rg_exam = view.findViewById(R.id.rg_exam);
        rg_exam.check(R.id.rbt_exam_on);
        rg_exam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_exam_on:
                        //待考
                        getMvpPresenter().examsOnRequest("0");
                        break;
                    case R.id.rbt_exam_ok:
                        //已考
                        getMvpPresenter().examsOkRequest("1");
                        break;
                }
            }
        });
        rv_exam = view.findViewById(R.id.rv_exam);
        rv_exam.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void examOnResultSuccess(String result) {
        final ExamsBean bean = new Gson().fromJson(result, ExamsBean.class);
        if (bean.code.equals("0000")) {
            if (!bean.getData().isEmpty()) {
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
            if (!bean.getData().isEmpty()) {
                okAdapter = new ExamOkAdapter(getActivity(), R.layout.item_exam_ok, bean.getData());
                rv_exam.setAdapter(okAdapter);
                okAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        goTo(ExamRuleActivity.class, null);
                    }
                });
            }
        } else {

        }
    }

    @Override
    public void examOkResultFailure(String result) {
        showToast(result);
    }


    public List<ExamOnBean> initExamOn() {
        List<ExamOnBean> list = new ArrayList<>();
        ExamOnBean bean = new ExamOnBean();
        bean.setTitle("陕西缔科网络科技有限公司年终考核");
        bean.setDate("2018-7-26 15:25");
        list.add(bean);
        return list;
    }

    public List<ExamOkBean> initExamOk() {
        List<ExamOkBean> list = new ArrayList<>();
        ExamOkBean bean = new ExamOkBean();
        bean.setTitle("通昱消防全体大培训");
        bean.setDate("2018-7-26 15:25");
        list.add(bean);
        return list;
    }

    @Override
    public void preventPreLoad() {
        super.preventPreLoad();
        getMvpPresenter().examsOnRequest("0");
    }
}
