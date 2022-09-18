package com.example.hw_04;

import static android.text.TextUtils.isEmpty;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hw_04.databinding.FragmentSetProfileBinding;


public class SetProfileFrag extends Fragment {


    public SetProfileFrag(){

    }

    public int weight=0;
    public String gender="female";

    FragmentSetProfileBinding binding ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_set_profile, container, false);

        binding =FragmentSetProfileBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof SetProfileFragInterface){
            setProfileFragInterface = (SetProfileFragInterface)context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.enterWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight = Integer.parseInt(binding.enterWeight.getText().toString());
                Log.d("demo", "onClick: ");
            }
        });

        binding.radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i == binding.male.getId()){
                    gender = "male";
                    FirstFrag.genderValue = 0.73;
                }
                if(i == binding.female.getId()){
                    gender = "female";
                }
            }
        });

        binding.weightGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = binding.enterWeight.getText().toString();
                if(!isEmpty(s) && onlyDigits(s,s.length())){
                    weight = Integer.parseInt(s);
                    User user = new User(gender,weight);
                    setProfileFragInterface.weightSetButtonClicked(user);
                }
                else{
                    Toast.makeText(getContext(), "Enter only numbers for weight", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProfileFragInterface.cancelButtonClicked();
            }
        });

    }
    public static Boolean onlyDigits(String str, int n)
    {

        for (int i = 0; i < n; i++) {

            if (str.charAt(i) < '0'
                    || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    SetProfileFragInterface setProfileFragInterface ;

public interface SetProfileFragInterface{

    void weightSetButtonClicked(User user);
    void cancelButtonClicked();

}













}