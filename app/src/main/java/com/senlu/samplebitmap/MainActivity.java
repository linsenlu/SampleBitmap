package com.senlu.samplebitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);

    }

    private int calcSampleSize(BitmapFactory.Options opts, int ivWidth, int ivHeight) {
        Log.e("123","ops:"+opts.outWidth+"/"+opts.outHeight);
        int sampleWidth = opts.outWidth/ivWidth;
        int sampleHeight = opts.outHeight/ivHeight;
        int sampleSize=sampleWidth>sampleHeight?sampleWidth:sampleHeight ;
        Log.e("123","sampleSize:"+sampleSize);
        return sampleSize;
    }

    public void show(View view){
        //二次采样
        //第1次采样，先加载图片的边框，得到图片的大小
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds= true;  //只加载边框
        BitmapFactory.decodeResource(getResources(),R.mipmap.me,opts);

        //计算采样率
        int ivWidth = iv.getWidth();
        int ivHeight = iv.getHeight();
        int sampleSize = calcSampleSize(opts,ivWidth,ivHeight);

        //第二次采样
        opts.inJustDecodeBounds = false;    //全部加载
        opts.inSampleSize = sampleSize;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.me, opts);
        iv.setImageBitmap(bitmap);
    }
}
