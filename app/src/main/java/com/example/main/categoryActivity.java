package com.example.main;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.theokanning.openai.image.Image;

import java.util.concurrent.ExecutionException;

public class categoryActivity extends AppCompatActivity {
    Button bArr[] = new Button[21];
    String rank[], rankList[];
    titleData td = new titleData();
    ConstraintLayout clay,btnClay;
    LinearLayout rankLayout;
    RelativeLayout.LayoutParams layoutParams;

    int check=0;


    protected void onCreate(Bundle savedInstanceState){
        ActionBar bar = getSupportActionBar();
        bar.hide();
        System.setProperty( "https.protocols", "TLSv1.1,TLSv1.2" );
        check = getIntent().getIntExtra("check", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        initializeView();
    }

    void initializeView(){
        clay = (ConstraintLayout)findViewById(R.id.CLay);
        btnClay = (ConstraintLayout)findViewById(R.id.btnClay);
        rankLayout = (LinearLayout)findViewById(R.id.rankLayout);
        ImageButton search = (ImageButton)findViewById(R.id.searchBtn);
        Button sBtn = (Button)findViewById(R.id.sBtn);
        EditText key = (EditText)findViewById(R.id.editKey);
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30,20,30,20);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check==0){
                    ranking();
                    //검색창과 검색버튼 표시
                    clay.bringToFront();
                    clay.invalidate();
                    clay.setVisibility(View.VISIBLE);
                    //btnClay를 invisible화해서 기존 카테고리를 제거하고, 랭킹리스트를 띄움
                    btnClay.setVisibility(View.INVISIBLE);
                    rankLayout.removeAllViews(); //앞서 작성된 텍스트뷰를 제거하기 위함

                    for(int i=0;i<rank.length;i++){
                        rankList(i);
                    }

                    check=1;
                    rankLayout.setVisibility(View.VISIBLE);
                }

                else{
                    clay.setVisibility(View.INVISIBLE);
                    btnClay.setVisibility(View.VISIBLE);
                    rankLayout.setVisibility(View.INVISIBLE);
                    check=0;
                }
            }
        });


        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                String temp = String.valueOf(key.getText());
                for(int i=0;i<td.title.length;i++){
                    for(int j=0;j<td.title[i].length;j++){
                        if(td.title[i][j].contains(temp)){
                            result+=td.title[i][j]+"←";
                        }
                    }
                }
                System.out.println(result);
                Intent intent = new Intent(getApplicationContext(), healthInfoActivity.class);
                intent.putExtra("list",result);

                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btnSetting();

        if(check==1){
            ranking();
            //검색창과 검색버튼 표시
            clay.bringToFront();
            clay.invalidate();
            clay.setVisibility(View.VISIBLE);
            //btnClay를 invisible화해서 기존 카테고리를 제거하고, 랭킹리스트를 띄움
            btnClay.setVisibility(View.INVISIBLE);
            rankLayout.removeAllViews(); //앞서 작성된 텍스트뷰를 제거하기 위함

            for(int i=0;i<rank.length;i++){
                rankList(i);
            }

            check=1;
            rankLayout.setVisibility(View.VISIBLE);
        }

    }


    void rankList(int index){
        if(index==0){ //title
            TextView tv = new TextView(getApplicationContext());
            tv.setText("자주 찾는 질병명");
            tv.setTextSize(35);
            tv.setTextColor(Color.BLACK);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setLayoutParams(layoutParams);
            tv.setBackgroundResource(R.drawable.infoborder);
            rankLayout.addView(tv);
        }

        TextView tv = new TextView(getApplicationContext());
        tv.setText(rankList[index]);
        tv.setTextSize(20);
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setLayoutParams(layoutParams);
        tv.setBackgroundColor(Color.parseColor("#00ff0000"));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), infoActivity.class);
                intent.putExtra("dName", rank[index]);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        rankLayout.addView(tv);
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



    void ranking(){
        String cut="㉾";
        try {
            rankingActivity task = new rankingActivity();
            String result = task.execute("start").get();
            rank = result.split(cut);

            rankList = new String[rank.length];
            for(int i=0;i<rank.length;i++){
                rankList[i] = String.valueOf(i+1) + ". " + rank[i];
            }

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "서버가 종료되어 있습니다.", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onBackPressed() { // 뒤로가기 버튼눌렀을 때 랭킹리스트가 떠있는 상태면 꺼지지 않고 원래 모습으로 되돌림
        if(check==1){
            clay.setVisibility(View.INVISIBLE);
            btnClay.setVisibility(View.VISIBLE);
            rankLayout.setVisibility(View.INVISIBLE);
            check=0;
        }
        else {
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
