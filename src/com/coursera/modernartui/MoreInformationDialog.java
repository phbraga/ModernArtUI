package com.coursera.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public final class MoreInformationDialog extends DialogFragment{

	private static MoreInformationDialog mMoreInformationDialog;
	
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
		
		TextView message = new TextView(getActivity());
		message.setTextSize(22);
		message.setText(R.string.dialog_message);
		message.setGravity(Gravity.CENTER);
		message.setPadding(10, 20, 10, 20);
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
				.setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
//						Intent chooser = Intent.createChooser(intent, "Choose");
//						startActivity(chooser);
						startActivity(intent);
					}
				})
				.setPositiveButton(R.string.dialog_positive_button, null);
		
		alertDialog.setView(message);
		
		return alertDialog.create();
	}
}
