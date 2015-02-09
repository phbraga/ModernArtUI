package com.coursera.modernartui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public final class MoreInformationDialog extends DialogFragment{

	private static MoreInformationDialog mMoreInformationDialog;
	
	private Button mNegativeButton;

    private Button mPositiveButton;
    
    private Dialog mMessageDialog;
    
	private MoreInformationDialog() {
		
	}
	
	public static MoreInformationDialog getInstance() {
		
		if(mMoreInformationDialog == null) {
			mMoreInformationDialog = new MoreInformationDialog();
		}
		
		return mMoreInformationDialog;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		/*
		TextView message = new TextView(getActivity());
		message.setTextSize(22);
		message.setText(R.string.dialog_message);
		message.setTextColor(getResources().getColor(R.color.white));
		message.setBackgroundColor(getResources().getColor(R.color.default_blue_background));
		message.setGravity(Gravity.CENTER);
		message.setPadding(10, 20, 10, 20);
		
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Translucent)
				.setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
//						Intent chooser = Intent.createChooser(intent, "Choose");
//						startActivity(chooser);
						startActivity(intent);
					}
				})
				.setPositiveButton(R.string.dialog_positive_button, null).create();

		alertDialog.setView(message);
		
		return alertDialog;
		*/
		
		mMessageDialog = new Dialog(getActivity(), android.R.style.Theme_Translucent);
		mMessageDialog.setContentView(R.layout.modern_art_dialog);
		mMessageDialog.setCancelable(true);
		mMessageDialog.setCanceledOnTouchOutside(true);
		
		mNegativeButton = (Button) mMessageDialog.findViewById(R.id.dialog_negative_button);
    	mPositiveButton = (Button) mMessageDialog.findViewById(R.id.dialog_positive_button);
    	
    	mNegativeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
				startActivity(intent);
				mMessageDialog.dismiss();
			}
		});
    	
    	mPositiveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMessageDialog.cancel();
			}
		});
    	
    	RelativeLayout relativeLayout = (RelativeLayout) mMessageDialog.findViewById(R.id.outside_dialog);
    	relativeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMessageDialog.cancel();
			}
		});
    	
    	return mMessageDialog;
	}
}
