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

public final class MoreInformationDialog extends DialogFragment implements OnClickListener{

	private Dialog mMessageDialog;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		mMessageDialog = new Dialog(getActivity(), android.R.style.Theme_Translucent);
		mMessageDialog.setContentView(R.layout.modern_art_dialog);
		mMessageDialog.setCancelable(true);
		mMessageDialog.setCanceledOnTouchOutside(true);
		
		Button negativeButton = (Button) mMessageDialog.findViewById(R.id.dialog_negative_button);
		negativeButton.setOnClickListener(this);
    	
		Button positiveButton = (Button) mMessageDialog.findViewById(R.id.dialog_positive_button);
		positiveButton.setOnClickListener(this);
		
		RelativeLayout relativeLayout = (RelativeLayout) mMessageDialog.findViewById(R.id.outside_dialog);
		relativeLayout.setOnClickListener(this);
    	
    	return mMessageDialog;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.dialog_negative_button) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
			startActivity(intent);
		} 
		mMessageDialog.dismiss();
		
	}
}
