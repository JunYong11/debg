package com.example.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class healthInfoActivity extends AppCompatActivity {
    RelativeLayout.LayoutParams layoutParams;

    titleData td = new titleData();
    String[] tName = new String[]{"가슴","골반","귀","기타","눈","다리","등/허리","머리","목","발","배","생식기","손","얼굴","엉덩이","유방","입","전신","코","팔","피부"};;
    String searchList[];
    LinearLayout layout;
    ScrollView sv;
    Button pre, next;
    TextView pageNum;
    LinkedHashSet hs;

    String t,result , cut="←";
    int count, page, nowPage=0;
    int check=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hs = new LinkedHashSet();
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
        sv = (ScrollView)findViewById(R.id.scrollView2);
        pre = (Button)findViewById(R.id.preBtn);
        next = (Button)findViewById(R.id.nextBtn);
        pageNum = (TextView)findViewById(R.id.pageText);
        
        //여기서부터 버튼 이벤트 리스너 작성
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nowPage<=0){
                    Toast.makeText(getApplicationContext(), "첫페이지 입니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    nowPage -= 1;
                    String temp = String.valueOf(nowPage + 1);
                    pageNum.setText(temp);
                    paging();

                    sv.fullScroll(ScrollView.FOCUS_UP);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page<=nowPage){
                    Toast.makeText(getApplicationContext(), "마지막 페이지입니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    nowPage += 1;
                    String temp = String.valueOf(nowPage +1);
                    pageNum.setText(temp);
                    paging();

                    sv.fullScroll(ScrollView.FOCUS_UP);
                }
            }
        });
        
        //여기서부터 로드 및 동적 뷰 생성
        layout = (LinearLayout) findViewById(R.id.root_layout);
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16,16,16,50);


        TextView tv = (TextView)findViewById(R.id.titleName);
        tv.setTextSize(30);
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(null, Typeface.BOLD);

        //어떤 part에 해당하는지 찾는 반복문
        for(int i=0;i<tName.length;i++){
            if(tName[i].equals(t)){
                check=i;
            }
        }
        pageCount();
        if(check==-1){ //검색을 통해 list를 받은 경우
            tv.setText("검색");
            if(result.equals("")){ //검색결과가 비어있는 경우
                Toast.makeText(getApplicationContext(), "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                setCardViewList();
            }
        }

        else { //part를 받아온 경우
            tv.setText(tName[check]);
            setCardViewPart();
        }


    }


    void paging(){ //페이징 함수
        layout.removeAllViews();
        if(check==-1){
            setCardViewList();
        }
        else {
            setCardViewPart();
        }
    }


    void setCardViewList(){
        Iterator it = hs.iterator();
        for(int i=0;i<(nowPage)*20;i++){ //페이지가 넘어간 만큼 인덱스를 밀기 위함
            it.next();
        }

        for(int i=(nowPage*20);i<(nowPage*20)+20;i++){
            if(hs.size()<=i){ //마지막 페이지에서 20개가 되지않을 경우 발생하는 오류를 방지
                break;
            }
            CardView cv = new CardView(this);
            cv.setLayoutParams(layoutParams);
            cv.setRadius(12F);
            cv.setContentPadding(25,25,25,25);
            cv.setCardBackgroundColor(Color.rgb(0,170,255));
            cv.setElevation(8F);
            cv.setMaxCardElevation(12F);
            cv.setBackgroundResource(R.drawable.infoborder);

            String output = (String)it.next();
            cv.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), infoActivity.class);
                                          intent.putExtra("dName", output);

                                          startActivity(intent);
                                          overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                      }
                                  }
            );
            cv.addView(createLayoutList(output));
            layout.addView(cv);
        }
    }

    void setCardViewPart(){
        for(int i=(nowPage*20);i<(nowPage*20)+20;i++){
            if(td.title[check].length<=i){ //마지막 페이지에서 20개가 되지않을 경우 발생하는 오류를 방지
                break;
            }
            CardView cv = new CardView(this);
            cv.setLayoutParams(layoutParams);
            cv.setRadius(12F);
            cv.setContentPadding(25,25,25,25);
            cv.setCardBackgroundColor(Color.rgb(0,170,255));
            cv.setElevation(8F);
            cv.setMaxCardElevation(12F);
            cv.setBackgroundResource(R.drawable.infoborder);


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


    LinearLayout createLayoutList(String text){ //list를 받아 온 경우
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText(text);
        t1.setTextSize(24f);
        t1.setTextColor(Color.BLACK);

        tempLayout.addView(t1);


        return tempLayout;
    }

    LinearLayout createLayout(int i){ //part를 받아 온 경우
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText(td.title[check][i]);
        t1.setTextSize(24f);
        t1.setTextColor(Color.BLACK);

        tempLayout.addView(t1);


        return tempLayout;
    }

    void pageCount(){ //page계산식
        if(check==-1){
            count = hs.size();
            page = count / 20 - 1;
        }
        else {
            count = td.title[check].length;
            page = count / 20 - 1;
        }

        if(count%20!=0){
            page+=1;
        }
        
    }


    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
