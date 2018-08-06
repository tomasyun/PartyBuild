package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.TaskBriefBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface TaskBriefView extends BaseMvpView {
    void resultSuccess(TaskBriefBean result);

    void resultFailure(String result);
}
