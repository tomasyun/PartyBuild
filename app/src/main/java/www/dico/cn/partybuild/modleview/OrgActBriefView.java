package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.OrgActBriefBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface OrgActBriefView extends BaseMvpView{
    void resultSuccess(OrgActBriefBean result);

    void resultFailure(String result);
}
