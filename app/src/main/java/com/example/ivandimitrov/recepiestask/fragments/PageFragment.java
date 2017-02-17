package com.example.ivandimitrov.recepiestask.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ivandimitrov.recepiestask.beer.BeerFragment;
import com.example.ivandimitrov.recepiestask.brewery.BreweryFragment;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Ivan Dimitrov on 2/16/2017.
 */

public class PageFragment extends Fragment {
    public static final int BEER_FRAGMENT    = 1;
    public static final int BREWERY_FRAGMENT = 2;

    public static final String BUNDLE_KEY_MESSAGE_TYPE = "pageType";
    public static final String NO_IMAGE_AVAILABLE      = "http://www.visit.bg//templates/ForeignResortsProject/resources/images/noimage.jpg";

    public static final Fragment newInstance(int message) {
        Fragment fragment;
        if (message == BEER_FRAGMENT) {
            fragment = new BeerFragment();
        } else if (message == BREWERY_FRAGMENT) {
            fragment = new BreweryFragment();
        } else {
            return null;
        }
        Bundle bdl = new Bundle();
        bdl.putInt(BUNDLE_KEY_MESSAGE_TYPE, message);
        fragment.setArguments(bdl);
        fragment.setRetainInstance(true);
        return fragment;
    }

    public static Bitmap getBitmapFromURL(String src) {
        Bitmap map = null;
        try {
            URL url = new URL(src);
            map = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}