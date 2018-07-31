package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import www.dico.cn.partybuild.R;

public class SignUpSuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupsuccess);
    }

    public void goback(View view) {
        this.finish();
    }

    //完成
    public void complete(View view) {
        this.finish();
    }
}
