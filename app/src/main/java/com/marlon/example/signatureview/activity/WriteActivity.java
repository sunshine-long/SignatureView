package com.marlon.example.signatureview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.marlon.example.signatureview.R;
import com.marlon.example.signatureview.view.SignatureView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteActivity extends AppCompatActivity {
    private static final int RESSOULTCODE = 1000;
    @BindView(R.id.clear1)
    Button clear1;
    @BindView(R.id.save1)
    Button save1;
    @BindView(R.id.change)
    Button change;
    @BindView(R.id.view)
    SignatureView mPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.clear1, R.id.save1, R.id.change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear1:
                mPathView.clear();
                break;
            case R.id.save1:
                if (mPathView.getTouched()) {
                    try {
                        mPathView.save("/sdcard/qm.png", true, 10);
                        setResult(RESSOULTCODE);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                    Toast.makeText(this, "您没有签名~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.change:
                mPathView.setPaintWidth(20);
                mPathView.setPenColor(Color.RED);
                mPathView.clear();
                break;
        }
    }
}
