package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.ExamView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.persistance.ExamsBean;
import www.dico.cn.partybuild.presenter.ExamPresenter;
@CreatePresenter(ExamPresenter.class)
public class ExamFragment extends AbstractFragment<ExamView, ExamPresenter> implements ExamView {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_exam,null);
        return view;
    }

    @Override
    public void resultSuccess(ExamsBean result) {

    }

    @Override
    public void resultFailure(String result) {

    }
}
