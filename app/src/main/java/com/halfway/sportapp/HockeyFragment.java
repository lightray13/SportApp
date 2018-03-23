package com.halfway.sportapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HockeyFragment extends Fragment {

    RecyclerView recyclerView;
    List<CategoryItem> items;
    public static RequestInterface requestInterface;

    public HockeyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hockey, container, false);
        requestInterface = Controller.getApi();
        items = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SportAdapter adapter = new SportAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);

        requestInterface.getCategory("hockey").enqueue(new Callback<CategoryItem>() {
            @Override
            public void onResponse(Call<CategoryItem> call, Response<CategoryItem> response) {
                for (int i = 0; i < response.body().getEvents().size(); i++) {
                    items.add(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoryItem> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }



}
