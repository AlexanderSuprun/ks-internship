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
 * Shows detailed information about a cat selected in {@link FragmentChooser}
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewer extends Fragment {

    private AppCompatTextView textView;

    public FragmentViewer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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