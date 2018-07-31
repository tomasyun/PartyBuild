package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.QuestionsAdapter;
import www.dico.cn.partybuild.bean.QuestionBean;
import www.dico.cn.partybuild.modleview.OnlineExamView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.bean.OnlineExamBean;
import www.dico.cn.partybuild.presenter.OnlineExamPresenter;
import www.dico.cn.partybuild.widget.CountDownButtonHelper;
import www.yuntdev.com.bottomnavigationlibrary.referview.NoTouchViewPager;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

//在线考试
@CreatePresenter(OnlineExamPresenter.class)
public class OnlineExamActivity extends AbstractMvpActivity<OnlineExamView, OnlineExamPresenter> implements OnlineExamView {
    private CountDownButtonHelper helper;
    @FieldView(R.id.tv_residue_time_online_exam)
    TextView tv_residue_time_online_exam;
    private String during = "1";
    private QuestionsAdapter adapter;
    @FieldView(R.id.vp_online_exam)
    NoTouchViewPager vp_online_exam;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (tv_residue_time_online_exam != null) {
                        helper = new CountDownButtonHelper(tv_residue_time_online_exam, "00分:00秒", Integer.parseInt(during) * 60, 1, 0);
                        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
                            @Override
                            public void finish() {

                            }
                        });
                        helper.start();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineexam);
        ViewFind.bind(this);
        mHandler.sendEmptyMessage(0);
        adapter = new QuestionsAdapter(this,questions() , R.layout.item_question);
        vp_online_exam.setAdapter(adapter);
    }

    public void goback(View view) {
        new AlertDialog(this).builder()
                .setTitle("退出考试")
                .setMsg("考试中途退出，会记零分，您确定退出?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnlineExamActivity.this.finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    public void resultSuccess(OnlineExamBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog(this).builder()
                    .setTitle("退出考试")
                    .setMsg("考试中途退出，会记零分，您确定退出?")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            OnlineExamActivity.this.finish();
                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).show();
        }
        return false;
    }

    public List<QuestionBean> questions() {
        List<QuestionBean> list = new ArrayList<>();
        QuestionBean bean = new QuestionBean();
        bean.setTitle("中国共产党成立日期___");
        list.add(bean);
        return list;
    }
}
