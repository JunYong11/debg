package com.example.main;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.concurrent.ExecutionException;

public class infoActivity extends AppCompatActivity {
    RelativeLayout.LayoutParams layoutParams;
    String t, result[];
    String[] title = new String[]{"질환명","정의","원인","증상","진단","치료","경과","주의사항"};

    protected void onCreate(Bundle savedInstanceState){
        ActionBar bar = getSupportActionBar();
        bar.hide();
        t = getIntent().getStringExtra("dName");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        initialView();
    }

    void initialView(){
        duration();
        try {
            LinearLayout layout = (LinearLayout) findViewById(R.id.root_layout3);

            healthActivity task = new healthActivity();
            result = task.execute(t).get();
            //result[0]~result[7]

            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(16,16,16,50);

            for(int i=3;i<result.length;i++){
                if(!result[i].equals("null")){
                    System.out.println(result[i]);
                    CardView cv = new CardView(this);
                    cv.setLayoutParams(layoutParams);
                    cv.setRadius(12F);
                    cv.setContentPadding(25,25,25,25);
                    cv.setCardBackgroundColor(Color.rgb(0,170,255));
                    cv.setElevation(8F);
                    cv.setMaxCardElevation(12F);
                    cv.setBackgroundResource(R.drawable.infoborder);

                    cv.addView(createLayout(i));
                    layout.addView(cv);
                }
            }




        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    void duration(){
        durationActivity task = new durationActivity();
        try {
            String temp = task.execute(t).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    LinearLayout createLayout(int i){
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText(title[i-3]);
        t1.setTextSize(24f);
        t1.setTextAlignment(ViewGroup.TEXT_ALIGNMENT_CENTER);
        t1.setTypeface(null, Typeface.BOLD);
        t1.setTextColor(Color.BLACK);

        TextView t2 = new TextView(this);
        t2.setText(result[i]);
        t2.setTextSize(18f);
        t2.setTextColor(Color.BLACK);

        tempLayout.addView(t1);
        tempLayout.addView(t2);


        return tempLayout;
    }
    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
