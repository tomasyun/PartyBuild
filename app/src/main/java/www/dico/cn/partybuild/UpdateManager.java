package www.dico.cn.partybuild;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import www.dico.cn.partybuild.service.DownLoadService;
import www.dico.cn.partybuild.utils.DeviceUtils;

public class UpdateManager {
    private Activity mContext;

    public UpdateManager(Activity mContext) {
        this.mContext = mContext;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(boolean isToast) {
        /**
         * 在这里请求后台接口，获取更新的内容和最新的版本号
         */
        // 版本的更新信息
//        String version_info = "更新内容\n" + "    1. 底部导航\n" + "    2. 增加视频学习资料\n" + "    ";
        int mVersion_code = DeviceUtils.getVersionCode(mContext);// 当前的版本号
        String versionCode = AppConfig.nVersionCode;
        if (!versionCode.equals("")) {
            int nVersion_code = Integer.parseInt(versionCode);
            if (mVersion_code < nVersion_code) {
//             显示提示对话
                showNoticeDialog(AppConfig.content);
                WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
                lp.alpha = 0.5f;
                mContext.getWindow().setAttributes(lp);
            } else {
                if (isToast) {
                    Toast.makeText(mContext, "已经是最新版本", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void showNoticeDialog(String version_info) {
        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.alert_update, null);
        view.setMinimumWidth((int) (mContext.getWindowManager().getDefaultDisplay().getWidth() * 0.8));//设置dialog的宽度
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);
        dialog.setCanceledOnTouchOutside(true);
        TextView tv_version_name = view.findViewById(R.id.tv_version_name);
        tv_version_name.setText(DeviceUtils.getVersionName(mContext));
        TextView tv_version_info = view.findViewById(R.id.tv_version_info);
        tv_version_info.setText(version_info);
        TextView tv_update_present = view.findViewById(R.id.tv_update_present);
        tv_update_present.setOnClickListener(view12 -> {
            dialog.dismiss();
            mContext.startService(new Intent(mContext, DownLoadService.class));
        });
        TextView tv_update_delay = view.findViewById(R.id.tv_update_delay);
        tv_update_delay.setOnClickListener(view1 -> dialog.dismiss());
        dialog.setOnDismissListener(dialogInterface -> {
            WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
            lp.alpha = 1f;
            mContext.getWindow().setAttributes(lp);
        });
        dialog.show();
    }
}
