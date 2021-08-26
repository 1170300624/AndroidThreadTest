package com.example.androidthreadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.thread_text);
        btn = findViewById(R.id.thread_button);

        MyThread thread = new MyThread();
        Thread th = new Thread(thread);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                th.start();
            }
        });
    }

    public class MyThread implements Runnable {

        private final int updateText = 1;
        private Handler handler;

        @Override
        public void run() {

            Message message = new Message();
            message.what = updateText;
            handler = new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == updateText){

                        txt.setText("Nice to meet you.");

                    }

                }
            };
            handler.sendMessage(message);

        }
    }
}