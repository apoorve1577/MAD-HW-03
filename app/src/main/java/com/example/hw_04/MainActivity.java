package com.example.hw_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hw_04.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        FirstFrag.FirstFragInterface, SetProfileFrag.SetProfileFragInterface,
        AddDrinkFrag.AddDrinkInterface, ViewDrinksFrag.ViewDrinksFragInterface {


    ActivityMainBinding binding ;

    public ArrayList<Drink> allDrinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new FirstFrag(), "First Fragment")
                .commit();

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.set){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, new SetProfileFrag());
        }


    }

    @Override
    public void setButtonClicked() {
         getSupportFragmentManager().beginTransaction()
        .replace(binding.containerView.getId(), new SetProfileFrag(), "Set Profile")
                 .addToBackStack(null)
        .commit();

        Log.d("demo", "setButtonClicked: ");
    }

    @Override
    public void addDrinksClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.containerView.getId(), new AddDrinkFrag(), "AddDrink Profile")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void viewDrinksClicked(ArrayList<Drink> drinkArrayList) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.containerView.getId(), ViewDrinksFrag.newInstance(allDrinks), "ViewDrink Profile")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void resetClicked() {
        FirstFrag firstFrag =  (FirstFrag) getSupportFragmentManager()
                .findFragmentByTag("First Fragment");

        if(firstFrag != null) {
            firstFrag.reset();
        }
        allDrinks.clear();
        getSupportFragmentManager().popBackStack();


    }

    @Override
    public void weightSetButtonClicked(User user) {
        FirstFrag firstFrag =  (FirstFrag) getSupportFragmentManager()
                                .findFragmentByTag("First Fragment");
        if(firstFrag != null) {
            firstFrag.updateWeightGender(user);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelButtonClicked() {
        FirstFrag firstFrag =  (FirstFrag) getSupportFragmentManager()
                .findFragmentByTag("First Fragment");

        if(firstFrag != null) {
            firstFrag.reset();
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAddDrinkClick(Drink drink) {

        allDrinks.add(drink);
        FirstFrag firstFrag =  (FirstFrag) getSupportFragmentManager()
                .findFragmentByTag("First Fragment");

        if(firstFrag != null) {
            firstFrag.updateDrinks(allDrinks);
        }

        getSupportFragmentManager().popBackStack();

    }

    @Override
    public void onCancel() {
//        FirstFrag firstFrag =  (FirstFrag) getSupportFragmentManager()
//                .findFragmentByTag("First Fragment");
//        if(firstFrag != null){
//            firstFrag.updateDrinks(allDrinks);
//        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void closeButtonCLicked(ArrayList<Drink> drinkArrayList) {
        allDrinks = drinkArrayList;
        FirstFrag firstFrag =  (FirstFrag) getSupportFragmentManager()
                .findFragmentByTag("First Fragment");

        if(firstFrag != null) {
            firstFrag.updateDrinks(allDrinks);
        }
        getSupportFragmentManager().popBackStack();
    }
}