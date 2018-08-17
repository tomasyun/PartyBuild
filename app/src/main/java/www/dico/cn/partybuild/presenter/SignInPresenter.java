package www.dico.cn.partybuild.presenter;

import android.app.Activity;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.MainActivity;
import www.dico.cn.partybuild.modleview.SignInView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;

public class SignInPresenter extends BaseMvpPresenter<SignInView> {
    Activity activity= AppManager.getManager().findActivity(MainActivity.class);
}
