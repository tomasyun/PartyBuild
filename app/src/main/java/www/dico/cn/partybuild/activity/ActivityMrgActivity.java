package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;

public class ActivityMrgActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymrg);
    }

    public void goBackActMrg(View view) {
        this.finish();
    }

    //组织活动
    public void gotoOrgAct(View view) {
        goTo(OrgActActivity.class, null);
    }

    //投票管理
    public void gotoVoteMrg(View view) {
        goTo(VoteManagerActivity.class, null);
    }

    //问卷调查
    public void gotoQuestionSur(View view) {
        goTo(SurveyListActivity.class, null);
    }
}
