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
import www.dico.cn.partybuild.adapter.QuestionSurveyAdapter;
import www.dico.cn.partybuild.bean.QuestionSurveyBean;
import www.dico.cn.partybuild.bean.SurveyForm;
import www.dico.cn.partybuild.modleview.SurveyListView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.SurveyListPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

@CreatePresenter(SurveyListPresenter.class)
public class SurveyListActivity extends AbstractMvpActivity<SurveyListView, SurveyListPresenter> implements SurveyListView {
    @BindView(R.id.rv_question_survey)
    RecyclerView rv_question_survey;
    @BindView(R.id.survey_empty_data)
    View survey_empty_data;
    @BindView(R.id.survey_net_error)
    View survey_net_error;
    private QuestionSurveyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveylist);
        ButterKnife.bind(this);
        rv_question_survey.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().doQuestionSurveyRequest();
    }

    public void goBackQuestionSurvey(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        QuestionSurveyBean bean = new Gson().fromJson(result, QuestionSurveyBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData()) {
                final List<QuestionSurveyBean.DataBean> beans = bean.getData();
                if (null != beans && beans.size() > 0) {
                    rv_question_survey.setVisibility(View.VISIBLE);
                    survey_empty_data.setVisibility(View.GONE);
                    survey_net_error.setVisibility(View.GONE);
                    adapter = new QuestionSurveyAdapter(this, R.layout.item_questionsurvey, beans);
                    rv_question_survey.setAdapter(adapter);
                    adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            SurveyForm form = new SurveyForm();
                            form.surveyId = beans.get(position).getId();
                            goTo(OnlineSurveyActivity.class, form);
                        }
                    });
                } else {
                    rv_question_survey.setVisibility(View.GONE);
                    survey_empty_data.setVisibility(View.VISIBLE);
                    survey_net_error.setVisibility(View.GONE);
                }
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        rv_question_survey.setVisibility(View.GONE);
        survey_empty_data.setVisibility(View.GONE);
        survey_net_error.setVisibility(View.VISIBLE);
        survey_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMvpPresenter().doQuestionSurveyRequest();
            }
        });
    }
}
