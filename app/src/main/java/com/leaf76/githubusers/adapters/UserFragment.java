package com.leaf76.githubusers.adapters;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.leaf76.githubusers.R;
import com.leaf76.githubusers.databinding.UserFragmentBinding;
import com.leaf76.githubusers.models.Item;
import com.leaf76.githubusers.viewmodels.GithubViewModelFactory;
import com.leaf76.githubusers.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    public static final String TAG = "User";

    private UserFragmentBinding binding;

    private GithubViewModelFactory factory = new GithubViewModelFactory();

    private UserViewModel userViewModel;

    private UserAdapter userAdapter = new UserAdapter(new ArrayList<Item>());

    public static UserFragment newInstance() { return new UserFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserFragmentBinding.inflate(inflater, container, false);

        binding.textInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    doSearch();
                    return true;
                }
                return  false;
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });

//        binding.userList.setLayoutManager(new LinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL,false));

        binding.userList.setLayoutManager(new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL));

        binding.userList.setAdapter(userAdapter);

        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);

        binding.setUserViewModel(userViewModel);
        userViewModel.getUsers().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                userAdapter.swapItems(items);
            }
        });
    }

    private void doSearch(){
        String query = binding.textInput.getText().toString();
        if(TextUtils.isEmpty(query)){
            userAdapter.clearItems();
            return;
        }
        userViewModel.searchUser(query);
        dismissKeyboard();
    }

    private void dismissKeyboard() {
        View view = getActivity().getCurrentFocus();
        if(view != null){
            InputMethodManager imm =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }


}
