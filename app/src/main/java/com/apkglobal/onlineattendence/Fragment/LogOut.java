package com.apkglobal.onlineattendence.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apkglobal.onlineattendence.R;
import com.apkglobal.onlineattendence.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Mayank on 7/21/2017.
 */

public class LogOut extends Fragment {

    Button btn_logout;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logout, container, false);

        btn_logout=(Button)view.findViewById(R.id.btn_logout);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity

                    Intent toLogin=new Intent(getActivity(),LoginActivity.class);
                    toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    toLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(toLogin);

                }
            }
        };
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();

            }
        });
        return view;
    }


    private void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);

        }
    }
}
