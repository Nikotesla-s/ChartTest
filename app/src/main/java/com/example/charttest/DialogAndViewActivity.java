package com.example.charttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogAndViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_and_view);
        //初始化butterKnife
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button)
    public void dialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(View.inflate(this,R.layout.bottom_refuse,null));
        System.out.println("显示");
        final AlertDialog alertDialog=dialog.show();
        System.out.println("退出");
    }
}