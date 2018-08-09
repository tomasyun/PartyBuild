package www.dico.cn.partybuild.mvp.proxy;

import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

/**
 * @Class: PresenterProxyInterface
 * @Description:代理接口
 * @author: yun tuo
 * @Date: 2018\1\29 0029 10:47
 */

public interface PresenterProxyInterface<V extends BaseMvpView, P extends BaseMvpPresenter<V>> {
    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
//    void setPresenterFactory(PresenterMvpFactory<V,P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterMvpFactory类型
     */
//    PresenterMvpFactory<V,P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();
}
