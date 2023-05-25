package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class questionActivity extends AppCompatActivity {
    String[] titleArr = {"가슴","골반","귀","기타","눈","다리","등/허리","머리","목",
            "발","배","생식기","손","얼굴","엉덩이","유방","입","전신","코","팔","피부"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.question);
            ActionBar bar = getSupportActionBar();
            que_select();
            bar.hide();
    }

    public void que_select(){
        Intent intent = getIntent();
        int num = intent.getIntExtra("번호",0);
        switch(num){
            case 0:
                set_question1();
                break;
            case 1:
                set_question2();
                break;
            case 2:
                set_question3();
                break;
            case 3:
                set_question4();
                break;
            case 4:
                set_question5();
                break;
            case 5:
                set_question6();
                break;
            case 6:
                set_question7();
                break;
            case 7:
                set_question8();
                break;
            case 8:
                set_question9();
                break;
            case 9:
                set_question10();
                break;
            case 10:
                set_question11();
                break;
            case 11:
                set_question12();
                break;
            case 12:
                set_question13();
                break;
            case 13:
                set_question14();
                break;
            case 14:
                set_question15();
                break;
            case 15:
                set_question16();
                break;
            case 16:
                set_question17();
                break;
            case 17:
                set_question18();
                break;
            case 18:
                set_question19();
                break;
            case 19:
                set_question20();
                break;
            case 20:
                set_question21();
                break;
        }
    }   //클릭한 질문지 출력함수

    void title(int num){
        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        Intent intent = getIntent();
        TextView tv = new TextView(this);
        tv.setTextSize(35);
        tv.setText(titleArr[num]+" "+"문진표");
        ll.addView(tv);
    }   //제목 출력 함수
    void question(String[] queArr){
        Intent intent = getIntent();
        int num = intent.getIntExtra("번호",0);
        title(num);
        int [] numArr = new int[queArr.length];
        for(int i =0;i<numArr.length;i++){
            numArr[i] = i+1;
        }

        TextView[] tvArr1 = new TextView[numArr.length];
        CheckBox[] cbArr1 = new CheckBox[numArr.length];

        for(int i =0;i<tvArr1.length; i++){
            LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
            tvArr1[i] = new TextView(this);
            cbArr1[i] = new CheckBox(this);

            LinearLayout rr = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            rr.setLayoutParams(layoutParams);
            ll.addView(rr);
            tvArr1[i].setTextSize(25);
            tvArr1[i].setText(numArr[i]+". "+queArr[i]+"있습니까?");
            rr.addView(tvArr1[i]);
            rr.addView(cbArr1[i]);
        }
    }   //질문지 출력 함수

    void set_question1(){
        String [] queArr1 = {"1","2","3"};
        question(queArr1);
    }   //가슴 질문지
    void set_question2(){


    }   //골반 질문지
    void set_question3(){

    }   //귀 질문지
    void set_question4(){


    }   //기타 질문지
    void set_question5(){

    }   //눈 질문지
    void set_question6(){


    }   //다리 질문지
    void set_question7(){


    }   //등/허리 질문지
    void set_question8(){


    }   //머리 질문지
    void set_question9(){


    }   //목 질문지
    void set_question10(){

    }   //발 질문지
    void set_question11(){


    }   //배 질문지
    void set_question12(){


    }   // 생식기 질문지
    void set_question13(){

    }   //손 질문지
    void set_question14(){

    }   //얼굴 질문지
    void set_question15(){


    }   //엉덩이 질문지
    void set_question16(){

    }   //유방 질문지
    void set_question17(){


    }   //입 질문지
    void set_question18(){


    }   //전신 질문지
    void set_question19(){


    }   //코 질문지
    void set_question20(){


    }   //팔 질문지
    void set_question21(){

    }   //피부 질문지

    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


}