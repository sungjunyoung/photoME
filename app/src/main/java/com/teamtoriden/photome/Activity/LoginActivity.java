package com.teamtoriden.photome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.teamtoriden.photome.R;

import static android.graphics.Typeface.createFromAsset;

public class LoginActivity extends AppCompatActivity {

    String myId;
    String myPassword;
    EditText idText;
    EditText pwText;
    CheckBox box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myId = "";
        myPassword = "";

        idText = (EditText) findViewById(R.id.idInput);
        pwText = (EditText) findViewById(R.id.pwInput);
        box = (CheckBox) findViewById(R.id.checkBox);

        //비밀번호는 *모양으로 표시
        PasswordTransformationMethod passwdtm = new PasswordTransformationMethod();
        pwText.setTransformationMethod(passwdtm);

        //saymael로 글꼴 변경
        Button button1 = (Button) findViewById(R.id.loginButton);
        button1.setTypeface(createFromAsset(getAssets(), "saymael.ttf"));
        Button button2 = (Button) findViewById(R.id.signupButton);
        button2.setTypeface(createFromAsset(getAssets(), "saymael.ttf"));
        box.setTypeface(createFromAsset(getAssets(), "saymael.ttf"));
        idText.setTypeface(createFromAsset(getAssets(), "saymael.ttf"));
        pwText.setTypeface(createFromAsset(getAssets(), "saymael.ttf"));
    }

    public boolean loginCheck(String id, String pw) {
        return idText.getText().toString().equals(id) && pwText.getText().toString().equals(pw);
    }

    public void LoginClicked(View view) {
        if (!loginCheck(myId, myPassword)) {
            Toast.makeText(LoginActivity.this, "로그인 정보가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
        } else {
            //MainActivity로 이동
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void SignupClicked(View view) {
        //SignupActivity로 이동
        Intent intent1 = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent1);
    }
}