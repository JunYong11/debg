package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class myInfoActivity extends AppCompatActivity {
    MainActivity mActivity = (MainActivity)MainActivity._Main_Activity;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);

        initializeView();
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
