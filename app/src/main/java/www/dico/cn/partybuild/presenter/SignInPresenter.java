package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.MainActivity;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.SignInView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class SignInPresenter extends BaseMvpPresenter<SignInView> {
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().findActivity(MainActivity.class))
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("获取中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };

    //获取需要签到的会议、活动
    public void doGetSignInConferenceRequest() {
        EasyHttp.post("getSignIn")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
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

    //签到
    public void doSaveSignIn(String id, String is) {
        EasyHttp.post("signIn")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .params("is", is)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().saveSignInSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().saveSignInFailure(e.getMessage());
                    }
                });
    }

    /**
     * 签到成功提示
     *
     * @param context
     * @param layoutId
     */
    public void signInSuccessfulDialogShow(final Activity context, int layoutId) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        dialog.setContentView(view);
        Window w = dialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        w.setGravity(Gravity.CENTER);
        lp.width = (int) (context.getWindowManager().getDefaultDisplay().getWidth() * 0.75);
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        w.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        TextView tv_sign_in_know = view.findViewById(R.id.tv_sign_in_know);
        tv_sign_in_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                getMvpView().signUpSuccessRefresh();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1f;
                context.getWindow().setAttributes(lp);
            }
        });
        dialog.show();
    }

}
