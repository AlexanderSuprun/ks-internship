package com.example.ks_internship.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.NewCatActivity;
import com.example.ks_internship.model.Cat;
import com.example.ks_internship.utils.Constants;
import com.example.ks_internship.utils.adapter.CatRecyclerAdapter;
import com.example.ks_internship.utils.listeners.OnCatRecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Contains {@link RecyclerView} filled with {@link Cat}
 * Starts {@link NewCatActivity} to add new item.
 */
public class FragmentChooser extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    CatRecyclerAdapter adapter;
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

        recyclerView = view.findViewById(R.id.recycler_view);
        fabAdd = view.findViewById(R.id.fab_add_new_item);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null && adapter.getItems() != null) {
                    Intent intent = new Intent(getContext(), NewCatActivity.class);
                    startActivityForResult(intent, Constants.NEW_ITEM_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.NEW_ITEM_REQUEST_CODE) {
            if (data != null && data.getExtras() != null) {
                catArrayList.add((Cat) data.getParcelableExtra(Constants.KEY_CAT_OBJECT));
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(recyclerView.getBottom());
            }
        }
    }

    public void setAdapter(CatRecyclerAdapter catRecyclerAdapter) {
        this.adapter = catRecyclerAdapter;
        this.catArrayList = catRecyclerAdapter.getItems();
    }

}