package www.dico.cn.partybuild;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import www.dico.cn.partybuild.modleview.MainView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MainPresenter;
import www.yuntdev.com.library.controller.NavigationController;
import www.yuntdev.com.library.item.BaseTabItem;
import www.yuntdev.com.library.item.NormalItemView;
import www.yuntdev.com.library.referview.NoTouchViewPager;
import www.yuntdev.com.library.referview.PageBottomTabLayout;

@CreatePresenter(MainPresenter.class)
public class MainActivity extends AbstractMvpActivity<MainView, MainPresenter> implements MainView {
    @FieldView(R.id.vp_main)
    NoTouchViewPager vp_main;
    @FieldView(R.id.tab_main)
    PageBottomTabLayout tab_main;
    private NavigationController navigationController;
    private static boolean isExit = false;
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

    }

    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView itemView = new NormalItemView(this);
        itemView.initialize(drawable, checkedDrawable, text);
        itemView.setTextDefaultColor(Color.GRAY);
        itemView.setTextCheckedColor(Color.parseColor("#dd1e12"));
        return itemView;
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
