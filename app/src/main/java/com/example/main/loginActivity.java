package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {
    Button login, find;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initializeView();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }

    public void initializeView(){
        setTitle("로그인");
        Button reg = (Button)findViewById(R.id.register);
        Button find = (Button)findViewById(R.id.find);
        login = (Button)findViewById(R.id.login);
        EditText EditID = (EditText)findViewById(R.id.ID);
        EditText EditPW = (EditText)findViewById(R.id.PW);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registerActivity.class);
                //intent.putExtra("email",email); << 이렇게 사용하면 키 값과 데이터 저장 가능
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    String result;
                    String[] nickname;
                    String ID = EditID.getText().toString();
                    String PW = EditPW.getText().toString();

                    if (!ID.equals("") && !PW.equals("")) {
                        loginProActivity task = new loginProActivity();
                        result = task.execute(ID, PW).get();

                        nickname = result.split("!");

                        if(nickname.length==2){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("nickname",nickname[1]);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                            finish();
                        }
                        else{
                            if (nickname[0].equals("로그인 성공")) {

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("nickname",nickname[1]);
                                if(nickname[2]!=null && !nickname[2].equals("null")){
                                    intent.putExtra("jindan", nickname[2]);
                                }
                                if(nickname[2].equals("null")){
                                    intent.putExtra("jindan", "의심되는 증상이 없습니다.");
                                }
                                startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }



                    }
                    else{
                        Toast.makeText(getApplicationContext(), "ID 또는 비밀번호가 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Log.i("DBtest", ".....ERROR.....!");
                    Toast.makeText(getApplicationContext(), "DB연결 에러발생", Toast.LENGTH_SHORT).show();
                }
            }
        });



        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), findActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
    }
}
