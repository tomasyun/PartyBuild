package www.dico.cn.partybuild.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.widget.ZoomImageView;

public class DisplayImgFileActivity extends AppCompatActivity {
    private String filePath = "";
    private ZoomImageView img_scale;

    public static void openDisplayImgFileActivity(Context context, String filePath) {
        Intent intent = new Intent(context, DisplayImgFileActivity.class);
        intent.putExtra("filepath", filePath);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayimgfile);
        Intent intent = getIntent();
        filePath = intent.getStringExtra("filepath");
        img_scale = findViewById(R.id.img_scale);
        Bitmap bitmap = getLocalBitmap(filePath); //从本地取图片(在cdcard中获取)  //
        img_scale.setImageBitmap(bitmap); //设置Bitmap
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
