package com.example.ivandimitrov.recepiestask.brewery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivandimitrov.recepiestask.R;
import com.example.ivandimitrov.recepiestask.beer.BeerListAdapter;
import com.example.ivandimitrov.recepiestask.beer.BeerNode;

import java.io.IOException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ivan Dimitrov on 2/17/2017.
 */

public class BreweryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_brewery);
        ImageView itemImage = (ImageView) findViewById(R.id.selected_item_image);
        TextView itemInfo = (TextView) findViewById(R.id.selected_item_info);
        TextView itemName = (TextView) findViewById(R.id.selected_item_name);
        ImageView isOrganicImage = (ImageView) findViewById(R.id.selected_item_is_organic_image);

        BeerNode selectedItem = new BeerNode();
        selectedItem.setName(getIntent().getStringExtra(BeerListAdapter.ITEM_NAME));
        selectedItem.setUrl(getIntent().getStringExtra(BeerListAdapter.ITEM_PICTURE));
        selectedItem.setInformation(getIntent().getStringExtra(BeerListAdapter.ITEM_INFORMATION));
        selectedItem.setOrganic(getIntent().getBooleanExtra(BeerListAdapter.ITEM_IS_ORGANIC, false));

        if (selectedItem.isOrganic()) {
            isOrganicImage.setImageResource(R.drawable.organic);
        } else {
            isOrganicImage.setImageResource(R.drawable.non_organic);
        }
        itemName.setText(selectedItem.getName());
        itemInfo.setText(selectedItem.getInformation());
        Disposable d = Observable.just(selectedItem.getUrl())
                .subscribeOn(Schedulers.io())
                .doOnNext(s -> selectedItem.setBitmap(getBitmapFromURL(selectedItem.getUrl())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> itemImage.setImageBitmap(selectedItem.getBitmap()));
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