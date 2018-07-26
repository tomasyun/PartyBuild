package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.ExamView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.bean.ExamsBean;
import www.dico.cn.partybuild.presenter.ExamPresenter;

//考试列表
@CreatePresenter(ExamPresenter.class)
public class ExamFragment extends AbstractFragment<ExamView, ExamPresenter> implements ExamView {
    private RadioGroup rg_exam;

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
                        break;
                    case R.id.rbt_exam_ok:
                        break;
                }
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
}
