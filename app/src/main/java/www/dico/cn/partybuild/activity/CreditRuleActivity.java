package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;

public class CreditRuleActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditrule);
    }

    public void goBackCreditRule(View view) {
        finish();
    }
}
