package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.QuestionSurveyBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class QuestionSurveyAdapter extends CommonAdapter<QuestionSurveyBean.DataBean> {

    public QuestionSurveyAdapter(Context context, int layoutId, List<QuestionSurveyBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, QuestionSurveyBean.DataBean questionSurveyBean, int position) {
        holder.setText(R.id.tv_title_question_survey_item, questionSurveyBean.getTitle());
        holder.setText(R.id.tv_date_question_survey_item, questionSurveyBean.getLimitDate());
        holder.setText(R.id.tv_population_question_survey_item, questionSurveyBean.getPaticipants());
    }
}
