package www.dico.cn.partybuild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.OnlineSurveyView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OnlineSurveyPresenter;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

@CreatePresenter(OnlineSurveyPresenter.class)
public class OnlineSurveyActivity extends AbstractMvpActivity<OnlineSurveyView,OnlineSurveyPresenter> implements OnlineSurveyView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinesurvey);
    }

    public void goBackOnlineSurvey(View view){
        new AlertDialog(this).builder()
                .setTitle("退出调查")
                .setMsg("调查中途退出，本次调查会作废，您确定退出?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnlineSurveyActivity.this.finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            new AlertDialog(this).builder()
                    .setTitle("退出调查")
                    .setMsg("调查中途退出，本次调查会作废，您确定退出?")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            OnlineSurveyActivity.this.finish();
                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).show();
        }
        return false;
    }
}
