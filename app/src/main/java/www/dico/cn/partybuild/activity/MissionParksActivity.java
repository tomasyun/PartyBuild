package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.SkipForm;

public class MissionParksActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missionparks);
    }

    public void goBackmissionParks(View view) {
        this.finish();
    }

    public void gotoNoticeAnnounce(View view) {
        //TODO 公告通知
        SkipForm form=new SkipForm();
        form.skip=4;
        goTo(CommonActivity.class,form);    }

    public void gotoBranchParks(View view) {
        //TODO 支部园地
        goTo(BranchParksActivity.class, null);
    }

    public void gotoGardenParks(View view) {
        //TODO 团员园地
        SkipForm form=new SkipForm();
        form.skip=5;
        goTo(CommonActivity.class,form);
    }

    public void gotoRegulation(View view) {
        //TODO 规章制度
        SkipForm form=new SkipForm();
        form.skip=6;
        goTo(CommonActivity.class,form);
    }

    public void gotoOrgSituation(View view) {
        //TODO 组织机构
        SkipForm form=new SkipForm();
        form.skip=7;
        goTo(CommonActivity.class,form);
    }
}
