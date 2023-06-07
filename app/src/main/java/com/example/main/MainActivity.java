package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String nick = null, result = "";
    int loginCheck=0;
    LinearLayout munjin;
    ImageButton healthInfo, main, question, chatGPT, myInfo;
    Button arduino;
    TextView symtom, headLine, jindan;
    private final long finishtimeed = 1000;
    private long presstime = 0;
    public static Activity _Main_Activity;

    String cut = "§";
    String disease[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginCheck=0;
        _Main_Activity = MainActivity.this;
        System.setProperty( "https.protocols", "TLSv1.1,TLSv1.2" );
        nick = getIntent().getStringExtra("nickname");
        result = getIntent().getStringExtra("jindan");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        munjin = (LinearLayout)findViewById(R.id.munjin);
        jindan = (TextView)findViewById(R.id.jindan);
        headLine = (TextView)findViewById(R.id.headLine);
        if(nick!=null && result!=null){ //로그인 했을 경우
            loginCheck=1;
            headLine = (TextView)findViewById(R.id.headLine);
            headLine.setText(nick + "님 좋은 하루 보내세요");
            //문진정보 및 증상정보까지 추가
            if(result.equals("의심되는 증상이 없습니다.")){
                jindan.setText(result);
            }
            else if(result!=null){
                disease=result.split(cut);
                munjin.removeAllViews();
                munjin.setBackgroundColor(Color.parseColor("#00ff0000"));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(16,16,16,32);

                for(int i=0;i<disease.length;i++){
                    CardView cv = new CardView(this);
                    cv.setLayoutParams(layoutParams);
                    cv.setRadius(12F);
                    cv.setContentPadding(25,25,25,25);
                    cv.setCardBackgroundColor(Color.parseColor("#00ff0000"));

                    cv.setElevation(8F);
                    cv.setMaxCardElevation(12F);
    //                cv.setBackgroundResource(R.drawable.infoborder);

                    int fI = i;
                    cv.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent intent = new Intent(getApplicationContext(), infoActivity.class);
                                                  intent.putExtra("dName",disease[fI]);
                                                  startActivity(intent);
                                                  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                              }
                                          }
                    );
                    cv.addView(createLayout(i));
                    munjin.addView(cv);
                }
            }
        }
        else if(nick!=null){
            loginCheck=1;
            headLine = (TextView)findViewById(R.id.headLine);
            headLine.setText(nick + "님 좋은 하루 보내세요");
            jindan.setText("문진표 메뉴를 통해\n진단이 필요합니다.");
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

    LinearLayout createLayout(int i){ //part를 받아 온 경우
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText(String.valueOf(i+1) + ". " + disease[i]);
        t1.setTextSize(16f);
        t1.setTextColor(Color.BLACK);

        tempLayout.addView(t1);


        return tempLayout;
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
        arduino = (Button)findViewById(R.id.arduino);



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
                if(nick!=null) {
                    Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                    intent.putExtra("nick", nick);
                    intent.putExtra("result", result);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                else{
                    Toast.makeText(getApplicationContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
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












