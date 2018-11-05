package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.NoticeBean;
import www.dico.cn.partybuild.utils.DateTimeUtils;
import www.dico.cn.partybuild.utils.GlideUtils;
import www.dico.cn.partybuild.utils.ScreenUtils;
import www.dico.cn.partybuild.utils.SizeUtils;
import www.dico.cn.partybuild.utils.StringUtils;
import www.dico.cn.partybuild.widget.ExpandLongTextView;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class NoticeAdapter extends CommonAdapter<NoticeBean.DataBean> {
    public SkipNoticeInfoInterface infoInterface;

    public void setInfoInterface(SkipNoticeInfoInterface infoInterface) {
        this.infoInterface = infoInterface;
    }

    public NoticeAdapter(Context context, int layoutId, List<NoticeBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(ViewHolder holder, NoticeBean.DataBean noticeBean, int position) {
        if (null != noticeBean.getAvatar() && !noticeBean.getAvatar().equals(""))
            GlideUtils.loadCircleImage(AppManager.getManager().curActivity(), AppConfig.urlFormat(noticeBean.getAvatar()), (ImageView) holder.getView(R.id.iv_avatar_notice_item));
        holder.setText(R.id.tv_name_notice_item, noticeBean.getName());
        String publishDate = noticeBean.getPublishDate();
        publishDate = (null == publishDate || publishDate.equals("")) ? DateTimeUtils.getNow() : noticeBean.getPublishDate();
        String diffDate = DateTimeUtils.getMinutes(publishDate, DateTimeUtils.getNow());
        int minutes;
        if (diffDate != null && !diffDate.equals("")) {
            minutes = Integer.valueOf(diffDate);
            if (minutes < 60) {
                holder.setText(R.id.tv_date_notice_item, minutes + "分钟前");
            } else if (minutes > 60 && minutes / 60 <= 24) {
                holder.setText(R.id.tv_date_notice_item, minutes / 60 + "小时前");
            } else {
                holder.setText(R.id.tv_date_notice_item, minutes / (24 * 60) + "天前");
            }
        }
        ExpandLongTextView tv_content_notice_item = holder.getView(R.id.tv_content_notice_item);
        tv_content_notice_item.setExpand(false);
        tv_content_notice_item.initWidth(ScreenUtils.getScreenWidth(AppManager.getManager().
                curActivity()) - SizeUtils.dp2px(AppManager.getManager().
                curActivity(), 100));
        tv_content_notice_item.setMaxLines(3);
        tv_content_notice_item.setExpandText(Html.fromHtml(StringUtils.trimStyle(noticeBean.getContent())));
        tv_content_notice_item.setOnClickListener(view -> infoInterface.skip(position));
    }

    public interface SkipNoticeInfoInterface {
        void skip(int position);
    }
}
