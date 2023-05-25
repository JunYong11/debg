package com.example.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class question_ctg extends AppCompatActivity {
    Button[] btn = new Button[21];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_ctg);
        btnClick();
    }
    public void btnClick(){

        btn[0] = (Button) findViewById(R.id.angry_btn1);
        btn[1] = (Button) findViewById(R.id.angry_btn2);
        btn[2] = (Button) findViewById(R.id.angry_btn3);
        btn[3]  = (Button) findViewById(R.id.angry_btn4);
        btn[4] = (Button) findViewById(R.id.angry_btn5);
        btn[5]= (Button) findViewById(R.id.angry_btn6);
        btn[6] = (Button) findViewById(R.id.angry_btn7);
        btn[7]= (Button) findViewById(R.id.angry_btn8);
        btn[8] = (Button) findViewById(R.id.angry_btn9);
        btn[9] = (Button) findViewById(R.id.angry_btn10);
        btn[10] = (Button) findViewById(R.id.angry_btn11);
        btn[11] = (Button) findViewById(R.id.angry_btn12);
        btn[12]= (Button) findViewById(R.id.angry_btn13);
        btn[13] = (Button) findViewById(R.id.angry_btn14);
        btn[14] = (Button) findViewById(R.id.angry_btn15);
        btn[15]= (Button) findViewById(R.id.angry_btn16);
        btn[16] = (Button) findViewById(R.id.angry_btn17);
        btn[17] = (Button) findViewById(R.id.angry_btn18);
        btn[18] = (Button) findViewById(R.id.angry_btn19);
        btn[19] = (Button) findViewById(R.id.angry_btn20);
        btn[20]= (Button) findViewById(R.id.angry_btn21);

        btn[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",0);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",1);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",2);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",3);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",4);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",5);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[6].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",6);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[7].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",7);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[8].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",8);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[9].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",9);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[10].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",10);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[11].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",11);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[12].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",12);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[13].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",13);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[14].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",14);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[15].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",15);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[16].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",16);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[17].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",17);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[18].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",18);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[19].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",19);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        btn[20].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), questionActivity.class);
                intent.putExtra("번호",20);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
    }









    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}