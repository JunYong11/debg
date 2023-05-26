package com.example.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class healthInfoActivity extends AppCompatActivity {
    RelativeLayout.LayoutParams layoutParams;

    titleData td = new titleData();
    String[] tName = new String[]{"가슴","골반","귀","기타","눈","다리","등/허리","머리","목","발","배","생식기","손","얼굴","엉덩이","유방","입","전신","코","팔","피부"};;
    String searchList[];
    LinkedHashSet hs = new LinkedHashSet();
    String t,result , cut="←";
    int check=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar bar = getSupportActionBar();
        bar.hide();
        t = getIntent().getStringExtra("part");
        result = getIntent().getStringExtra("list");
        //마지막 번지는 버림
        if(result!=null) {
            searchList = result.split(cut);
            for(int i=0;i<searchList.length;i++){
                hs.add(searchList[i]);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthinfo);

        initializeView();
    }

    void initializeView(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.root_layout);
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16,16,16,50);


        //어떤 part에 해당하는지 찾는 반복문
        for(int i=0;i<tName.length;i++){
            if(tName[i].equals(t)){
                check=i;
            }
        }

        if(check==-1){ //검색을 통해 list를 받은 경우
            Iterator it = hs.iterator();
            for(int i=0;i<hs.size();i++){
                CardView cv = new CardView(this);
                cv.setLayoutParams(layoutParams);
                cv.setRadius(12F);
                cv.setContentPadding(25,25,25,25);
                cv.setCardBackgroundColor(Color.rgb(0,170,255));
                cv.setElevation(8F);
                cv.setMaxCardElevation(12F);

                cv.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getApplicationContext(), infoActivity.class);
                                              intent.putExtra("dName", (String) it.next());

                                              startActivity(intent);
                                              overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                          }
                                      }
                );
                cv.addView(createLayoutList(i));
                layout.addView(cv);
            }
        }

        else { //part를 받아온 경우
            for(int i=0;i<td.title[check].length;i++){
                CardView cv = new CardView(this);
                cv.setLayoutParams(layoutParams);
                cv.setRadius(12F);
                cv.setContentPadding(25,25,25,25);
                cv.setCardBackgroundColor(Color.rgb(0,170,255));
                cv.setElevation(8F);
                cv.setMaxCardElevation(12F);


                int finalI = i;
                cv.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Intent intent = new Intent(getApplicationContext(), infoActivity.class);
                          intent.putExtra("dName",td.title[check][finalI]);

                          startActivity(intent);
                          overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                      }
                  }
                );
                cv.addView(createLayout(i));
                layout.addView(cv);
            }
        }


    }
    LinearLayout createLayoutList(int i){ //list를 받아 온 경우
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText(searchList[i]);
        t1.setTextSize(24f);
        t1.setTextColor(Color.WHITE);

        tempLayout.addView(t1);


        return tempLayout;
    }

    LinearLayout createLayout(int i){ //part를 받아 온 경우
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText(td.title[check][i]);
        t1.setTextSize(24f);
        t1.setTextColor(Color.WHITE);

        tempLayout.addView(t1);


        return tempLayout;
    }



    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
