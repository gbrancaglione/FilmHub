package com.example.filmhub;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;

import static android.support.constraint.Constraints.TAG;

public class ProfileActivity extends Fragment {
    public Button buttonEditProfile;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,  Bundle savedInstance ){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstance);

       /* buttonEditProfile = getView().findViewById(R.id.buttonEdit);
        buttonEditProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileActivity()).commit();
            }
        });*/
        return inflater.inflate(R.layout.activity_profile,container,false);

    }

}
