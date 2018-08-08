package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.MeetBriefBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface MeetingBriefView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);
}
