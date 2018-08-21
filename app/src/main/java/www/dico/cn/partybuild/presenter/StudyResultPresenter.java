package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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
    Activity activity=AppManager.getManager().findActivity(StudyResultActivity.class);
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(activity)
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("提交中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };

    public void doSubmitStudyResultRequest(String id, String result) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("result", result);
        String json = new Gson().toJson(map);
        EasyHttp.post("")
                .upJson(json)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().resultSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().resultFailure(e.getMessage());
                    }
                });
    }
}