package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String nick = null;
    int loginCheck=0;
    ImageButton healthInfo, main, question, chatGPT, myInfo;
    Button arduino;
    TextView symtom, headLine, jindan;
    private final long finishtimeed = 1000;
    private long presstime = 0;
    public static Activity _Main_Activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginCheck=0;
        _Main_Activity = MainActivity.this;
        System.setProperty( "https.protocols", "TLSv1.1,TLSv1.2" );
        nick = getIntent().getStringExtra("nickname");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headLine = (TextView)findViewById(R.id.headLine);
        if(nick!=null){ //로그인 했을 경우
            loginCheck=1;
            headLine = (TextView)findViewById(R.id.headLine);
            headLine.setText(nick + "님 좋은 하루 보내세요");
            //문진정보 및 증상정보까지 추가
        }
        else{
            headLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            });
        }
        initializeView();

    }

    @Override
    public void onBackPressed() { // 뒤로가기 버튼
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - presstime;

        if (0 <= intervalTime && finishtimeed >= intervalTime)
        {
            finish();
        }
        else
        {
            presstime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }

    void initializeView(){
        setTitle("동의보감");
        healthInfo = (ImageButton) findViewById(R.id.healthInfo);
        myInfo = (ImageButton) findViewById(R.id.myInfo);
        chatGPT = (ImageButton) findViewById(R.id.chatGPT);
        question = (ImageButton) findViewById(R.id.question);
        main = (ImageButton) findViewById(R.id.main);
        symtom = (TextView)findViewById(R.id.symtoms);
        jindan = (TextView)findViewById(R.id.jindan);
        arduino = (Button)findViewById(R.id.arduino);

        //jindan 값 바꿔서 진단표 수정
        



        arduino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), arduinoActivity.class); //카테고리로 수정
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });


        symtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), categoryActivity.class); //카테고리로 수정
                intent.putExtra("check", 1);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), question_ctg.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        healthInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), categoryActivity.class); //카테고리로 수정
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        chatGPT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), chatgptActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        myInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loginCheck==0){
                    Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), myInfoActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
            }
        });

    }
}












