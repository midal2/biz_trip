package biztrip.stock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import biztrip.stock.biz.Stock;
import biztrip.stock.biz.StockInfo;
import biztrip.stock.biz.StockPresenter;

public class MainActivity extends AppCompatActivity {
    private Stock.Present stockPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockPresent = new StockPresenter();

        ViewPager viewPager = findViewById(R.id.container);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.d(FragmentPagerAdapter.class.getSimpleName(), "getItem:" + position);
                return StockFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "관심사항";
                    case 1:
                        return "전체목록";
                    case 2:
                        return "환경설정";
                }
                return null;
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d(ViewPager.OnPageChangeListener.class.getSimpleName(), "onPageScrolled:" + position + "/" + positionOffset + "/" + positionOffsetPixels );
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(ViewPager.OnPageChangeListener.class.getSimpleName(), "onPageSelected:" + position);

//                StockList_AsyncTask stockList_asyncTask = new StockList_AsyncTask();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.d(ViewPager.OnPageChangeListener.class.getSimpleName(), "onPageScrollStateChanged:" + state);
            }
        });

        /*if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.stockContainer, StockFragment.newInstance(stockPresent))
                    .commit();
        }*/
    }
}

