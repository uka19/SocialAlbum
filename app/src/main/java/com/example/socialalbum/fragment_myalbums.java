package com.example.socialalbum;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_myalbums extends Fragment {

    private TextView username,name_surname;
    private View view;
    private CircleImageView profile_photo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myalbums,container,false);
        getIDs();
        getUserInfo();
        return  view;
    }
    private void getIDs(){
        username = view.findViewById(R.id.username);
        name_surname = view.findViewById(R.id.name_surname);
        profile_photo = view.findViewById(R.id.profile_photo);
    }
    private void getUserInfo(){
        username.setText(SharedPrefManager.getInstance(getActivity()).getUsername());
        name_surname.setText(SharedPrefManager.getInstance(getActivity()).getUserName()+" "+SharedPrefManager.getInstance(getActivity()).getUserSurname());
        Glide
                .with(getActivity())
                .load(SharedPrefManager.getInstance(getActivity()).getPhotoUrl())
                .centerCrop()
                .into(profile_photo);


    }

}
