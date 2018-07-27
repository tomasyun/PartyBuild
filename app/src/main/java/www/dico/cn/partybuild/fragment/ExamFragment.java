package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.ExamRuleActivity;
import www.dico.cn.partybuild.adapter.ExamOkAdapter;
import www.dico.cn.partybuild.adapter.ExamOnAdapter;
import www.dico.cn.partybuild.bean.ExamOkBean;
import www.dico.cn.partybuild.bean.ExamOnBean;
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
                        rv_exam.setAdapter(onAdapter);
                        break;
                    case R.id.rbt_exam_ok:
                        //已考
                        rv_exam.setAdapter(okAdapter);
                        break;
                }
            }
        });
        rv_exam = view.findViewById(R.id.rv_exam);
        rv_exam.setLayoutManager(new LinearLayoutManager(getActivity()));
        onAdapter = new ExamOnAdapter(getActivity(), R.layout.item_exam_on, initExamOn());
        rv_exam.setAdapter(onAdapter);
        okAdapter = new ExamOkAdapter(getActivity(), R.layout.item_exam_ok, initExamOk());
        onAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(ExamRuleActivity.class, null);
            }
        });
        okAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(ExamRuleActivity.class, null);
            }
        });
        return view;
    }

    @Override
    public void resultSuccess(ExamsBean result) {

    }

    @Override
    public void resultFailure(String result) {
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
}
