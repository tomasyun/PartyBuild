package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.ExamOkBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class ExamOkAdapter extends CommonAdapter<ExamOkBean> {
    public ExamOkAdapter(Context context, int layoutId, List<ExamOkBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ExamOkBean examOkBean, int position) {
        holder.setText(R.id.tv_title_exam_ok_item,examOkBean.getTitle());
        holder.setText(R.id.tv_date_exam_ok_item,examOkBean.getDate());
        TextView tv_state_exam_ok_item=holder.getView(R.id.tv_state_exam_ok_item);

    }
}
