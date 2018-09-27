package www.dico.cn.partybuild.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.R;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.DownloadProgressCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.utils.HttpLog;

public class DownLoadService extends Service {
    private String destFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "M_UPDATE_DIR";
    private Context mContext;
    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private int preProgress = 0;
    private String destFileName = "update.apk";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = this;
        loadFile();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void loadFile() {
        setUp();
        String url = AppConfig.urlFormat1(AppConfig.updateUrl);
        EasyHttp.downLoad(url)
                .savePath(destFileDir)
                .saveName(destFileName)
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        HttpLog.e(progress + "% ");
                        DownLoadService.this.update(progress);
                        if (done) {
                            cancel();
                        }
                    }

                    @Override
                    public void onStart() {
                        HttpLog.i("======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete(String path) {
                        HttpLog.e("文件保存路径：" + path);
                        installApk(new File(path));
                    }

                    @Override
                    public void onError(ApiException e) {
                        cancel();
                        Toast.makeText(DownLoadService.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void installApk(File file) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//www.dico.cn.partybuild--包名
            Uri apkUri = FileProvider.getUriForFile(mContext, "www.dico.cn.partybuild.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            // 执行意图进行安装
        } else {
            Uri uri = Uri.fromFile(file);
            install.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        mContext.startActivity(install);
    }

    public void setUp() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {//8.0
            manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("1",
                    "channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(mContext, "1");
            builder.setSmallIcon(R.mipmap.img_desk_icon)
                    .setContentText("0%")
                    .setContentTitle("智慧党建应用更新")
                    .setProgress(100, 0, false)
                    .setOnlyAlertOnce(true);//提醒声仅一次
            manager.notify(1000, builder.build());
        } else {
            builder = new NotificationCompat.Builder(mContext)
                    .setSmallIcon(R.mipmap.img_desk_icon)
                    .setContentText("0%")
                    .setContentTitle("智慧党建应用更新")
                    .setProgress(100, 0, false);
            manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1000, builder.build());
        }
    }

    public void update(int progress) {
        int currProgress = progress;
        if (preProgress < currProgress) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
            manager.notify(1000, builder.build());
        }
        preProgress = progress;
    }

    /**
     * 取消通知
     */
    public void cancel() {
        manager.cancel(1000);
    }

}
