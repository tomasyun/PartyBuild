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
        // TODO 组织活动
        goTo(OrgActActivity.class, null);
    }

    public void gotoVoteMrg(View view) {
        //TODO 投票管理
        goTo(VoteManagerActivity.class, null);
    }

    public void gotoQuestionSur(View view) {
        //TODO 问卷调查
        goTo(SurveyListActivity.class, null);
    }

    public void gotoPartyParks(View view) {
        //TODO 党员园地
    }

    public void gotoBranchParks(View view) {
        //TODO 支部园地
    }

    public void gotoTrainingParks(View view) {
        //TODO 培训园地
    }

    public void gotoOrgSituation(View view) {
        //TODO 组织机构
    }

    public void gotoPublicPromise(View view) {
        //TODO 公开承诺
        goTo(PublicPromiseActivity.class, null);
    }
}
