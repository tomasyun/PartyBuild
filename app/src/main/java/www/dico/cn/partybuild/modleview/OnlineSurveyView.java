package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface OnlineSurveyView extends BaseMvpView{
    void resultSuccess(String result);

    void resultFailure(String result);

    void saveAnswerSuccess(String result);

    void saveAnswerFailure(String result);
}
