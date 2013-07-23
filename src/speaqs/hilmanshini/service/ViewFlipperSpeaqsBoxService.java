package speaqs.hilmanshini.service;

import android.view.MotionEvent;
import android.view.View;
import hilmanshini.speaqs.TestActivity;

public interface ViewFlipperSpeaqsBoxService extends Service{

	void setViewToSpeaqsBoxFlipper();

	void loadViewFlipper();

	void setXToLastX(MotionEvent touchevent);

	void setCurrentX(MotionEvent touchevent);

	boolean isLastXLessThanCurrentX();

	boolean isLastXGreaterThanCurrentX();

	boolean isDisplayedChildNull();

	boolean isDisplayedChildNotNull();

	void toPreviousView();

	void toNextView();
	public byte getIndexView();

	void jumpToVoiceBox();

	void jumpToSearch(View v);

	void jumpToMySpeaqs();

	void jumpToMention();

	void handleTouch(MotionEvent touchevent);

    
}
