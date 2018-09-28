package www.dico.cn.partybuild.presenter;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.OrgActView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class OrgActPresenter extends BaseMvpPresenter<OrgActView> {
    //组织活动
    public void doOrgActRequest(IProgressDialog dialog) {
        EasyHttp.post("orgActList")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().resultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().resultFailure(e.getMessage());
                    }
                });
    }
}
