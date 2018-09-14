package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PublicPromiseView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.PublicPromisePresenter;

@CreatePresenter(PublicPromisePresenter.class)
public class PublicPromiseActivity extends AbstractMvpActivity<PublicPromiseView, PublicPromisePresenter> implements PublicPromiseView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicpromise);
    }

    public void goBackPublicPromise(View view) {
        this.finish();
    }
}
