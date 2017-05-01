package com.example.dell.glideprogressive2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView tvProgress;
    ProgressBar progressBarLoading;

    String url = "https://img.clipartfest.com/04b00b79c14f0bb5097597fa84dee4b9_dawn-over-mount-fuji-mount-fuji-clipart_1024-768.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.imgV);
        progressBarLoading = (ProgressBar) findViewById(R.id.progress_bar);
        tvProgress = (TextView) findViewById(R.id.tv_progress);

        GlideProgressive progressive = new GlideProgressive(MainActivity.this);
        progressive.setProgressListener(new GlideProgressive.ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                final int progress = (int) ((100 * bytesRead) / contentLength);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBarLoading.setProgress(progress);
                        tvProgress.setText(progress+"%");
                        if (progress >= 100) {
                            progressBarLoading.setVisibility(View.GONE);
                            tvProgress.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        progressive.startDownload(url, img);
    }
}