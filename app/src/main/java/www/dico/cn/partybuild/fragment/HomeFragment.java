package www.dico.cn.partybuild.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.ActivityMrgActivity;
import www.dico.cn.partybuild.activity.MailboxActivity;
import www.dico.cn.partybuild.activity.MeetingActivity;
import www.dico.cn.partybuild.activity.PayDuesActivity;
import www.dico.cn.partybuild.activity.StudyTaskActivity;
import www.dico.cn.partybuild.modleview.HomeView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.bean.AdvertiseImgM;
import www.dico.cn.partybuild.bean.HomeBean;
import www.dico.cn.partybuild.presenter.HomePresenter;
import www.dico.cn.partybuild.utils.GlideUtils;
import www.dico.cn.partybuild.widget.CustomTextView;

//首页
@CreatePresenter(HomePresenter.class)
public class HomeFragment extends AbstractFragment<HomeView, HomePresenter> implements HomeView, View.OnClickListener {
    private XBanner xbanner;//轮播
    private TextView tv_gongshi_home;
    private CustomTextView tv_notice_home;
    private LinearLayout lin_meeting_home;
    private LinearLayout lin_studies_home;
    private LinearLayout lin_activity_home;
    private LinearLayout lin_dues_home;
    private LinearLayout lin_mailbox_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        xbanner = view.findViewById(R.id.xbanner);

        tv_gongshi_home = view.findViewById(R.id.tv_gongshi_home);
        SpannableString content = new SpannableString("公示公告");
        ForegroundColorSpan blue = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        ForegroundColorSpan red = new ForegroundColorSpan(Color.RED);
        content.setSpan(blue, 0, content.length() - 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        content.setSpan(red, 2, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        StyleSpan style = new StyleSpan(Typeface.ITALIC);
        content.setSpan(style, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_gongshi_home.setText(content);

        tv_notice_home = view.findViewById(R.id.tv_notice_home);
        tv_notice_home.setText("陕西缔科网络科技有限公司");
        tv_notice_home.init(getActivity().getWindowManager());
        tv_notice_home.startScroll();
        tv_notice_home.setEnabled(false);

        lin_meeting_home = view.findViewById(R.id.lin_meeting_home);
        lin_studies_home = view.findViewById(R.id.lin_studies_home);
        lin_activity_home = view.findViewById(R.id.lin_activity_home);
        lin_dues_home = view.findViewById(R.id.lin_dues_home);
        lin_mailbox_home = view.findViewById(R.id.lin_mailbox_home);
        lin_meeting_home.setOnClickListener(this);
        lin_studies_home.setOnClickListener(this);
        lin_activity_home.setOnClickListener(this);
        lin_dues_home.setOnClickListener(this);
        lin_mailbox_home.setOnClickListener(this);

        List<AdvertiseImgM> urls = new ArrayList<>();
        AdvertiseImgM advertise = new AdvertiseImgM();
        advertise.setPoster("http://pic.5tu.cn/uploads/allimg/1606/pic_5tu_big_201606272309319893.jpg");
        urls.add(advertise);
        AdvertiseImgM advertise2 = new AdvertiseImgM();
        advertise2.setPoster("http://pic.5tu.cn/uploads/allimg/1607/pic_5tu_big_2016070102141153200.jpg");
        urls.add(advertise2);
        AdvertiseImgM advertise3 = new AdvertiseImgM();
        advertise3.setPoster("http://pic.5tu.cn/uploads/allimg/1607/pic_5tu_big_201607091531527229.jpg");
        urls.add(advertise3);
        List<String> titles = new ArrayList<>();
        titles.add("飞屋环游记");
        titles.add("蓝色科技");
        titles.add("运动会");
        xbanner.setAutoPlayAble(urls.size() > 1);
        xbanner.setData(urls, titles);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                GlideUtils.loadImage(getActivity(), ((AdvertiseImgM) model).getPoster(), (ImageView) view);
            }
        });

        return view;
    }

    @Override
    public void resultSuccess(HomeBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_meeting_home://三会一课
                goTo(MeetingActivity.class, null);
                break;
            case R.id.lin_studies_home://学习任务
                goTo(StudyTaskActivity.class, null);
                break;
            case R.id.lin_activity_home://活动管理
                goTo(ActivityMrgActivity.class, null);
                break;
            case R.id.lin_dues_home://党费缴纳
                goTo(PayDuesActivity.class, null);
                break;
            case R.id.lin_mailbox_home://领导信箱
                goTo(MailboxActivity.class, null);
                break;
        }
    }
}
