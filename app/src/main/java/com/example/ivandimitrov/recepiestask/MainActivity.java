package com.example.ivandimitrov.recepiestask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.ivandimitrov.recepiestask.beer.BeerFragment;
import com.example.ivandimitrov.recepiestask.brewery.BreweryFragment;
import com.example.ivandimitrov.recepiestask.fragments.PageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int BEER_PAGE    = 0;
    public static final int BREWERY_PAGE = 1;

    private int mCurrentPageBackground = BEER_PAGE;

    private ViewPager viewPager;

    private Animation mFadeOutAnimation;
    private Animation mFadeInAnimation;
    private boolean isToolbarCollapsed = false;

    //=====LISTENERS=====
    private AppBarLayout.OnOffsetChangedListener mCollapseListener;
    private Palette.PaletteAsyncListener         mPaletteListener;
    private TabLayout.OnTabSelectedListener      mTabSelectedListener;
    private Animation.AnimationListener          mAnimationListener;
//    private static final String API_KEY = "ae6f8ff58acfc1a25628b22bf59f0153";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //===============================
//        //====== RETROFIT + RXJAVA ======
//        //===============================
//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//        apiService.getData()
//                .subscribeOn(Schedulers.io())
//                .doOnNext(s -> Log.d("SHOW", s.getData().getName() + " " + s.getData().getLabels().getMedium()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
//        //===============================

        mCurrentPageBackground = R.drawable.beer_background;
        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);

        final AppBarLayout collapsedBar = (AppBarLayout) findViewById(R.id.htab_appbar);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        final List<Fragment> fragments = getFragments();

        final PageAdapter beerPageAdapter = new PageAdapter(getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setBackgroundResource(R.drawable.beer_background);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beer_background);
        mPaletteListener = palette -> {
            int vibrantColor = palette.getVibrantColor(Color.RED);
            int vibrantDarkColor = palette.getDarkVibrantColor(Color.RED);
            collapsingToolbarLayout.setContentScrimColor(vibrantColor);
            collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
        };
        Palette.from(bitmap).generate(mPaletteListener);

        mCollapseListener = (appBarLayout, verticalOffset) -> {
            if (verticalOffset == 0) {
                isToolbarCollapsed = false;
            } else {
                isToolbarCollapsed = true;
            }
        };
        collapsedBar.addOnOffsetChangedListener(mCollapseListener);

        mTabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case BEER_PAGE:
                        mCurrentPageBackground = R.drawable.beer_background;
                        break;
                    case BREWERY_PAGE:
                        mCurrentPageBackground = R.drawable.brewery_background;
                        break;
                }
                mAnimationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        collapsingToolbarLayout.startAnimation(mFadeInAnimation);
                        collapsingToolbarLayout.setBackgroundResource(mCurrentPageBackground);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                mFadeOutAnimation.setAnimationListener(mAnimationListener);

                if (!isToolbarCollapsed) {
                    collapsingToolbarLayout.startAnimation(mFadeOutAnimation);
                } else {
                    collapsingToolbarLayout.setBackgroundResource(mCurrentPageBackground);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
        tabLayout.setOnTabSelectedListener(mTabSelectedListener);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(beerPageAdapter);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(BeerFragment.newInstance(PageFragment.BEER_FRAGMENT));
        fList.add(BreweryFragment.newInstance(PageFragment.BREWERY_FRAGMENT));
        return fList;
    }
}
