package com.example.ks_internship.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.utils.listeners.CatSelectListener;

/**
 * A simple {@link Fragment} subclass.
 * Controls the actions of the buttons in fragment_chooser.xml
 */
public class FragmentChooser extends Fragment {

    private AppCompatButton coconutButton;
    private AppCompatButton mortyButton;
    private AppCompatButton woodyButton;
    private AppCompatButton cakeButton;
    private AppCompatButton marcusButton;

    private CatSelectListener catSelectListener;

    public FragmentChooser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);

        initViews(view);
        setListeners();

        return view;
    }

    private void initViews(View view) {
        coconutButton = view.findViewById(R.id.fragment_chooser_btn_coconut);
        mortyButton = view.findViewById(R.id.fragment_chooser_btn_morty);
        woodyButton = view.findViewById(R.id.fragment_chooser_btn_woody);
        cakeButton = view.findViewById(R.id.fragment_chooser_btn_cake);
        marcusButton = view.findViewById(R.id.fragment_chooser_btn_marcus);
    }

    private void setListeners() {
        coconutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catSelectListener != null)
                    catSelectListener.onCoconutSelected();
            }
        });

        mortyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catSelectListener != null)
                    catSelectListener.onMortySelected();
            }
        });

        woodyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catSelectListener != null)
                    catSelectListener.onWoodySelected();
            }
        });

        cakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catSelectListener != null)
                    catSelectListener.onCakeSelected();
            }
        });

        marcusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catSelectListener != null)
                    catSelectListener.onMarcusSelected();
            }
        });
    }

    public void setCatSelectListener(CatSelectListener listener) {
        this.catSelectListener = listener;
    }
}