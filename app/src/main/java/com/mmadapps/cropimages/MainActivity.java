package com.mmadapps.cropimages;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
    @OnClick(R.id.main_crop_simple)
    public void onCenteredCropsClick() {
        startActivity(new Intent(MainActivity.this, SimpleCropActivity.class));
    }

    @OnClick(R.id.main_crop_custom)
    public void onCustomCropsClick() {
        startActivity(new Intent(MainActivity.this, CustomCropActivity.class));
    }
}

