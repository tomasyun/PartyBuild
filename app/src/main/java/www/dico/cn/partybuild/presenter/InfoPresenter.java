package www.dico.cn.partybuild.presenter;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.InfoView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class InfoPresenter extends BaseMvpPresenter<InfoView> {
    //资讯列表

    /**
     * @param id     栏目id 0：党建要闻、1：习总讲话 2：国史党史、3：时代先锋
     * @param draw   分页合法性校验
     * @param start  起始记录数
     * @param length 分页大小
     */
    public void doGetInfoRequest(IProgressDialog dialog, String id, String draw, int start, int length) {
        EasyHttp.post("getInfo")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .params("draw", draw)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
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
