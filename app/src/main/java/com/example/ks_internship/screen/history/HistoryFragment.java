package com.example.ks_internship.screen.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseFragment;
import com.example.ks_internship.utils.adapter.HistoryRecyclerAdapter;

public class HistoryFragment extends BaseFragment implements HistoryContract.View {

    private RecyclerView recyclerView;
    private HistoryRecyclerAdapter adapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initToolbarWithClearHistoryAction(getString(R.string.toolbar_title_search_history), view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {

    }
}
