package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.NoticeInfoBean;
import www.dico.cn.partybuild.modleview.NoticeInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.NoticeInfoPresenter;

@CreatePresenter(NoticeInfoPresenter.class)
public class NoticeInfoActivity extends AbstractMvpActivity<NoticeInfoView, NoticeInfoPresenter> implements NoticeInfoView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeinfo);
    }

    public void goback(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
