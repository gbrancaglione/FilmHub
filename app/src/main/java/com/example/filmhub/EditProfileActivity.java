package com.example.filmhub;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstance) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstance);

        return inflater.inflate(R.layout.activity_edit_profile, container, false);

    }
}
