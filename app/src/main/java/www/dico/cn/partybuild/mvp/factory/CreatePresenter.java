package www.dico.cn.partybuild.mvp.factory;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;

/**
 * @Class: CreatePresenter
 * @Description:标注创建Presenter的注解
 * @author: yun tuo
 * @Date: 2018\1\29 0029 10:30
 */

@Inherited  //允许子类继承父类 的注解
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseMvpPresenter> value();
}