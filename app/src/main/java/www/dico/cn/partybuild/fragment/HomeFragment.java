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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.ActivityMrgActivity;
import www.dico.cn.partybuild.activity.IntegrityBuildActivity;
import www.dico.cn.partybuild.activity.MailboxActivity;
import www.dico.cn.partybuild.activity.MeetingActivity;
import www.dico.cn.partybuild.activity.MissionParksActivity;
import www.dico.cn.partybuild.activity.PayDuesActivity;
import www.dico.cn.partybuild.activity.StudyTaskActivity;
import www.dico.cn.partybuild.activity.UnionWorkActivity;
import www.dico.cn.partybuild.bean.AdvertiseImgM;
import www.dico.cn.partybuild.bean.HomeBean;
import www.dico.cn.partybuild.modleview.HomeView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.HomePresenter;
import www.dico.cn.partybuild.utils.GlideUtils;
import www.dico.cn.partybuild.widget.CustomTextView;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshListener;

//首页
@CreatePresenter(HomePresenter.class)
public class HomeFragment extends AbstractFragment<HomeView, HomePresenter> implements HomeView, View.OnClickListener {
    @BindView(R.id.srl_home)
    SmartRefreshLayout srl_home;
    @BindView(R.id.xbanner)
    XBanner xbanner;//轮播
    @BindView(R.id.tv_gongshi_home)
    TextView tv_gongshi_home;
    @BindView(R.id.tv_notice_home)
    CustomTextView tv_notice_home;
    @BindView(R.id.rel_meeting_home)
    RelativeLayout rel_meeting_home;
    @BindView(R.id.rel_studies_home)
    RelativeLayout rel_studies_home;
    @BindView(R.id.rel_activity_home)
    RelativeLayout rel_activity_home;
    @BindView(R.id.rel_dues_home)
    RelativeLayout rel_dues_home;
    @BindView(R.id.rel_mailbox_home)
    RelativeLayout rel_mailbox_home;
    @BindView(R.id.rel_mission_home)
    RelativeLayout rel_mission_home;
    @BindView(R.id.rel_union_home)
    RelativeLayout rel_union_home;
    @BindView(R.id.rel_integrity_home)
    RelativeLayout rel_integrity_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        srl_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMvpPresenter().homeDataRequest();
            }
        });

        SpannableString content = new SpannableString("公示公告");
        content.setSpan(new ForegroundColorSpan(Color.parseColor("#0099EE")), 0, content.length() - 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        content.setSpan(new ForegroundColorSpan(Color.RED), 2, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        StyleSpan style = new StyleSpan(Typeface.ITALIC);
        content.setSpan(style, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_gongshi_home.setText(content);

        tv_notice_home.setText("");
        tv_notice_home.init(getActivity().getWindowManager());

        rel_meeting_home.setOnClickListener(this);
        rel_studies_home.setOnClickListener(this);
        rel_activity_home.setOnClickListener(this);
        rel_dues_home.setOnClickListener(this);
        rel_mailbox_home.setOnClickListener(this);
        rel_mission_home.setOnClickListener(this);
        rel_union_home.setOnClickListener(this);
        rel_integrity_home.setOnClickListener(this);
//        List<AdvertiseImgM> urls = new ArrayList<>();
//        AdvertiseImgM advertise = new AdvertiseImgM();
//        advertise.setPoster("http://pic.5tu.cn/uploads/allimg/1606/pic_5tu_big_201606272309319893.jpg");
//        urls.add(advertise);
//        AdvertiseImgM advertise2 = new AdvertiseImgM();
//        advertise2.setPoster("http://pic.5tu.cn/uploads/allimg/1607/pic_5tu_big_2016070102141153200.jpg");
//        urls.add(advertise2);
//        AdvertiseImgM advertise3 = new AdvertiseImgM();
//        advertise3.setPoster("http://pic.5tu.cn/uploads/allimg/1607/pic_5tu_big_201607091531527229.jpg");
//        urls.add(advertise3);
//        List<String> titles = new ArrayList<>();
//        titles.add("飞屋环游记");
//        titles.add("蓝色科技");
//        titles.add("运动会");
//        xbanner.setAutoPlayAble(urls.size() > 1);
//        xbanner.setData(urls, titles);
//        xbanner.loadImage(new XBanner.XBannerAdapter() {
//            @Override
//            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                GlideUtils.loadImage(getActivity(), ((AdvertiseImgM) model).getPoster(), (ImageView) view);
//            }
//        });
        getMvpPresenter().homeDataRequest();
        return view;
    }

    @Override
    public void resultSuccess(String result) {
        srl_home.finishRefresh();
        HomeBean bean = new Gson().fromJson(result, HomeBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                if (bean.getData().getAnnouncement() != null) {
                    String content = bean.getData().getAnnouncement().getContent();
                    tv_notice_home.setText(content);
                    tv_notice_home.init(getActivity().getWindowManager());
                    tv_notice_home.startScroll();
                    tv_notice_home.setEnabled(false);
                }
                List<AdvertiseImgM> urls = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                List<HomeBean.DataBean.AdvertisementBean> list = bean.getData().getAdvertisement();
                if (null != list && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        AdvertiseImgM advertise = new AdvertiseImgM();
                        advertise.setPoster(list.get(i).getAdvertImg());
                        urls.add(advertise);
                        titles.add(list.get(i).getAdvertTitle());
                    }
                }
                if (urls.size() > 1)
                    xbanner.setAutoPlayAble(urls.size() > 1);
                xbanner.setData(urls, titles);
                xbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        GlideUtils.loadImageSetUpError(getActivity(), ((AdvertiseImgM) model).getPoster(), (ImageView) view, R.mipmap.img_dico);
                    }
                });
                xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(XBanner banner, Object model, int position) {

                    }
                });
            }
//            new UpdateManager(getActivity()).checkUpdate(false);//版本检测
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        srl_home.finishRefresh();
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_meeting_home://三会一课
                goTo(MeetingActivity.class, null);
                break;
            case R.id.rel_studies_home://学习任务
                goTo(StudyTaskActivity.class, null);
                break;
            case R.id.rel_activity_home://活动管理
                goTo(ActivityMrgActivity.class, null);
                break;
            case R.id.rel_dues_home://党费缴纳
                goTo(PayDuesActivity.class, null);
                break;
            case R.id.rel_mailbox_home://领导信箱
                goTo(MailboxActivity.class, null);
                break;
            case R.id.rel_mission_home://团青园地
                goTo(MissionParksActivity.class, null);
                break;
            case R.id.rel_union_home://工会工作
                goTo(UnionWorkActivity.class, null);
                break;
            case R.id.rel_integrity_home://廉政建设
                goTo(IntegrityBuildActivity.class, null);
                break;
        }
    }
}
