package com.teamtoriden.photome.Activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.teamtoriden.photome.R;

public class IntroduceActivity extends AppCompatActivity {

    TextView contentName;
    TextView contentDescription;
    Image contentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");

        contentName = (TextView) findViewById(R.id.content_name);
        contentDescription = (TextView) findViewById(R.id.content_description);

        contentName.setText(name);
        contentDescription.setText(description);
    }

}
