package www.dico.cn.partybuild;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.fragment.ExamFragment;
import www.dico.cn.partybuild.fragment.HomeFragment;
import www.dico.cn.partybuild.fragment.InfoFragment;
import www.dico.cn.partybuild.fragment.PersonalFragment;
import www.dico.cn.partybuild.fragment.SignInFragment;
import www.dico.cn.partybuild.modleview.MainView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MainPresenter;
import www.dico.cn.partybuild.widget.NoScrollViewPager;
import www.yuntdev.com.bottomnavigationlibrary.adapter.MyViewPagerAdapter;
import www.yuntdev.com.bottomnavigationlibrary.controller.NavigationController;
import www.yuntdev.com.bottomnavigationlibrary.item.BaseTabItem;
import www.yuntdev.com.bottomnavigationlibrary.item.NormalItemView;
import www.yuntdev.com.bottomnavigationlibrary.referview.PageBottomTabLayout;


@CreatePresenter(MainPresenter.class)
public class MainActivity extends AbstractMvpActivity<MainView, MainPresenter> implements MainView {
    private static boolean isExit = false;
    @BindView(R.id.vp_main)
    NoScrollViewPager vp_main;
    @BindView(R.id.tab_main)
    PageBottomTabLayout tab_main;
    private NavigationController controller;
    private List<Fragment> fragments;
    private ExamFragmentInterface fragmentInterface;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        controller = tab_main
                .custom()
                .addItem(tabItem(R.mipmap.img_home_on, R.mipmap.img_home_ok, "首页"))
                .addItem(tabItem(R.mipmap.img_info_on, R.mipmap.img_info_ok, "资讯"))
                .addItem(tabItem(R.mipmap.img_signin_on, R.mipmap.img_signin_ok, "签到"))
                .addItem(tabItem(R.mipmap.img_exam_on, R.mipmap.img_exam_ok, "考试"))
//                .addItem(tabItem(R.mipmap.img_dues_on, R.mipmap.img_dues_ok, "党费缴纳"))
                .addItem(tabItem(R.mipmap.img_mine_on, R.mipmap.img_mine_ok, "我的"))
                .build();
        setUp();
        vp_main.setNoScroll(true);
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), controller.getItemCount(), fragments);
        vp_main.setAdapter(adapter);
        vp_main.setCurrentItem(0);
        controller.setupWithViewPager(vp_main);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(new SkipReceiver(), new IntentFilter("cn.diconet.www"));
    }

    public void setUp() {
        fragments = new ArrayList<>();
        for (int i = 0; i < controller.getItemCount(); i++) {
            int position = i;
            switch (position) {
                case 0://首页
                    HomeFragment hFragment = new HomeFragment();
                    fragments.add(hFragment);
                    break;
                case 1://资讯
                    InfoFragment iFragment = new InfoFragment();
                    fragments.add(iFragment);
                    break;
                case 2://签到
                    SignInFragment sFragment = new SignInFragment();
                    fragments.add(sFragment);
                    break;
                case 3://考试
                    ExamFragment eFragment = new ExamFragment();
                    fragments.add(eFragment);
                    break;
                case 4://我的
                    PersonalFragment pFragment = new PersonalFragment();
                    fragments.add(pFragment);
                    break;
            }
        }
    }

    private BaseTabItem tabItem(int drawable, int checkedDrawable, String text) {
        NormalItemView item = new NormalItemView(this);
        item.initialize(drawable, checkedDrawable, text);
        item.setTextDefaultColor(Color.GRAY);
        item.setTextCheckedColor(Color.parseColor("#dd1e12"));
        return item;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
                        Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                Log.e("####", "exit application");
                this.finish();
            }
            return true;
        }
        return false;
    }

    public ExamFragmentInterface getFragmentInterface() {
        return fragmentInterface;
    }

    public void setFragmentInterface(ExamFragmentInterface fragmentInterface) {
        this.fragmentInterface = fragmentInterface;
    }

    public interface ExamFragmentInterface {
        void notifyRefresh();
    }

    private class SkipReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String skip = intent.getStringExtra("skip");
            switch (skip) {
                case "3":
                    vp_main.setCurrentItem(Integer.parseInt(skip));
                    fragmentInterface.notifyRefresh();
                    break;
            }
        }
    }
}
