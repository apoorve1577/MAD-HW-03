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
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    ListView drinksList;
    DrinksAdapter adapter;

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

        drinksList = binding.drinksListView;
        adapter = new DrinksAdapter(getContext(), R.layout.drink_row_item, allDrinks);
        drinksList.setAdapter(adapter);
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


    }

    public void setView(int currentIndex) {


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