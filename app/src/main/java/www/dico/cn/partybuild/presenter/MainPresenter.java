package www.dico.cn.partybuild.presenter;


import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.MainView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.SimpleCallBack;
import www.yuntdev.com.library.exception.ApiException;

public class MainPresenter extends BaseMvpPresenter<MainView> {

    public void doVersionUpdateRequest() {
        EasyHttp.post("versionUpdate")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        getMvpView().resultFailure(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        getMvpView().resultSuccess(s);
                    }
                });
    }
}
