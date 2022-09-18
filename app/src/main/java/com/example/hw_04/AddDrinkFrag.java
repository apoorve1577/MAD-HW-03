package com.example.hw_04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.hw_04.databinding.FragmentAddDrinkBinding;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDrinkFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDrinkFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int percent = 0;
    int size =1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddDrinkFrag() {
        // Required empty public constructor
    }

    FragmentAddDrinkBinding binding ;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDrinkFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDrinkFrag newInstance(String param1, String param2) {
        AddDrinkFrag fragment = new AddDrinkFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_add_drink, container, false);
    binding = FragmentAddDrinkBinding.inflate(inflater,container,false);
    return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.radioGroupDrink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == binding.oz1.getId()){
                size =1;
                }
                if(i == binding.oz5.getId()){
                size =5;
                }
                if(i == binding.oz12.getId()){
                size =12;
                }
            }
        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.progress.setText(i+"%");
                percent = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.addDrinkBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drink drink = new Drink(size, percent,new Date());
                addDrinkInterface.onAddDrinkClick(drink);
            }
        });

        binding.drinkCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDrinkInterface.onCancel();
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AddDrinkInterface){
            addDrinkInterface = (AddDrinkInterface)context;
        }
    }


    AddDrinkInterface addDrinkInterface ;

    public interface AddDrinkInterface{

        void onAddDrinkClick(Drink drink);
        void onCancel();

    }

}