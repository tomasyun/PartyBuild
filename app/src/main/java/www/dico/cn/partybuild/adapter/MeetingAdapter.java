package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.MeetingBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class MeetingAdapter extends CommonAdapter<MeetingBean.DataBean> {
    public MeetingAdapter(Context context, int layoutId, List<MeetingBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MeetingBean.DataBean meetingBean, int position) {
        TextView tv_state_meeting_item = holder.getView(R.id.tv_state_meeting_item);
        TextView tv_flag_meeting_item=holder.getView(R.id.tv_flag_meeting_item);
        switch (meetingBean.getState()) {
            case "0"://未开始
                tv_state_meeting_item.setBackgroundColor(AppManager.getManager().curActivity().getResources().getColor(R.color.theme_color));
                tv_state_meeting_item.setTextColor(AppManager.getManager().curActivity().getResources().getColor(R.color.white));
                tv_state_meeting_item.setText("未开始");
                tv_flag_meeting_item.setText("截止报名");
                break;
            case "1"://进行中
                tv_state_meeting_item.setBackgroundDrawable(AppManager.getManager().curActivity().getResources().getDrawable(R.drawable.red_stroke_white_bg));
                tv_state_meeting_item.setTextColor(AppManager.getManager().curActivity().getResources().getColor(R.color.theme_color));
                tv_state_meeting_item.setText("进行中");
                tv_flag_meeting_item.setText("截止报名");
                break;
            case "2"://已结束
                tv_state_meeting_item.setBackgroundColor(AppManager.getManager().curActivity().getResources().getColor(R.color.grayer));
                tv_state_meeting_item.setTextColor(AppManager.getManager().curActivity().getResources().getColor(R.color.white));
                tv_state_meeting_item.setText("已结束");
                tv_flag_meeting_item.setText("结束日期");
                break;
        }
        holder.setText(R.id.tv_title_meeting_item, meetingBean.getTitle());
        holder.setText(R.id.tv_date_meeting_item, meetingBean.getLimitDate());
    }
}
