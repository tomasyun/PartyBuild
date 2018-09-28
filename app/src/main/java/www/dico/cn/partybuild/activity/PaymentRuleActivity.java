package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;

public class PaymentRuleActivity extends BaseActivity {
    @BindView(R.id.credit_info_empty_data)
    View credit_info_empty_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentrule);
        ButterKnife.bind(this);
        TextView tv_empty = credit_info_empty_data.findViewById(R.id.tv_empty);
        tv_empty.setText("暂无规则");
    }

    public void goBackPaymentRule(View view) {
        this.finish();
    }
}
