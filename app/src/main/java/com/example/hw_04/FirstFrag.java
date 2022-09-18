package com.example.hw_04;

import static android.text.TextUtils.isEmpty;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.hw_04.databinding.*;

import java.util.ArrayList;

public class FirstFrag extends Fragment {

    public String TAG = "demo";
    public int weight;
    public String gender="" ;
    public static double genderValue = 0.66;
    Boolean reset = false;


    ArrayList<Drink> allDrinks = new ArrayList<>();

    public FirstFrag(){

    }

    public static FirstFrag newInstance(){
    FirstFrag firstFrag = new FirstFrag();
     return firstFrag;
    }

    public FragmentFirstBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstFragInterface.setButtonClicked();
            }
        });

        binding.viewDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstFragInterface.viewDrinksClicked(allDrinks);
            }
        });

        binding.addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstFragInterface.addDrinksClicked();
            }
        });

        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstFragInterface.resetClicked();
            }
        });

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FirstFragInterface){
        firstFragInterface = (FirstFragInterface)context;
        }
    }

    FirstFragInterface firstFragInterface;

    public interface FirstFragInterface {
        void setButtonClicked();
        void addDrinksClicked();
        void viewDrinksClicked(ArrayList<Drink> allDrinks);
        void resetClicked();
    }

    public void updateWeightGender(User user){
        weight = user.weight;
        gender = user.gender;
        binding.addDrink.setEnabled(true);
    }

    public void updateDrinks(ArrayList<Drink> drinks){
        if(drinks.size()!=0){
            binding.viewDrinks.setEnabled(true);
        }
        allDrinks = drinks;

    }

    public void reset(){
        reset = true;
        resetFragment();
        reset = false;
    }


    public void resetFragment(){
        binding.weight.setBackgroundColor(Color.BLACK);
        binding.weight.setText("N/A");
        binding.drinkNumber.setBackgroundColor(Color.BLACK);
        binding.drinkNumber.setText("0");
        binding.bacValue.setBackgroundColor(Color.BLACK);
        binding.bacValue.setText("0.000");
        binding.status.setBackgroundColor(Color.GREEN);
        binding.status.setText("You are safe");
        binding.viewDrinks.setEnabled(false);
        binding.addDrink.setEnabled(false);
        allDrinks.clear();
    }


    @Override
    public void onResume() {
        super.onResume();
        if(reset){
            resetFragment();
        }

        if(allDrinks.size()>0){
            binding.viewDrinks.setEnabled(true);
        }

        if(!isEmpty(gender)) {
            binding.weight.setBackgroundColor(Color.BLACK);
            binding.weight.setText(weight + "(" + gender + ")");
        }

            binding.drinkNumber.setBackgroundColor(Color.BLACK);
            binding.drinkNumber.setText(allDrinks.size()+"");
            String bacValue = updateBAC();
            binding.bacValue.setBackgroundColor(Color.BLACK);
            binding.bacValue.setText(bacValue);
    }


    public String updateBAC() {

        Double a = 0.0;
        int dp = 0;
        for (Drink drink : allDrinks) {
            dp += drink.percent * drink.size;
        }
        a = Double.valueOf(dp / 100);
        Log.d(TAG, weight + " gender " + gender + " dp " + dp + "A " + a);
        Double bac = (dp * 5.14) / (weight * genderValue * 100);
        statusColor(bac);
        Log.d(TAG, " bac " + bac);
        bac = Math.round(bac * 1000.0) / 1000.0;
        String bacValue = String.valueOf(bac);
        return bacValue;
    }

    public void statusColor(Double bac) {
        String status = "You are safe";
        int color = Color.GREEN;
        if (bac <= 0.08) {
            status = "You are safe";
            color = Color.GREEN;
            binding.addDrink.setEnabled(true);
        }
        if (bac > 0.08 && bac <= 0.2) {
            status = "Be careful!";
            color = Color.rgb(204, 63, 18);
            binding.addDrink.setEnabled(true);
        }
        if (bac > 0.2) {
            status = "Over the limit";
            color = Color.rgb(255, 0, 0);
            binding.addDrink.setEnabled(false);
            Toast.makeText(this.getContext(), "No more Drinks For you", Toast.LENGTH_SHORT)
                    .show();
        }
         binding.status.setBackgroundColor(Color.BLACK);
         binding.status.setBackgroundColor(color);
         binding.status.setText(status);

    }



    @Override
    public void onStart() {
        super.onStart();
    }
}