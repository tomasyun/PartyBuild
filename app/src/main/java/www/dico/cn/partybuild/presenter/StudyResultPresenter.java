package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.StudyResultActivity;
import www.dico.cn.partybuild.modleview.StudyResultView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class StudyResultPresenter extends BaseMvpPresenter<StudyResultView> {
    public IProgressDialog getDialog() {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().findActivity(StudyResultActivity.class))
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("获取中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        return dialog;
    }

    public void doSubmitStudyResultRequest(String id, String result) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("result", result);
        String json = new Gson().toJson(map);
        EasyHttp.post("studyObtain")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .upJson(json)
                .execute(new ProgressDialogCallBack<String>(getDialog(), true, true) {
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
