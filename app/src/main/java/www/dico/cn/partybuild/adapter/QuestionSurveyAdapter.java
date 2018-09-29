package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.QuestionSurveyBean;
import www.dico.cn.partybuild.utils.DateTimeUtils;
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
        if (DateTimeUtils.isExpired(questionSurveyBean.getLimitDate())) {
            holder.setText(R.id.tv_state_des_survey_item, "调查过期");
        } else {
            holder.setText(R.id.tv_state_des_survey_item, "调查截止");
        }
    }
}
