package com.example.ivandimitrov.recepiestask.brewery;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivandimitrov.recepiestask.R;
import com.example.ivandimitrov.recepiestask.fragments.PageFragment;
import com.example.ivandimitrov.recepiestask.retrofit.ApiClient;
import com.example.ivandimitrov.recepiestask.retrofit.ApiInterface;
import com.example.ivandimitrov.recepiestask.retrofit.brewery.ExampleBrewery;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ivan Dimitrov on 2/17/2017.
 */

public class BreweryFragment extends PageFragment {
    private RecyclerView       mRecyclerView;
    private BreweryListAdapter mAdapter;
    private List<BreweryNode> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_view, container, false);
        setRetainInstance(true);
        mAdapter = new BreweryListAdapter(getActivity(), mList);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        if (mList.isEmpty()) {
            mList.add(new BreweryNode());
            mList.add(new BreweryNode());
            mList.add(new BreweryNode());
            mList.add(new BreweryNode());
            mList.add(new BreweryNode());
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Function<Integer, Observable<Pair<Integer, ExampleBrewery>>> f = i -> apiService.getBreweryData()
                    .map(ex -> new Pair<>(i, ex))
                    .subscribeOn(Schedulers.io());

            Observable.range(0, 5).flatMap(f)
                    .doOnNext(s -> {
                        if (s.second.getData().getImages() == null) {
                            mList.get(s.first).setUrl(NO_IMAGE_AVAILABLE);
                        } else {
                            mList.get(s.first).setUrl(s.second.getData().getImages().getMedium());
                        }
                        mList.get(s.first).setName(s.second.getData().getName());
                        mList.get(s.first).setInformation(s.second.getData().getDescription());
                        mList.get(s.first).setOrganic(s.second.getData().getIsOrganic().equals("Y"));
                        mList.get(s.first).setBitmap(getBitmapFromURL(mList.get(s.first).getUrl()));
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> mAdapter.notifyDataSetChanged());
        }
        return v;
    }
}