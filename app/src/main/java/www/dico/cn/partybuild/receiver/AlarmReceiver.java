package www.dico.cn.partybuild.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import www.dico.cn.partybuild.service.RefreshNoticeService;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, RefreshNoticeService.class);
        context.startService(i);
    }
}
