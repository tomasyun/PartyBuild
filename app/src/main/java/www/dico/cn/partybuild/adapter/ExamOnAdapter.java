package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.ExamsBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class ExamOnAdapter extends CommonAdapter<ExamsBean.DataBean> {
    public ExamOnAdapter(Context context, int layoutId, List<ExamsBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ExamsBean.DataBean bean, int position) {
        holder.setText(R.id.tv_title_exam_on_item, bean.getTitle());
        holder.setText(R.id.tv_date_exam_on_item, bean.getLimitDate());
    }
}
