package hilmanshini.speaqs;

import android.view.View;

public interface SpeaqsBoxActivityListener {
	void onUpdateStatusClick(View v);
	
	public void onSubmitUpdateStatusClick(View v);
	public void onMentionButtonClick(View v);
	public void onSpeaqsButtonClick(View v);
	public void onCancelButtonClick(View v);


	void OnVoiceBoxButtonClick(View v);

	void onMentionButtonMenuClick(View v);

	void onMySpeaqsMenuClick(View v);

	void onSearchMenuClick(View v);


        void onMentionBackPressed();

}
