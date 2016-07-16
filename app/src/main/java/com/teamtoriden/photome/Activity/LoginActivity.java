package com.teamtoriden.photome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.teamtoriden.photome.R;

public class LoginActivity extends AppCompatActivity {

    String myId;
    String myPassword;
    EditText idText;
    EditText pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //id, pw 미리 123123로 정해둠
        myId = "123123";
        myPassword = "123123";

        idText = (EditText) findViewById(R.id.idInput);
        pwText = (EditText) findViewById(R.id.pwInput);

        //비밀번호는 *모양으로 표시
        PasswordTransformationMethod passwdtm = new PasswordTransformationMethod();
        pwText.setTransformationMethod(passwdtm);
    }


    public boolean loginCheck(String id, String pw) {

        if (idText.getText().toString().equals(id) && pwText.getText().toString().equals(pw)) {
            return true;
        } else {
            return false;
        }
    }

    public void LoginClicked(View view) {

        //isCheckTrue에 loginCheck함수에서 나온 결과 대입


        if (!loginCheck(myId, myPassword)) {
            Toast.makeText(LoginActivity.this, "The email and password you entered don't match", Toast.LENGTH_SHORT).show();
        } else {
            //MainActivity로 이동
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void SignupClicked(View view) {
        Intent intent2 = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent2);
    }
}