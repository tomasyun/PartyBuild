package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.NoticeInfoActivity;
import www.dico.cn.partybuild.bean.NoticeBean;
import www.dico.cn.partybuild.utils.DateTimeUtils;
import www.dico.cn.partybuild.utils.GlideUtils;
import www.dico.cn.partybuild.utils.ScreenUtils;
import www.dico.cn.partybuild.utils.SizeUtils;
import www.dico.cn.partybuild.widget.ExpandLongTextView;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class NoticeAdapter extends CommonAdapter<NoticeBean.DataBean> {
    public NoticeAdapter(Context context, int layoutId, List<NoticeBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(ViewHolder holder, NoticeBean.DataBean noticeBean, int position) {
        GlideUtils.loadCircleImage(AppManager.getManager().curActivity(), noticeBean.getAvatar(), (ImageView) holder.getView(R.id.iv_avatar_notice_item));
        holder.setText(R.id.tv_name_notice_item, noticeBean.getName());
        String publishDate=noticeBean.getPublishDate();
        publishDate=(publishDate==null)?DateTimeUtils.getNow():publishDate;
        String minutes = DateTimeUtils.getMinutes(publishDate, DateTimeUtils.getNow());
        holder.setText(R.id.tv_date_notice_item, minutes + "分钟前");

        ExpandLongTextView tv_content_notice_item = holder.getView(R.id.tv_content_notice_item);
        tv_content_notice_item.setExpand(false);
        tv_content_notice_item.initWidth(ScreenUtils.getScreenWidth(AppManager.getManager().curActivity()) - SizeUtils.dp2px(AppManager.getManager().curActivity(), 60));
        tv_content_notice_item.setMaxLines(1);
        tv_content_notice_item.setExpandText(noticeBean.getContent());
        tv_content_notice_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppManager.getManager().curActivity(), NoticeInfoActivity.class);
                AppManager.getManager().curActivity().startActivity(intent);
            }
        });
    }
}
