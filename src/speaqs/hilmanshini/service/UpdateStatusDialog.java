package speaqs.hilmanshini.service;

import hilmanshini.speaqs.R;
import hilmanshini.speaqs.SpeaqsBoxActivityListener;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

public class UpdateStatusDialog extends Dialog {
	SpeaqsBoxActivityListener listener;

	public UpdateStatusDialog(Context c, SpeaqsBoxActivityListener listener) {
		super(c);
		this.listener = listener;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.updatestatus_dialog);
		bindUpdateStatusDialog();
		
		setCancelable(false);
	}
	public void showDialog() {
		show();
	}

	private void bindUpdateStatusDialog() {
		View v = findViewById(R.id.update_status_ok_button);
		v.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onSubmitUpdateStatusClick(v);
			}
		});
		v = findViewById(R.id.updaate_status_cancel_button);
		v.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onCancelButtonClick(v);
			}
		});
		v = findViewById(R.id.update_status_mention_button);
		v.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onMentionButtonClick(v);
			}
		});
		v = findViewById(R.id.update_status_speaqs_button);
		v.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onSpeaqsButtonClick(v);
			}
		});
	}

}
