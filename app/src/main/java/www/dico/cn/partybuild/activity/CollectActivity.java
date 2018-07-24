package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.CollectView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.CollectsBean;
import www.dico.cn.partybuild.presenter.CollectPresenter;

//收藏
@CreatePresenter(CollectPresenter.class)
public class CollectActivity extends AbstractMvpActivity<CollectView, CollectPresenter> implements CollectView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ViewFind.bind(this);
        //getMvpPresenter().collectsRequest("");
    }

    public void goback(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(CollectsBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
