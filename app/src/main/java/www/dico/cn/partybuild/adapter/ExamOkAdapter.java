package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.ExamsBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class ExamOkAdapter extends CommonAdapter<ExamsBean.DataBean> {
    public ExamOkAdapter(Context context, int layoutId, List<ExamsBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(ViewHolder holder, ExamsBean.DataBean bean, int position) {
        holder.setText(R.id.tv_title_exam_ok_item, bean.getTitle());
        holder.setText(R.id.tv_date_exam_ok_item, bean.getEndDate());

        TextView tv_exam_ok_score = holder.getView(R.id.tv_exam_ok_score);
        NumberFormat nf = new DecimalFormat("#");

        SpannableString score = new SpannableString(nf.format(Double.valueOf(bean.getExamScore())) + "分");
        score.setSpan(new AbsoluteSizeSpan(40), 0, score.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        score.setSpan(new AbsoluteSizeSpan(28), score.length() - 1, score.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_exam_ok_score.setText(score);
        String isPass = bean.getIsPass();
        if (isPass != null && !isPass.equals("")) {
            switch (isPass) {
                case "0"://考试不及格
                    tv_exam_ok_score.setTextColor(AppManager.getManager().curActivity().getColor(R.color.theme_color));
                    break;
                case "1"://考试及格
                    tv_exam_ok_score.setTextColor(AppManager.getManager().curActivity().getColor(R.color.green));
                    break;
            }
        }
    }
}
