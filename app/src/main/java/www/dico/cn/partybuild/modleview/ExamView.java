package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.bean.ExamsBean;

public interface ExamView extends BaseMvpView{
    void examOnResultSuccess(String result);

    void examOnResultFailure(String result);
    void examOkResultSuccess(String result);

    void examOkResultFailure(String result);
}
