package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.ExamRuleBean;

public interface ExamRuleView extends BaseMvpView {
    void resultSuccess(ExamRuleBean result);

    void resultFailure(String result);
}
