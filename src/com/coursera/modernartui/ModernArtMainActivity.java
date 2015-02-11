package com.coursera.modernartui;

import java.util.ArrayList;

import com.coursera.modernartui.R.color;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ModernArtMainActivity extends Activity {

	private static final String TAG = "ModernArtMainActivity";
	private Context mContext;
	private ActionBar mActionBar;
	private LinearLayout mRectanglesLayout;
	private SeekBar mSeekBar;
	private ArrayList<Integer> startColors;
	private ArrayList<Integer> finalColors;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art_main);
        
        mContext = this;
        mActionBar = getActionBar();
        configureActionBar();
        
        mRectanglesLayout = (LinearLayout) findViewById(R.id.rectangles_layout);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        
        initColors();
        
        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				int mRectanglesLayoutChildCount = mRectanglesLayout.getChildCount();
				Log.d(TAG, "OnProgressChange. mRectanglesLayout Child Count: " + mRectanglesLayoutChildCount);
				
				for(int i = 0, index = 0 ; i < mRectanglesLayoutChildCount ; ++i) {
					Log.d(TAG, "OnProgressChange. Collum " + i);
					
					View child = mRectanglesLayout.getChildAt(i);
					if (child instanceof LinearLayout) {
						
						LinearLayout collum = (LinearLayout) child;
						int collumChildCount = collum.getChildCount();
						Log.d(TAG, "OnProgressChange. Collum " + i + " Child Count: " + collumChildCount);
						
						for(int j = 0 ; j < collumChildCount ; ++j, ++index) {
							Log.d(TAG, "OnProgressChange. Collum " + i + " Child: " + j);
							
							View collumChild = collum.getChildAt(j);
							if (collumChild instanceof LinearLayout) {
								LinearLayout rectangle = (LinearLayout) collumChild;
								rectangle.setBackgroundColor(adjustColorByProgress(startColors.get(index), finalColors.get(index) ,progress));
							}
						}
					}
				}
			}
		});
    }

	private void initColors() {
		startColors = new ArrayList<Integer>();
		finalColors = new ArrayList<Integer>();
		
		Resources resources = getResources();
		
		startColors.add(resources.getColor(R.color.red));
		startColors.add(resources.getColor(R.color.white));
		startColors.add(resources.getColor(R.color.gray));
		startColors.add(resources.getColor(R.color.lighblue));
		startColors.add(resources.getColor(R.color.lightorange));
		
		finalColors.add(resources.getColor(R.color.final_red));
		finalColors.add(resources.getColor(R.color.final_white));
		finalColors.add(resources.getColor(R.color.final_gray));
		finalColors.add(resources.getColor(R.color.final_lighblue));
		finalColors.add(resources.getColor(R.color.final_lightorange));
	}

	private void configureActionBar() {
        mActionBar.setDisplayShowHomeEnabled(false);
		ColorDrawable colorDrawable = new ColorDrawable(mContext.getResources()
				.getColor(R.color.default_blue_background));
		mActionBar.setBackgroundDrawable(colorDrawable);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.modern_art_main, menu);
        return true;
    }

	public void showMoreInformationDialog(final MenuItem menuItem) {
		(new MoreInformationDialog()).show(getFragmentManager(), TAG);
	}

    private int adjustColorByProgress(int startColor, int finalColor, int progress) {
		
    	int r = Color.red(finalColor) - Color.red(startColor);
    	int g = Color.green(finalColor) - Color.green(startColor);
    	int b = Color.blue(finalColor) - Color.blue(startColor);
    	
    	int final_r = Color.red(startColor) + r * progress / 100;
    	int final_g = Color.green(startColor) + g * progress / 100;
    	int final_b = Color.blue(startColor) + b * progress / 100;
    	
    	return Color.rgb(final_r, final_g, final_b);
	}

}
