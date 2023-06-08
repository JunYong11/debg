package com.example.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class myInfoActivity extends AppCompatActivity {
    MainActivity mActivity = (MainActivity)MainActivity._Main_Activity;

    String nick = null;
    String result = null;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);
        try {
            nick = getIntent().getStringExtra("nick");
            myinformationActivity task = new myinformationActivity();
            result = task.execute(nick).get();
            String [] resArr = result.split(",");
            show(resArr);
            initializeView();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void show(String[]resArr){
        TextView tv1 = findViewById(R.id.textView1);
        tv1.setText(nick+"님 반갑습니다");
        tv1.setTextSize(25);
        tv1.setTypeface(null, Typeface.BOLD);

        TextView tv2 = findViewById(R.id.textView2);
        tv2.setText("ID: "+ resArr[0]);
        tv2.setTextSize(20);

        TextView tv3 = findViewById(R.id.textView3);
        tv3.setText("PW: ");
        tv3.setTextSize(20);
        TextView tv4 = findViewById(R.id.textView4);
        tv4.setText("문진 횟수: "+ resArr[1] + "회 진행하였습니다.");
        tv4.setTextSize(20);



    }

    void initializeView() {
        setTitle("동의보감");
        ImageButton healthInfo = (ImageButton) findViewById(R.id.myhealthInfo);
        ImageButton myInfo = (ImageButton) findViewById(R.id.mymyInfo);
        ImageButton chatGPT = (ImageButton) findViewById(R.id.mychatGPT);
        ImageButton question = (ImageButton) findViewById(R.id.myquestion);
        ImageButton main = (ImageButton) findViewById(R.id.mymain);
        Button logout = (Button) findViewById(R.id.logout);
        main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mActivity.finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    };




    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
