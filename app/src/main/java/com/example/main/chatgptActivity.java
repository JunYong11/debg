package com.example.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;


public class chatgptActivity extends AppCompatActivity {
    //chatgpt
    public static String answer = "";
    int cv;
    public static OpenAiService service = new OpenAiService("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");
    public static List<ChatMessage> message = new ArrayList<ChatMessage>();
    //layout

    chatData cd = new chatData();
    RelativeLayout.LayoutParams layoutParams;
    ScrollView scrollview;
    LinearLayout layout;
    Button btn;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatgpt);
        initializeView();
    }

    void initializeView(){
        setTitle("Q&A");
        layout = (LinearLayout) findViewById(R.id.root_layout2);
        edit = (EditText)findViewById(R.id.questionEdit);
        btn = (Button)findViewById(R.id.questionBtn);
        scrollview = ((ScrollView) findViewById(R.id.scv));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        edit.bringToFront();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sending();
            }
        });
        setting();
    }

    void setting(){
        for(int i=0;i<cd.count;i++){
            if(cd.cv[i].getParent() !=null){
                ((ViewGroup)cd.cv[i].getParent()).removeView(cd.cv[i]);
            }
            layout.addView(cd.cv[i]);
        }
    }

    void addQuestion(String temp){
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(450,16,16,50);

        CardView cv = new CardView(this);
        cv.setLayoutParams(layoutParams);
        cv.setRadius(12F);
        cv.setContentPadding(25,25,25,25);
        cv.setCardBackgroundColor(Color.rgb(0,170,255));
        cv.setElevation(8F);
        cv.setMaxCardElevation(12F);

        cv.addView(createLayoutQ(temp));
        layout.addView(cv);
        cd.cv[cd.count]=cv;
        cd.count++;
    }

    void addAnswer(){
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16,16,450,50);

        CardView cv = new CardView(this);
        cv.setLayoutParams(layoutParams);
        cv.setRadius(12F);
        cv.setContentPadding(25,25,25,25);
        cv.setCardBackgroundColor(Color.rgb(255,149,00));
        cv.setElevation(8F);
        cv.setMaxCardElevation(12F);

        cv.addView(createLayoutA());
        layout.addView(cv);
        cd.cv[cd.count]=cv;
        cd.count++;
    }
    void sending()  {
        String input= String.valueOf(edit.getText());
        addQuestion(input);
        addAnswer();
        scroll();
    }

    void scroll(){
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    LinearLayout createLayoutQ(String text){
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText(text);
        t1.setTextSize(17f);
        t1.setTextColor(Color.WHITE);

        tempLayout.addView(t1);

        return tempLayout;
    }

    LinearLayout createLayoutA(){

        String input= String.valueOf(edit.getText());
        edit.setText("");
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);

        t1.setTextSize(17f);

        t1.setTextColor(Color.WHITE);
        tempLayout.addView(t1);
        Toast myToast = Toast.makeText(this.getApplicationContext(),"조금만 기다려주세요...", Toast.LENGTH_SHORT);
        myToast.show();
        input(input, t1);
        return tempLayout;
    }

    public void input(String input, TextView t){
        message.add(new ChatMessage(ChatMessageRole.USER.value(), input));

        ChatCompletionRequest chatCompletionRequest;
        chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo").messages(message).n(1)
                .maxTokens(2000).logitBias(Collections.emptyMap()).build();
        // 대화 완료를 스트리밍할 Flowable 개체 만들기
        Flowable<ChatCompletionChunk> flowableResult = service.streamChatCompletion(chatCompletionRequest);
        // 결과를 저장할 StringBuilder 개체 생성
        StringBuilder buffer = new StringBuilder();
        // Flowable 객체를 확인하고 결과를 출력
        flowableResult.subscribe(chunk -> {
            chunk.getChoices().forEach(choice -> {
                String result = choice.getMessage().getContent();
                if (result != null) {
                    buffer.append(result);
                    System.out.print(choice.getMessage().getContent());
                    t.setText(t.getText() + choice.getMessage().getContent());
                    scroll();
                }
            });
        }, Throwable::printStackTrace, () -> System.out.print("\n>>")); //이걸로 t1갱신
        message.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), buffer.toString()));
    }







    public void close(){
        service.shutdownExecutor();
    }

    @Override
    public void finish() {
        super.finish();
        //finish, back버튼 누를 때 공통사항, 0.3초 사라지는 애니메이션
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}