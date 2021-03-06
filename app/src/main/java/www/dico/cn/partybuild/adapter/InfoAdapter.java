package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.InfoBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class InfoAdapter extends CommonAdapter<InfoBean.DataBeanX.DataBean> {
    public InfoAdapter(Context context, int layoutId, List<InfoBean.DataBeanX.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, InfoBean.DataBeanX.DataBean infoBean, int position) {
        holder.setText(R.id.tv_title_info_item, infoBean.getTitle());
        holder.setText(R.id.tv_date_info_item, infoBean.getPublishDate());
        holder.setText(R.id.tv_comment_info_item, infoBean.getCommentNum() + "评论");
    }
}
