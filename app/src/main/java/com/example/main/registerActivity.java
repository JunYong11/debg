package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;


public class registerActivity extends AppCompatActivity {
    Button registerBtn;
    EditText EditID,EditPW,EditAnswer,EditNickname;
    Spinner s;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        Spinner spn = findViewById(R.id.spn);

        ArrayAdapter spn_adapter = ArrayAdapter.createFromResource(this, R.array.list이름,android.R.layout.simple_spinner_item);

        spn_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(spn_adapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long I){
                String a = spn.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //여기서부터 회원가입 버튼
        setTitle("회원가입");

        registerBtn = (Button)findViewById(R.id.regButton);
        EditID = (EditText)findViewById(R.id.EditID);
        EditPW = (EditText)findViewById(R.id.EditPW);
        EditNickname = (EditText)findViewById(R.id.EditNickname);
        EditAnswer = (EditText)findViewById(R.id.EditAnswer);
        s = (Spinner)findViewById(R.id.spn);

        InputFilter filterAlphaNum = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
                if (!ps.matcher(source).matches()) {
                    return "";
                }
                return null;
            }
        };

        EditID.setFilters(new InputFilter[] {
                filterAlphaNum,
                new InputFilter.LengthFilter(12)
        });
        EditPW.setFilters(new InputFilter[] {
                filterAlphaNum,
                new InputFilter.LengthFilter(20)
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String result;
                    String id = EditID.getText().toString();
                    String pw = EditPW.getText().toString();
                    String nick = EditNickname.getText().toString();
                    String ans = EditAnswer.getText().toString();
                    String spn = (String) s.getSelectedItem();

                    if (id != null && pw != null && nick != null && ans != null) {
                        if(id.length()>=6){
                            regActivity task = new regActivity();
                            result = task.execute(id, pw, nick, ans, spn).get();
                            if (result.equals("이미 존재하는 아이디 입니다.")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "ID는 영문+숫자 조합으로 6자리~12자리로 구성하여야합니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "비어있는 칸을 채워주세요.",Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e){
                    Log.i("DBtest", ".....ERROR.....!");
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
