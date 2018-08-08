package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.StudyTaskBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface StudyTaskView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);
}
