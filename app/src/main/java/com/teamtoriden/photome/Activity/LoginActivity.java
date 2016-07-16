package com.teamtoriden.photome.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

        myId = "123123";
        myPassword = "123123";

        idText = (EditText) findViewById(R.id.idInput);
        pwText = (EditText) findViewById(R.id.pwInput);

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

}