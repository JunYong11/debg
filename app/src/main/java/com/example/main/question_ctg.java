package com.example.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class question_ctg extends AppCompatActivity {
    String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_ctg);

        Intent intent = getIntent();
        if (intent != null) {
            result = intent.getStringArrayExtra("result"); // result 배열 데이터를 가져옴
            // result 배열을 사용하여 필요한 작업을 수행
            displayResult();
        }
    }

    private void displayResult() {
        LinearLayout ll = findViewById(R.id.ll_result);

        for (String data : result) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            textView.setText(data);

            ll.addView(textView);
        }
    }



    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}