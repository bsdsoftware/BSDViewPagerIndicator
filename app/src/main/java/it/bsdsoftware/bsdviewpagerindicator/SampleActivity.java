package it.bsdsoftware.bsdviewpagerindicator;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import it.bsdsoftware.library.IndicatorContainer;

public class SampleActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int[] pages = new int[]{1,2,3,4,5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        IndicatorContainer indicator = (IndicatorContainer) findViewById(R.id.indicator);

        List<String> label = new ArrayList<>();
        for(int i : pages){
            label.add(""+i);
        }
        indicator.setTextColorDeselected(Color.WHITE);
        indicator.setTextColorSelected(Color.BLUE);
        indicator.setBackgroundColorSelected(Color.GREEN);
        indicator.setBackgroundColorDeselected(Color.BLACK);
        indicator.setPadding(8);
        indicator.setMargin(8);
        indicator.setIndicatorClickable(true);
        indicator.setLabelText(label);
        indicator.setViewPager(mPager);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            SampleFragment sampleFragment = new SampleFragment();
            Bundle args = new Bundle();
            args.putInt("page_key", pages[position]);
            sampleFragment.setArguments(args);
            return sampleFragment;
        }

        @Override
        public int getCount() {
            return pages.length;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
