package com.example.ivandimitrov.recepiestask.beer;

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
import com.example.ivandimitrov.recepiestask.retrofit.beer.Example;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ivan Dimitrov on 2/17/2017.
 */

public class BeerFragment extends PageFragment {
    private RecyclerView    mRecyclerView;
    private BeerListAdapter mAdapter;
    private List<BeerNode> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_view, container, false);
        if (mList.isEmpty()) {
            mList.add(new BeerNode());
            mList.add(new BeerNode());
            mList.add(new BeerNode());
            mList.add(new BeerNode());
            mList.add(new BeerNode());
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Function<Integer, Observable<Pair<Integer, Example>>> f = i -> apiService.getBeerData()
                    .map(ex -> new Pair<>(i, ex))
                    .subscribeOn(Schedulers.io());

            Observable.range(0, 5).flatMap(f)
                    .doOnNext(s -> {
                        if (s.second.getData().getLabels() == null) {
                            mList.get(s.first).setUrl(NO_IMAGE_AVAILABLE);
                        } else {
                            mList.get(s.first).setUrl(s.second.getData().getLabels().getMedium());
                        }

                        if (s.second.getData().getAvailable() == null) {
                            mList.get(s.first).setAvailability("Unlimited");
                        } else {
                            mList.get(s.first).setAvailability(s.second.getData().getAvailable().getDescription());
                        }

                        if (s.second.getData().getStyle() == null) {
                            mList.get(s.first).setCategory("Unknown");
                        } else {
                            mList.get(s.first).setCategory(s.second.getData().getStyle().getCategory().getName());
                        }

                        mList.get(s.first).setName(s.second.getData().getName());
                        mList.get(s.first).setInformation(s.second.getData().getDescription());
                        mList.get(s.first).setFoodPair(s.second.getData().getFoodPairings());
                        mList.get(s.first).setOrganic(s.second.getData().getIsOrganic().equals("Y"));
                        mList.get(s.first).setBitmap(getBitmapFromURL(mList.get(s.first).getUrl()));
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> mAdapter.notifyDataSetChanged());
        }

        mAdapter = new BeerListAdapter(getActivity(), mList);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }
}
