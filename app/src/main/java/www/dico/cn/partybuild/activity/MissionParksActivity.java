package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;

public class MissionParksActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missionparks);

    }

    public void goBackmissionParks(View view) {
        this.finish();
    }

    //公告通知
    public void gotoNoticeAnnounce(View view) {

    }

    //支部园地
    public void gotoBranchParks(View view) {
        goTo(BranchParksActivity.class, null);
    }

    //团员园地
    public void gotoGardenParks(View view) {

    }

    //规章制度
    public void gotoRegulation(View view) {

    }

    //组织机构
    public void gotoOrgSituation(View view) {

    }
}
