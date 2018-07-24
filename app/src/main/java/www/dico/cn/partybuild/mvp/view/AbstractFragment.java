package www.dico.cn.partybuild.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.io.Serializable;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.mvp.factory.PresenterMvpFactoryImpl;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.mvp.proxy.BaseMvpProxy;
import www.dico.cn.partybuild.mvp.proxy.PresenterProxyInterface;
import www.dico.cn.partybuild.persistance.Form;
import www.dico.cn.partybuild.widget.CustomToast;

/**
 * @Class: AbstractFragment
 * @Description:继承Fragment的MvpFragment基类
 * @author: yun tuo
 * @Date: 2018\1\29 0029 11:32
 */
public class AbstractFragment<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements PresenterProxyInterface<V, P> {

    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    /**
     * 可以实现自己PresenterMvpFactory工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
//    @Override
//    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
//        mProxy.setPresenterFactory(presenterFactory);
//    }
//
//
//    /**
//     * 获取创建Presenter的工厂
//     *
//     * @return PresenterMvpFactory类型
//     */
//    @Override
//    public PresenterMvpFactory<V, P> getPresenterFactory() {
//        return mProxy.getPresenterFactory();
//    }

    /**
     * 获取Presenter
     *
     * @return P
     */
    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }

    public void showToast(String msg) {
//        showToast(msg,-1,-1);
        CustomToast.Instance().showToast(AppManager.getManager().curActivity(), msg);
    }

    protected void showToast(String msg, int bgColor, int txtColor) {
//        CustomToast.Instance().showToastCustom(getActivity(), msg, R.layout.toast_msg, R.id.txt_toast_message, Gravity.CENTER, bgColor, txtColor);
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
        intent.setClass(getActivity(), next);
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
        Intent intent = getActivity().getIntent();
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
