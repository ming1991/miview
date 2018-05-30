package com.example.ming;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyGesture";

    Button btn_start;
    Button btn_end;
    Button btn_extend;
    Button btn_original;
    CustomSurfaceView customSurfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    String pathVideo;
    LinearLayout line_view;

    private int fatherView_W;

    private int fatherView_H;
    public  int ratio = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.start_vedio);
        btn_end = findViewById(R.id.stop_vedio);
        line_view = findViewById(R.id.line_view);
        customSurfaceView = findViewById(R.id.hv_scrollView);
        btn_extend = findViewById(R.id.extend);
        btn_original = findViewById(R.id.original);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) customSurfaceView.getLayoutParams();
        layoutParams.width = (int) (ScreenUtils.getScreenWidth(getApplicationContext()));
        layoutParams.gravity = Gravity.CENTER;

        customSurfaceView.setLayoutParams(layoutParams);
        initgetViewW_H();

        btn_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playSurfaceView();
            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopSurfaceView();
            }
        });
        btn_extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customSurfaceView.setExtend(ratio);
            }
        });
        btn_original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customSurfaceView.setOriginal();
            }
        });

        surfaceHolder = customSurfaceView.getHolder();
        surfaceHolder.setFixedSize(800, 800);
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
            }
        });

    }

    private void initgetViewW_H() {
        line_view.postDelayed(new Runnable() {
            @Override
            public void run() {
                fatherView_W = line_view.getWidth();
                fatherView_H = line_view.getHeight();
                Log.i(TAG, "father Top" + line_view.getTop());
                Log.i(TAG, "father Bottom" + line_view.getBottom());
                //  TODO
                //customSurfaceView.setFatherW_H(line_view.getTop(), line_view.getBottom());
                customSurfaceView.setFatherW_H(fatherView_W, fatherView_H);
                customSurfaceView.setFatherTopAndBottom(line_view.getTop(), line_view.getBottom());
            }
        }, 100);
    }

    public void stopSurfaceView() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void playSurfaceView() {
        try {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }
            mediaPlayer.reset();
            AssetFileDescriptor in = getResources().getAssets().openFd("ming.mp4");
            mediaPlayer.setDataSource(in.getFileDescriptor(), in.getStartOffset(), in.getLength());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDisplay(surfaceHolder);

            //循环
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
