package com.teamtoriden.photome.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.teamtoriden.photome.R;

import java.util.Arrays;
import java.util.List;

public class StatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private Bitmap shareImage;
    private Button fbButton;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_status, container, false);
        Context context = view.getContext();

        fbButton = (Button) view.findViewById(R.id.fbbutton);
        fbButton.setOnClickListener(mClickListener);

        FacebookSdk.sdkInitialize(view.getContext());


        return view;
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            shareImage = BitmapFactory.decodeResource(view.getResources(), R.drawable.facebookshare);
            callbackManager = CallbackManager.Factory.create();

            List<String> permissionNeeds = Arrays.asList("publish_actions");

            //this loginManager helps you eliminate adding a LoginButton to your UI
            loginManager = LoginManager.getInstance();

            loginManager.logInWithPublishPermissions(getActivity(), permissionNeeds);

            loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    SharePhoto photo = new SharePhoto.Builder().setBitmap(shareImage).setCaption("wow").build();
                    SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();

                    ShareApi.share(content, null);

                    Toast.makeText(view.getContext(),"페이스북 공유가 완료되었습니다!", Toast.LENGTH_SHORT).show();

                    Log.d("please","share!!");
                }

                @Override
                public void onCancel() {
                    Log.d("please","no!!");
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.d("please","error!!");
                }
            });


        }
    };

}
