package www.dico.cn.partybuild.presenter;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.NoticeInfoView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class NoticeInfoPresenter extends BaseMvpPresenter<NoticeInfoView> {
    //通知详情
    public void doNoticeInfoRequest(IProgressDialog dialog, String id) {
        EasyHttp.post("noticeInfo")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
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

    public void doSubmitCommentRequest(IProgressDialog dialog, String isFlag, String id, String content) {
        EasyHttp.post("saveComment")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("isFlag", isFlag)
                .params("id", id)
                .params("content", content)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().submitCommentSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().submitCommentFailure(e.getMessage());
                    }
                });

    }
}
