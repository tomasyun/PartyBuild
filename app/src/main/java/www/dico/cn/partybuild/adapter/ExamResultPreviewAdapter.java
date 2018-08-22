package www.dico.cn.partybuild.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.ExamResultActivity;
import www.dico.cn.partybuild.bean.ExamReviewBean;

public class ExamResultPreviewAdapter extends TagAdapter<ExamReviewBean.DataBean.QuestionIdsBean> {
    private LayoutInflater mInflater;

    public ExamResultPreviewAdapter(List<ExamReviewBean.DataBean.QuestionIdsBean> datas) {
        super(datas);
        mInflater = LayoutInflater.from(AppManager.getManager().curActivity());
    }

    @Override
    public View getView(FlowLayout parent, int position, ExamReviewBean.DataBean.QuestionIdsBean questionIdsBean) {
        View convertView = mInflater.inflate(R.layout.item_question_tag, parent, false);
        TextView tv_question_number = convertView.findViewById(R.id.tv_question_number);
        ImageView iv_result_flag = convertView.findViewById(R.id.iv_result_flag);
        tv_question_number.setText(String.valueOf(position + 1));
        ExamResultActivity activity = (ExamResultActivity) AppManager.getManager().findActivity(ExamResultActivity.class);
        switch (questionIdsBean.getIsTrue()) {
            case "0":
                iv_result_flag.setBackgroundDrawable(activity.getResources().getDrawable(R.mipmap.img_error));
                tv_question_number.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.textview_corner5_red_stroke_white_bg));
                break;
            case "1":
                iv_result_flag.setBackgroundDrawable(activity.getResources().getDrawable(R.mipmap.img_correct));
                tv_question_number.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.textview_corner5_green_stroke_white_bg));
                break;
        }
        return convertView;
    }
}
