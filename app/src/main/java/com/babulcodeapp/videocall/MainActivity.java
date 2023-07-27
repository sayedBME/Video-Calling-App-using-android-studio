package com.babulcodeapp.videocall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText codeBox;
    Button  joinBtn, shareBtn, demoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        codeBox = findViewById(R.id.codeBox);
        joinBtn = findViewById(R.id.joinBtn);
        shareBtn = findViewById(R.id.shareBtn);


        try {
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setWelcomePageEnabled(false)
                    .setFeatureFlag("invite.enabled",false)
                    .setFeatureFlag("help.enabled",false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(codeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(MainActivity.this , options);
            }
        });
           shareBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                 String string = codeBox.getText().toString();
                 Intent intent=new Intent();
                 intent.setAction(intent.ACTION_SEND);
                 intent.putExtra(Intent.EXTRA_TEXT, string);
                 intent.setType("text/plain");
                 startActivity(intent);
               }
           });
                demoBtn= (Button) findViewById(R.id.demoBtn);
                demoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this , LoginActivity.class));
                     }
                });

    }


}