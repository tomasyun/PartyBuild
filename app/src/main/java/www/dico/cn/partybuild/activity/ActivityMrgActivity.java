package www.dico.cn.partybuild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import www.dico.cn.partybuild.R;

public class ActivityMrgActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymrg);
    }

    public void goback(View view) {
        this.finish();
    }

    //组织活动
    public void gotoOrgAct(View view) {
        Intent intent = new Intent(this, OrgActActivity.class);
        startActivity(intent);
    }

    //投票管理
    public void gotoVoteMrg(View view) {
        Intent intent = new Intent(this, VoteManagerActivity.class);
        startActivity(intent);
    }

    //问卷调查
    public void gotoQuestionSur(View view) {
        Intent intent = new Intent(this, QuestionSurveyActivity.class);
        startActivity(intent);
    }
}
