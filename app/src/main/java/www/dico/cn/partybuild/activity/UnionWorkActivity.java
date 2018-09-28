package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.SkipForm;

public class UnionWorkActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unionwork);
    }

    public void goBackUnionWork(View view) {
        this.finish();
    }

    public void branchUnion(View view) {
        //TODO 分工会
        SkipForm form = new SkipForm();
        form.skip = 8;
        goTo(CommonActivity.class, form);
    }

    public void unionCommittee(View view) {
        //TODO 工会委员会
        SkipForm form = new SkipForm();
        form.skip = 9;
        goTo(CommonActivity.class, form);
    }

    public void womenCommittee(View view) {
        //TODO 女工委员会
        SkipForm form = new SkipForm();
        form.skip = 10;
        goTo(CommonActivity.class, form);
    }

    public void relateSystem(View view) {
        //TODO 相关制度
        SkipForm form = new SkipForm();
        form.skip = 11;
        goTo(CommonActivity.class, form);
    }

    public void pubInfo(View view) {
        //TODO 公开信息
        SkipForm form = new SkipForm();
        form.skip = 12;
        goTo(CommonActivity.class, form);
    }

    public void firstCouncil(View view) {
        //TODO 十届一次职代会
        SkipForm form = new SkipForm();
        form.skip = 13;
        goTo(CommonActivity.class, form);
    }

    public void secondCouncil(View view) {
        //TODO 十届二次职代会
        SkipForm form = new SkipForm();
        form.skip = 14;
        goTo(CommonActivity.class, form);
    }

    public void thirdCouncil(View view) {
        //TODO 十届三次职代会
        SkipForm form = new SkipForm();
        form.skip = 15;
        goTo(CommonActivity.class, form);
    }

    public void fourthCouncil(View view) {
        //TODO 十届四次职代会
        SkipForm form = new SkipForm();
        form.skip = 16;
        goTo(CommonActivity.class, form);
    }

    public void chess(View view) {
        //TODO 象棋协会
        SkipForm form = new SkipForm();
        form.skip = 17;
        goTo(CommonActivity.class, form);
    }

    public void dance(View view) {
        //TODO 舞蹈瑜伽协会
        SkipForm form = new SkipForm();
        form.skip = 18;
        goTo(CommonActivity.class, form);
    }

    public void tennis(View view) {
        //TODO 台球、乒乓球协会
        SkipForm form = new SkipForm();
        form.skip = 19;
        goTo(CommonActivity.class, form);
    }

    public void basketball(View view) {
        //TODO 篮球协会
        SkipForm form = new SkipForm();
        form.skip = 20;
        goTo(CommonActivity.class, form);
    }

    public void camera(View view) {
        //TODO 摄影协会
        SkipForm form = new SkipForm();
        form.skip = 21;
        goTo(CommonActivity.class, form);
    }

    public void badminton(View view) {
        //TODO 网球、羽毛球协会
        SkipForm form = new SkipForm();
        form.skip = 22;
        goTo(CommonActivity.class, form);
    }

    public void swimming(View view) {
        //TODO 游泳协会
        SkipForm form = new SkipForm();
        form.skip = 23;
        goTo(CommonActivity.class, form);
    }

    public void firstUnion(View view) {
        //TODO 第一分工会
        SkipForm form = new SkipForm();
        form.skip = 24;
        goTo(CommonActivity.class, form);
    }

    public void secondUnion(View view) {
        //TODO 第二分工会
        SkipForm form = new SkipForm();
        form.skip = 25;
        goTo(CommonActivity.class, form);
    }

    public void thirdUnion(View view) {
        //TODO 第三分工会
        SkipForm form = new SkipForm();
        form.skip = 26;
        goTo(CommonActivity.class, form);
    }

    public void fourthUnion(View view) {
        //TODO 第四分工会
        SkipForm form = new SkipForm();
        form.skip = 27;
        goTo(CommonActivity.class, form);
    }

    public void fiveUnion(View view) {
        //TODO 第五分工会
        SkipForm form = new SkipForm();
        form.skip = 28;
        goTo(CommonActivity.class, form);
    }

    public void sixUnion(View view) {
        //TODO 第六分工会
        SkipForm form = new SkipForm();
        form.skip = 29;
        goTo(CommonActivity.class, form);
    }
}
