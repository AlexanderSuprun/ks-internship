package com.example.ks_internship.screen.history;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.MainActivity;
import com.example.ks_internship.base.BaseFragment;
import com.example.ks_internship.utils.Constants;
import com.example.ks_internship.utils.adapter.HistoryRecyclerAdapter;
import com.example.ks_internship.utils.listener.OnHistoryRecyclerItemClickListener;

import java.util.ArrayList;

public class HistoryFragment extends BaseFragment implements HistoryContract.View {

    private RecyclerView recyclerView;
    private HistoryRecyclerAdapter adapter;
    private HistoryContract.Presenter presenter;
    private ArrayList<String> historyItems = new ArrayList<>();

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        initToolbarWithClearHistoryAction(getString(R.string.toolbar_title_search_history), view);
        recyclerView = view.findViewById(R.id.fragment_history_rv);

        adapter = new HistoryRecyclerAdapter(historyItems, new OnHistoryRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String name) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(Constants.HISTORY_TITLE, historyItems.get(position));
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        presenter.takeView(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void setHistoryItems(ArrayList<String> items) {
        historyItems.clear();
        historyItems.addAll(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
