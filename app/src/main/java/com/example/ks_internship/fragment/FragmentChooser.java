package com.example.ks_internship.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.model.Cat;
import com.example.ks_internship.utils.adapter.GitRepoRecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Contains {@link RecyclerView} filled with {@link Cat}
 */
public class FragmentChooser extends Fragment {

    RecyclerView recyclerView;
    GitRepoRecyclerAdapter adapter;
    ArrayList<Cat> catArrayList;

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

        recyclerView = view.findViewById(R.id.fragment_chooser_recycler_view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    public void setAdapter(GitRepoRecyclerAdapter gitRepoRecyclerAdapter) {
        this.adapter = gitRepoRecyclerAdapter;
        this.catArrayList = gitRepoRecyclerAdapter.getItems();
    }

}