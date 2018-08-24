package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.MeetingActivity;
import www.dico.cn.partybuild.modleview.MeetingView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class MeetingPresenter extends BaseMvpPresenter<MeetingView> {
    //三会一课列表
    public void doMeetingRequest() {
        EasyHttp.post("conferenceList")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
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

    public IProgressDialog getDialog() {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().findActivity(MeetingActivity.class))
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("获取中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        return dialog;
    }
}
