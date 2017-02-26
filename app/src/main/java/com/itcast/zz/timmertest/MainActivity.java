package com.itcast.zz.timmertest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

                @Bind(R.id.tv_timmer)
                TextView mTvTimmer;
                private CountDownTimer mCountDownTimer;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);
                    ButterKnife.bind(this);
                    mCountDownTimer = new CountDownTimer(10000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            mTvTimmer.setText(millisUntilFinished/1000+"秒后重发");
                        }

            @Override
            public void onFinish() {
                mTvTimmer.setText("获取验证码");
            }
        };
        mCountDownTimer.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @OnClick(R.id.tv_timmer)
    public void onClick() {
        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
                break;
                // Night mode is not active, we're in day time
            case Configuration.UI_MODE_NIGHT_YES:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                break;
                // Night mode is active, we're at night!
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                // We don't know what mode we're in, assume notnight
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mCountDownTimer.cancel();
        super.onDestroy();
    }
}
