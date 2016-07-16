package com.teamtoriden.photome.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamtoriden.photome.Class.Place;
import com.teamtoriden.photome.R;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    String myId;
    String myPassword;
    EditText idText;
    EditText pwText;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    //private List<Place> placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("places");
        //prepareMessageData();

        myId = "";
        myPassword = "";

        idText = (EditText) findViewById(R.id.idInput);
        pwText = (EditText) findViewById(R.id.pwInput);

        //비밀번호는 *모양으로 표시
        PasswordTransformationMethod passwdtm = new PasswordTransformationMethod();
        pwText.setTransformationMethod(passwdtm);


        //saymael로 글꼴 변경
        Button button1 = (Button) findViewById(R.id.loginButton);
        button1.setTypeface(Typeface.createFromAsset(getAssets(), "saymael.ttf"));

        Button button2 = (Button) findViewById(R.id.signupButton);
        button2.setTypeface(Typeface.createFromAsset(getAssets(), "saymael.ttf"));

        CheckBox box = (CheckBox) findViewById(R.id.checkBox);
        box.setTypeface(Typeface.createFromAsset(getAssets(), "saymael.ttf"));

        //String image1 = "traditionalmaket";
        /*Drawable image2 = getResources().getDrawable(R.drawable.ganmonbeach);
        Drawable image3 = getResources().getDrawable(R.drawable.hanbandow);
        Drawable image4 = getResources().getDrawable(R.drawable.ongsim);
        Drawable image5 = getResources().getDrawable(R.drawable.skijump);*/

        //writeNewPlace("전통 시장", "전통시장입니다 먹거리가득", image1,false,0,0);
        /*writeNewPlace("콧등", "콧등입니니다", image2,false,0,0);
        writeNewPlace("콧등", "콧등입니니다", image3,false,0,0);
        writeNewPlace("콧등", "콧등입니니다", image4,false,0,0);
        writeNewPlace("콧등", "콧등입니니다", image5,false,0,0);*/


    }

    private void writeNewPlace(String name, String description, String image, boolean flag, double longitude, double latitude) {
        Place place = new Place(name, description, image, flag, longitude, latitude);
        myRef.push().setValue(place);
    }


    public boolean loginCheck(String id, String pw) {
        return idText.getText().toString().equals(id) && pwText.getText().toString().equals(pw);
    }

    public void LoginClicked(View view) {
        if (!loginCheck(myId, myPassword)) {
            Toast.makeText(LoginActivity.this, "아이디와 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
        } else {
            //MainActivity로 이동
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void SignupClicked(View view) {
        //SignupActivity로 이동
        Intent intent2 = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent2);
    }
}