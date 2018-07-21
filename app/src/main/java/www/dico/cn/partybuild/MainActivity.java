package www.dico.cn.partybuild;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.fragment.ExamFragment;
import www.dico.cn.partybuild.fragment.HomeFragment;
import www.dico.cn.partybuild.fragment.PayDuesFragment;
import www.dico.cn.partybuild.fragment.PersonalFragment;
import www.dico.cn.partybuild.fragment.SignInFragment;
import www.dico.cn.partybuild.modleview.MainView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MainPresenter;
import www.yuntdev.com.bottomnavigationlibrary.adapter.MyViewPagerAdapter;
import www.yuntdev.com.bottomnavigationlibrary.controller.NavigationController;
import www.yuntdev.com.bottomnavigationlibrary.item.BaseTabItem;
import www.yuntdev.com.bottomnavigationlibrary.item.NormalItemView;
import www.yuntdev.com.bottomnavigationlibrary.referview.NoTouchViewPager;
import www.yuntdev.com.bottomnavigationlibrary.referview.PageBottomTabLayout;


@CreatePresenter(MainPresenter.class)
public class MainActivity extends AbstractMvpActivity<MainView, MainPresenter> implements MainView {
    @FieldView(R.id.vp_main)
    NoTouchViewPager vp_main;
    @FieldView(R.id.tab_main)
    PageBottomTabLayout tab_main;
    private NavigationController controller;
    private static boolean isExit = false;
    private List<Fragment> fragments;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFind.bind(this);
        controller = tab_main
                .custom()
                .addItem(tabItem(R.mipmap.img_home_on, R.mipmap.img_home_ok, "首页"))
                .addItem(tabItem(R.mipmap.img_signin_on, R.mipmap.img_signin_ok, "签到"))
                .addItem(tabItem(R.mipmap.img_exam_on, R.mipmap.img_exam_ok, "考试"))
                .addItem(tabItem(R.mipmap.img_dues_on, R.mipmap.img_dues_ok, "党费缴纳"))
                .addItem(tabItem(R.mipmap.img_mine_on, R.mipmap.img_mine_ok, "我的"))
                .build();
        setUp();
        vp_main.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), controller.getItemCount(), fragments));
        vp_main.setCurrentItem(0);
        controller.setupWithViewPager(vp_main);
    }

    public void setUp() {
        fragments = new ArrayList<>();
        for (int i = 0; i < controller.getItemCount(); i++) {
            int position = i;
            switch (position) {
                case 0:
                    HomeFragment hFragment = new HomeFragment();
                    fragments.add(hFragment);
                    break;
                case 1:
                    SignInFragment sFragment = new SignInFragment();
                    fragments.add(sFragment);
                    break;
                case 2:
                    ExamFragment eFragment = new ExamFragment();
                    fragments.add(eFragment);
                    break;
                case 3:
                    PayDuesFragment dFragment = new PayDuesFragment();
                    fragments.add(dFragment);
                    break;
                case 4:
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
}
