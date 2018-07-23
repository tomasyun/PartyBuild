package www.dico.cn.partybuild.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.io.Serializable;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.mvp.factory.PresenterMvpFactoryImpl;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.mvp.proxy.BaseMvpProxy;
import www.dico.cn.partybuild.mvp.proxy.PresenterProxyInterface;
import www.dico.cn.partybuild.persistance.Form;
import www.dico.cn.partybuild.widget.CustomToast;

/**
 * @Class: AbstractMvpActivity
 * @Description:继承自Activity的基类MvpActivity
 * 使用代理模式来代理Presenter的创建、销毁、绑定、解绑以及Presenter的状态保存,其实就是管理Presenter的生命周期
 * @author: yun tuo
 * @Date: 2018\1\29 0029 11:30
 */

public class AbstractMvpActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends FragmentActivity implements PresenterProxyInterface<V,P> {
    private AppManager appManager;
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V,P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("perfect-mvp","V onCreate");
        Log.e("perfect-mvp","V onCreate mProxy = " + mProxy);
        Log.e("perfect-mvp","V onCreate this = " + this.hashCode());
        appManager=AppManager.getManager();
        appManager.addActivity(this);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp","V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp","V onDestroy = " );
        mProxy.onDestroy();
        appManager.finishActivity();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp","V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

//    @Override
//    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
//        Log.e("perfect-mvp","V setPresenterFactory");
//        mProxy.setPresenterFactory(presenterFactory);
//    }
//
//    @Override
//    public PresenterMvpFactory<V, P> getPresenterFactory() {
//        Log.e("perfect-mvp","V getPresenterFactory");
//        return mProxy.getPresenterFactory();
//    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp","V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }

    public void showToast(String msg) {
//        showToast(msg,-1,-1);
        CustomToast.Instance().showToast(this, msg);
    }

    protected void showToast(String msg, int bgColor, int txtColor) {
//        CustomToast.Instance().showToastCustom(this, msg, R.layout.toast_msg, R.id.txt_toast_message, Gravity.CENTER, bgColor, txtColor);
    }

    /*
     * 实现画面的跳转
     * @next 跳转目的地
     * @param 参数Form
     */
    public void goTo(Class<? extends Activity> next, Form param) {
        goTo(next, param, -1);
    }

    public void goToForResult(Class<? extends Activity> next, Form param) {
        goTo(next, param, -1, true);
    }

    public void goTo(Class<? extends Activity> next, Form param, int intentFlag) {
        goTo(next, param, intentFlag, false);
    }

    public void goTo(Class<? extends Activity> next, Form param, int intentFlag, boolean result) {
        Intent intent = new Intent();
        if (param != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("param", param);
            intent.putExtras(bundle);
        }
        if (intentFlag > -1) {
            intent.setFlags(intentFlag);
        }
        intent.setClass(this, next);
        if (result)
            startActivityForResult(intent, 0);
        else
            startActivity(intent);
    }

    /*
     * @Title: getParam
     * @Description: TODO(获取activity间传递的参数)
     * @param @return    设定文件
     * @return T    返回类型
     * @throws
     */
    public <T> T getParam() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        T temp = null;
        if (bundle != null) {
            Serializable obj = bundle.getSerializable("param");
            if (obj != null) {
                try {
                    temp = (T) obj;
                } catch (Exception e) {

                }
            }
        }
        return temp;
    }
}
