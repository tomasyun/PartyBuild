package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.StudyTaskBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class StudyTaskAdapter extends CommonAdapter<StudyTaskBean.DataBean> {
    public StudyTaskAdapter(Context context, int layoutId, List<StudyTaskBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, StudyTaskBean.DataBean bean, int position) {
        TextView tv_elective_study_task_item = holder.getView(R.id.tv_elective_study_task_item);
        switch (bean.getIsElective()) {
            case "0":
                tv_elective_study_task_item.setText("必修");
                tv_elective_study_task_item.setBackgroundColor(Color.parseColor("#369af2"));
                break;
            case "1":
                tv_elective_study_task_item.setText("选修");
                tv_elective_study_task_item.setBackgroundColor(Color.parseColor("#f97d6c"));
                break;
        }

        holder.setText(R.id.tv_title_study_task_item, bean.getTitle());
        holder.setText(R.id.tv_date_study_task_item, bean.getLimitDate());
        NumberFormat format = new DecimalFormat("0.00");
        String curHours = bean.getCurHours();
        curHours = (curHours == null) ? "" : format.format(Double.valueOf(bean.getCurHours()));
        holder.setText(R.id.tv_learned_study_task_item, curHours + "");
        String totalHours = bean.getTotalHours();
        totalHours = (totalHours == null) ? "" : format.format(Double.valueOf(bean.getTotalHours()));
        holder.setText(R.id.tv_length_study_task_item, "/" + totalHours);
        TextView tv_state_study_task_item = holder.getView(R.id.tv_state_study_task_item);
        switch (bean.getTaskState()) {//0:去学习,1:已学习
            case "0":
                tv_state_study_task_item.setText("去学习");
                tv_state_study_task_item.setTextColor(AppManager.getManager().curActivity().getResources().getColor(R.color.theme_color));
                tv_state_study_task_item.setBackgroundDrawable(AppManager.getManager().curActivity().getResources().getDrawable(R.drawable.textview_corner20_red_stroke_white_bg));
                break;
            case "1":
                tv_state_study_task_item.setText("已完成");
                tv_state_study_task_item.setTextColor(AppManager.getManager().curActivity().getResources().getColor(R.color.white));
                tv_state_study_task_item.setBackgroundDrawable(AppManager.getManager().curActivity().getResources().getDrawable(R.drawable.textview_corner20_grayer_bg));
                break;
            case "2":
                tv_state_study_task_item.setText("已过期");
                tv_state_study_task_item.setTextColor(AppManager.getManager().curActivity().getResources().getColor(R.color.white));
                tv_state_study_task_item.setBackgroundDrawable(AppManager.getManager().curActivity().getResources().getDrawable(R.drawable.textview_corner20_grayer_bg));
                break;
        }
    }
}
