package com.example.ks_internship.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.model.Cat;

/**
 * A simple {@link Fragment} subclass.
 * Displays detailed information about a cat selected in {@link FragmentChooser}
 */
public class FragmentViewer extends Fragment {

    private AppCompatTextView textView;
    private final static String INFO_ARG = "INFO_ARG";

    public FragmentViewer() {
        // Required empty public constructor
    }

    public static FragmentViewer newInstance(String info) {
        FragmentViewer fragmentViewer = new FragmentViewer();
        Bundle args = new Bundle();
        args.putString(INFO_ARG, info);
        fragmentViewer.setArguments(args);
        return fragmentViewer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            textView.setText(getArguments().getString(INFO_ARG));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        textView = view.findViewById(R.id.fragment_viewer_text_view);

        return view;
    }

    public void displayInformation(Cat cat) {
        textView.setText(cat.toString());
    }
}