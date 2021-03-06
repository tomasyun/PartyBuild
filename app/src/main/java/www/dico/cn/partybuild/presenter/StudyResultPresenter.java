package www.dico.cn.partybuild.presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.StudyResultView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class StudyResultPresenter extends BaseMvpPresenter<StudyResultView> {
    public void doSubmitStudyResultRequest(IProgressDialog dialog, String id, String result) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("result", result);
        String json = new Gson().toJson(map);
        EasyHttp.post("studyObtain")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .upJson(json)
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
