package com.example.hw_04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hw_04.databinding.FragmentViewDrinksBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDrinksFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDrinksFrag extends Fragment {


    public ArrayList<Drink> allDrinks = new ArrayList<>();

    FragmentViewDrinksBinding binding;
    int currentIndex = 0;

    public ViewDrinksFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ViewDrinksFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrinksFrag newInstance(ArrayList<Drink> allDrinks) {
        ViewDrinksFrag fragment = new ViewDrinksFrag();
        Bundle args = new Bundle();
        args.putParcelableArrayList("allDrinks",  allDrinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allDrinks = getArguments().getParcelableArrayList("allDrinks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentViewDrinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        setView(currentIndex);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex--;
                Log.d("currentIndex", "" + currentIndex);
                if(currentIndex < 0) {
                    currentIndex = allDrinks.size()-1;
                }
                Log.d("currentIndex", "new Index after back" + currentIndex);

                setView(currentIndex);
            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("currentIndex", "" + currentIndex);
                currentIndex++;
                if(currentIndex > (allDrinks.size()-1)) {
                    currentIndex = 0;
                }
                Log.d("currentIndex", "new Index after next" + currentIndex);

                setView(currentIndex);
            }
        });

        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("currentIndex", "new Index before delete" + currentIndex);

                allDrinks.remove(currentIndex);
                currentIndex--;
                Log.d("currentIndex", "new Index after delete" + currentIndex);

                if(allDrinks.size() == 0) {
                    viewDrinksFragInterface.closeButtonCLicked(allDrinks);
                }
                else {
                    if (currentIndex < 0) {
                        currentIndex = allDrinks.size();
                    }
                    if (currentIndex > allDrinks.size() - 1) {
                        currentIndex = 0;
                    }
                    setView(currentIndex);
                }
            }
        });

        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDrinksFragInterface.closeButtonCLicked(allDrinks);
            }
        });



    }

    public void setView(int currentIndex) {

        binding.totalDrinks.setText("Drink " + (currentIndex+1) + " out of " + allDrinks.size());


        binding.alcoholPercent.setText(allDrinks.get(currentIndex).percent + "% Alcohol");
        binding.alcoholContent.setText(allDrinks.get(currentIndex).size + "Oz");

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");

        binding.addedTime.setText("Added " + format.format(allDrinks.get(currentIndex).createDatetime));
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ViewDrinksFragInterface){
            viewDrinksFragInterface = (ViewDrinksFragInterface)context;
        }
    }

    ViewDrinksFragInterface viewDrinksFragInterface;

    public interface ViewDrinksFragInterface{
        void closeButtonCLicked(ArrayList<Drink> allDrinks);

    }
}