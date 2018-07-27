package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.MeetingBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class MeetingAdapter extends CommonAdapter<MeetingBean> {
    public MeetingAdapter(Context context, int layoutId, List<MeetingBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MeetingBean meetingBean, int position) {
        holder.setText(R.id.tv_date_meeting_item, meetingBean.getDate());
    }
}
