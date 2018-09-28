package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.InfodetailsActivity;
import www.dico.cn.partybuild.activity.MeetingSummaryActivity;
import www.dico.cn.partybuild.modleview.MeetingSummaryView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class MeetingSummaryPresenter extends BaseMvpPresenter<MeetingSummaryView> {
    public IProgressDialog getDialog() {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().findActivity(MeetingSummaryActivity.class))
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("获取中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        return dialog;
    }

    public void doMeetingSummaryRequest(String id) {
        EasyHttp.post("meetingSummary")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .execute(new ProgressDialogCallBack<String>(getDialog(), true, true) {
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
