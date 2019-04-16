package www.dico.cn.partybuild.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.io.Serializable;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.bean.Form;
import www.dico.cn.partybuild.widget.CustomToast;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class BaseActivity extends FragmentActivity {
    public Activity mActivity;
    public IProgressDialog dialog = () -> {
        LoadingDialog.Builder builder = new LoadingDialog.Builder(BaseActivity.this)
                .setCancelable(true)
                .setCancelOutside(true)
                .setMessage("获取中..")
                .setShowMessage(true);
        return builder.create();
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AppManager.getManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        AppManager.getManager().finishActivity();
    }

    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String msg) {
//        showToast(msg,-1,-1);
        CustomToast.Instance().showToast(this, msg);
    }

    protected void showToast(String msg, int bgColor, int txtColor) {
//        CustomToast.Instance().showToastCustom(this, msg, R.layout.toast_msg, R.id.txt_toast_message, Gravity.CENTER, bgColor, txtColor);
    }

    /*
     * 实现画面的跳转
     * @next 跳转目的地
     * @param 参数Form
     */
    public void goTo(Class<? extends Activity> next, Form param) {
        goTo(next, param, -1);
    }

    public void goToForResult(Class<? extends Activity> next, Form param) {
        goTo(next, param, -1, true);
    }

    public void goTo(Class<? extends Activity> next, Form param, int intentFlag) {
        goTo(next, param, intentFlag, false);
    }

    public void goTo(Class<? extends Activity> next, Form param, int intentFlag, boolean result) {
        Intent intent = new Intent();
        if (param != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("param", param);
            intent.putExtras(bundle);
        }
        if (intentFlag > -1) {
            intent.setFlags(intentFlag);
        }
        intent.setClass(this, next);
        if (result)
            startActivityForResult(intent, 0);
        else
            startActivity(intent);
    }

    /*
     * @Title: getParam
     * @Description: TODO(获取activity间传递的参数)
     * @param @return    设定文件
     * @return T    返回类型
     * @throws
     */
    public <T> T getParam() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        T temp = null;
        if (bundle != null) {
            Serializable obj = bundle.getSerializable("param");
            if (obj != null) {
                try {
                    temp = (T) obj;
                } catch (Exception e) {

                }
            }
        }
        return temp;
    }
}