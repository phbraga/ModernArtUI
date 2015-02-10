package com.coursera.modernartui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art_main);
        
        mContext = this;
        mActionBar = getActionBar();
        configureActionBar();
        
        mRectanglesLayout = (LinearLayout) findViewById(R.id.rectangles_layout);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        
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
				
				for(int i = 0 ; i < mRectanglesLayoutChildCount ; ++i) {
					Log.d(TAG, "OnProgressChange. Collum " + i);
					
					View child = mRectanglesLayout.getChildAt(i);
					if (child instanceof LinearLayout) {
						
						LinearLayout collum = (LinearLayout) child;
						int collumChildCount = collum.getChildCount();
						Log.d(TAG, "OnProgressChange. Collum " + i + " Child Count: " + collumChildCount);
						
						for(int j = 0 ; j < collumChildCount ; ++j) {
							Log.d(TAG, "OnProgressChange. Collum " + i + " Child: " + j);
							
							View collumChild = collum.getChildAt(j);
							if (collumChild instanceof LinearLayout) {
								LinearLayout rectangle = (LinearLayout) collumChild;
								adjustColorByProgress(rectangle, progress);
							}
						}
					}
				}
			}
		});
        
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

    private void adjustColorByProgress(LinearLayout rectangle, int progress) {
		
	}

}
