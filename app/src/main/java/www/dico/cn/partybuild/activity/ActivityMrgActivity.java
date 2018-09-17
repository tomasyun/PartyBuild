package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.SkipForm;

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
        SkipForm form=new SkipForm();
        form.skip=0;
        goTo(CommonActivity.class,form);
    }

    public void gotoBranchParks(View view) {
        //TODO 支部园地
        SkipForm form=new SkipForm();
        form.skip=1;
        goTo(CommonActivity.class,form);
    }

    public void gotoTrainingParks(View view) {
        //TODO 培训园地
        SkipForm form=new SkipForm();
        form.skip=2;
        goTo(CommonActivity.class,form);
    }

    public void gotoOrgSituation(View view) {
        //TODO 组织机构
        SkipForm form=new SkipForm();
        form.skip=3;
        goTo(CommonActivity.class,form);
    }

    public void gotoPublicPromise(View view) {
        //TODO 公开承诺
        goTo(PublicPromiseActivity.class, null);
    }
}
