package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.QuestionSurveyAdapter;
import www.dico.cn.partybuild.bean.QuestionSurveyBean;
import www.dico.cn.partybuild.modleview.QuestionSurveyView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.QuestionSurveyPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

@CreatePresenter(QuestionSurveyPresenter.class)
public class QuestionSurveyActivity extends AbstractMvpActivity<QuestionSurveyView, QuestionSurveyPresenter> implements QuestionSurveyView {
    @FieldView(R.id.rv_question_survey)
    RecyclerView rv_question_survey;
    private QuestionSurveyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionsurvey);
        ViewFind.bind(this);
        rv_question_survey.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionSurveyAdapter(this, R.layout.item_questionsurvey, questionSurveys());
        rv_question_survey.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }
        });
    }

    public void goback(View view) {
        this.finish();
    }

    public List<QuestionSurveyBean> questionSurveys() {
        List<QuestionSurveyBean> list = new ArrayList<>();
        QuestionSurveyBean bean = new QuestionSurveyBean();
        bean.setTitle("问卷调查1");
        list.add(bean);
        return list;
    }

    @Override
    public void resultSuccess(QuestionSurveyBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
