package com.teamtoriden.photome.Activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.teamtoriden.photome.R;

public class SignupActivity extends AppCompatActivity {


    //비밀번호, 비밀번호확인 일치 불일치 판별해주는 코드
    EditText Pw;
    EditText confirmPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Pw = (EditText) findViewById(R.id.editPassword);
        confirmPw = (EditText) findViewById(R.id.editConfirm);
        PasswordTransformationMethod passwdtm = new PasswordTransformationMethod();

        //비밀번호는 *모양으로 표시
        Pw.setTransformationMethod(passwdtm);
        confirmPw.setTransformationMethod(passwdtm);

        //saymael으로 폰트 변경
        EditText View1 = (EditText) findViewById(R.id.editUsername);
        View1.setTypeface(Typeface.createFromAsset(getAssets(), "saymael.ttf"));

        EditText View2 = (EditText) findViewById(R.id.editPassword);
        View2.setTypeface(Typeface.createFromAsset(getAssets(), "saymael.ttf"));

        EditText View3 = (EditText) findViewById(R.id.editConfirm);
        View3.setTypeface(Typeface.createFromAsset(getAssets(), "saymael.ttf"));

        Button Button1 = (Button) findViewById(R.id.button);
        Button1.setTypeface(Typeface.createFromAsset(getAssets(), "saymael.ttf"));

    }

    public void checkPwconfirmPw() {
        if (Pw.getText().toString().equals(confirmPw.getText().toString())) {
            //로그인 액티비티로 이동(아이디, 비밀번호 공백으로 해줘야함->로그인 액티비티에서 id,pw 모두 공백으로 해둠)
            finish();
        } else {
            //패스워드가 일치하지 않을때 뜨는 경고메시지
            Toast.makeText(SignupActivity.this, "The password you entered don't match", Toast.LENGTH_SHORT).show();
        }
    }

    public void NextstepClicked(View view) {
        checkPwconfirmPw();
    }
}
