package com.example.main;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class categoryActivity extends AppCompatActivity {
    Button bArr[] = new Button[21];

    protected void onCreate(Bundle savedInstanceState){
        ActionBar bar = getSupportActionBar();
        bar.hide();
        System.setProperty( "https.protocols", "TLSv1.1,TLSv1.2" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        btnSetting();
    }



    public void onClickBtn(View v) {
        try {
            String result[];
            String[] nickname = new String[3];
            String part = ((TextView)v).getText().toString();
            if (!part.equals("")) {

                Intent intent = new Intent(getApplicationContext(), healthInfoActivity.class);
                intent.putExtra("part",part);

                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        }catch(Exception e){
            Log.i("DBtest", ".....ERROR.....!");
            Toast.makeText(getApplicationContext(), "DB연결 에러발생", Toast.LENGTH_SHORT).show();
        }
    }

    void btnSetting(){
        bArr[0]=(Button)findViewById(R.id.가슴);
        bArr[1]=(Button)findViewById(R.id.골반);
        bArr[2]=(Button)findViewById(R.id.귀);
        bArr[3]=(Button)findViewById(R.id.기타);
        bArr[4]=(Button)findViewById(R.id.눈);
        bArr[5]=(Button)findViewById(R.id.다리);
        bArr[6]=(Button)findViewById(R.id.등허리);
        bArr[7]=(Button)findViewById(R.id.머리);
        bArr[8]=(Button)findViewById(R.id.목);
        bArr[9]=(Button)findViewById(R.id.발);
        bArr[10]=(Button)findViewById(R.id.배);
        bArr[11]=(Button)findViewById(R.id.생식기);
        bArr[12]=(Button)findViewById(R.id.손);
        bArr[13]=(Button)findViewById(R.id.얼굴);
        bArr[14]=(Button)findViewById(R.id.엉덩이);
        bArr[15]=(Button)findViewById(R.id.유방);
        bArr[16]=(Button)findViewById(R.id.입);
        bArr[17]=(Button)findViewById(R.id.전신);
        bArr[18]=(Button)findViewById(R.id.코);
        bArr[19]=(Button)findViewById(R.id.팔);
        bArr[20]=(Button)findViewById(R.id.피부);
    }

    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
