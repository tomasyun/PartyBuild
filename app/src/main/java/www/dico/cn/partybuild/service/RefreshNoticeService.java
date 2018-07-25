package www.dico.cn.partybuild.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.receiver.AlarmReceiver;

//消息实时更新
public class RefreshNoticeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
//AlarmManager的常用方法有三个：
//
//        set(int type，long startTime，PendingIntent pi)；//一次性
//        setExact(int type, long triggerAtMillis, PendingIntent operation)//一次性的精确版
//        setRepeating(int type，long startTime，long intervalTime，PendingIntent
//        pi)；//精确重复
//        setInexactRepeating（int type，long startTime，long
//        intervalTime，PendingIntent pi）；//非精确，降低功耗
//        type表示闹钟类型，startTime表示闹钟第一次执行时间，long intervalTime表示间隔时间，PendingIntent表示闹钟响应动作
//
//        对以上各个参数的详细解释
//        闹钟的类型：
//
//        AlarmManager.ELAPSED_REALTIME：休眠后停止，相对开机时间
//        AlarmManager.ELAPSED_REALTIME_WAKEUP：休眠状态仍可唤醒cpu继续工作，相对开机时间
//        AlarmManager.RTC：同1，但时间相对于绝对时间
//        AlarmManager.RTC_WAKEUP：同2，但时间相对于绝对时间
//        AlarmManager.POWER_OFF_WAKEUP：关机后依旧可用，相对于绝对时间
//        绝对时间：1970 年 1月 1 日 0 点
//        startTime：
//        闹钟的第一次执行时间，以毫秒为单位，一般使用当前时间。
//
//        SystemClock.elapsedRealtime()：系统开机至今所经历时间的毫秒数
//        System.currentTimeMillis()：1970 年 1 月 1 日 0 点至今所经历时间的毫秒数
//        intervalTime：执行时间间隔。
//
//        PendingIntent ：
//        PendingIntent用于描述Intent及其最终的行为.，这里用于获取定时任务的执行动作。