package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.NoticeBean;
import www.dico.cn.partybuild.utils.GlideUtils;
import www.dico.cn.partybuild.utils.ScreenUtils;
import www.dico.cn.partybuild.utils.SizeUtils;
import www.dico.cn.partybuild.widget.ExpandLongTextView;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class NoticeAdapter extends CommonAdapter<NoticeBean> {
    public NoticeAdapter(Context context, int layoutId, List<NoticeBean> datas) {
        super(context, layoutId, datas);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(ViewHolder holder, NoticeBean noticeBean, int position) {
//        GlideUtils.loadCircleImage(AppManager.getManager().curActivity(),noticeBean.getAvatar(),(ImageView)holder.getView(R.id.iv_avatar_notice_item));
        holder.setText(R.id.tv_name_notice_item,noticeBean.getName());
        holder.setText(R.id.tv_date_notice_item,noticeBean.getDate());

        ExpandLongTextView tv_content_notice_item=holder.getView(R.id.tv_content_notice_item);
        tv_content_notice_item.initWidth(ScreenUtils.getScreenWidth(AppManager.getManager().curActivity()) - SizeUtils.dp2px(AppManager.getManager().curActivity(), 80));
        tv_content_notice_item.setMaxLines(2);
        tv_content_notice_item.setExpandText(noticeBean.getContent());
    }
}