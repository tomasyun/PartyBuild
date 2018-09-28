package www.dico.cn.partybuild.presenter;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.PublicPromiseView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class PublicPromisePresenter extends BaseMvpPresenter<PublicPromiseView> {
    public void doPublicPromiseRequest(IProgressDialog dialog, String articleType, String childrenType, String draw, int start, int length) {
        EasyHttp.post("findArticle")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("articleType", articleType)
                .params("childrenType", childrenType)
                .params("draw", draw)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().resultSuccess(s);
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
