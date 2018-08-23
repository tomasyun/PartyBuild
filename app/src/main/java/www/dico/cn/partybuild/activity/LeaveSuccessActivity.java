package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;

public class LeaveSuccessActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leavesuccess);
    }

    public void leaveComplete(View view) {
        this.finish();
    }

    public void goBackLeaveSuccess(View view) {
        goTo(MeetingBriefActivity.class, null);
    }
}
