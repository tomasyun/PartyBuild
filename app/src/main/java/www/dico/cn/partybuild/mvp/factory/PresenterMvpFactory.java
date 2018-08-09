package www.dico.cn.partybuild.mvp.factory;

import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

/**
 * @Class: PresenterMvpFactory
 * @Description:Presenter工厂接口
 * @author: yun tuo
 * @Date: 2018\1\29 0029 10:31
 */

public interface PresenterMvpFactory<V extends BaseMvpView, P extends BaseMvpPresenter<V>> {
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
