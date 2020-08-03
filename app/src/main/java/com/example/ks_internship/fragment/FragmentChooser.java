package com.example.ks_internship.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.ks_internship.R;
import com.example.ks_internship.utils.KeyboardUtils;
import com.example.ks_internship.utils.adapter.GitRepoRecyclerAdapter;
import com.example.ks_internship.utils.listeners.OnGitRepoSearchAction;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChooser extends Fragment {

    private RecyclerView recyclerView;
    private View loaderBlock;

    private AppCompatEditText usernameInput;
    private AppCompatButton btnSearch;
    private GitRepoRecyclerAdapter adapter;
    private OnGitRepoSearchAction searchListener;

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        setListeners();
        handleSearchAction();
    }

    public void setAdapter(GitRepoRecyclerAdapter gitRepoRecyclerAdapter) {
        this.adapter = gitRepoRecyclerAdapter;
    }

    public void setSearchListener(OnGitRepoSearchAction listener) {
        this.searchListener = listener;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.fragment_chooser_recycler_view);
        loaderBlock = view.findViewById(R.id.fragment_chooser_loader_block);
        usernameInput = view.findViewById(R.id.fragment_chooser_et_username);
        btnSearch = view.findViewById(R.id.fragment_chooser_btn_search);
    }

    private void handleSearchAction() {
        if (TextUtils.isEmpty(usernameInput.getText().toString())) {
            usernameInput.requestFocus();
        } else {
            KeyboardUtils.hide(usernameInput);
            searchListener.onSearchButtonClick(usernameInput);
        }
    }

    private void setListeners() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSearchAction();
            }
        });

        usernameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    handleSearchAction();
                    return true;
                }
                return false;
            }
        });
    }

    public void showLoaderBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoaderBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.GONE);
        }
    }

    public void setUsernameInput(String username) {
        usernameInput.setText(username);
    }
}