package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.InformationBean;

public interface InformationView extends BaseMvpView{
    void resultSuccess(InformationBean result);

    void resultFailure(String result);
}
