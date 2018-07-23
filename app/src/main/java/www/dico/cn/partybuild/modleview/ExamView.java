package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.ExamsBean;

public interface ExamView extends BaseMvpView{
    void resultSuccess(ExamsBean result);

    void resultFailure(String result);
}
