package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.CommonView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CommonPresenter;
@CreatePresenter(CommonPresenter.class)
public class CommonActivity extends AbstractMvpActivity<CommonView,CommonPresenter> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }
}
