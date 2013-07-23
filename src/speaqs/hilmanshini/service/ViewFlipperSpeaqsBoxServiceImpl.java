package speaqs.hilmanshini.service;

import hilmanshini.speaqs.R;
import android.app.Activity;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ViewFlipperSpeaqsBoxServiceImpl extends StandardService implements
		ViewFlipperSpeaqsBoxService {
	private ViewFlipper viewFlipper;

	public ViewFlipperSpeaqsBoxServiceImpl(Activity activity) {
		super(activity);
	}

	@Override
	public void setViewToSpeaqsBoxFlipper() {
		activity.setContentView(R.layout.speaqsbox_flipper);
	}

	@Override
	public void loadViewFlipper() {
		//
		viewFlipper = (ViewFlipper) activity
				.findViewById(R.id.container_ffliper);
		on(1);
	}

	float lastX;

	@Override
	public void setXToLastX(MotionEvent touchevent) {
		lastX = touchevent.getX();

	}

	float currentX;

	@Override
	public void setCurrentX(MotionEvent touchevent) {
		currentX = touchevent.getX();

	}

	@Override
	public boolean isLastXLessThanCurrentX() {
		return (lastX < currentX);
	}

	@Override
	public boolean isLastXGreaterThanCurrentX() {

		return (lastX > currentX);
	}

	@Override
	public boolean isDisplayedChildNull() {
		return (viewFlipper.getDisplayedChild() == 0);
	}

	@Override
	public boolean isDisplayedChildNotNull() {
		return (viewFlipper.getDisplayedChild() == 1);
	}

	@Override
	public void toPreviousView() {
		viewFlipper.setInAnimation(activity, R.anim.in_from_right);
		viewFlipper.setOutAnimation(activity, R.anim.out_to_left);
		indexView++;
		viewFlipper.showPrevious();

	}

	private byte indexView = 1;

	@Override
	public void toNextView() {
		indexView--;
		viewFlipper.setInAnimation(activity, R.anim.in_from_left);
		viewFlipper.setOutAnimation(activity, R.anim.out_to_right);

		viewFlipper.showNext();

	}

	public byte getIndexView() {
		return indexView;
	}
	private void on(int index){
		int a[] = {R.id.vbhdpibutton, R.id.mentionhdpibutton,R.id.mshdpibutton,R.id.searchhdpibutton};
		int a2[] = {R.drawable.voicebox_hdpi,R.drawable.mention_hdpi,R.drawable.myspeaqs_hdpi,R.drawable.search_hdpi};
		for (int i = 0; i < a.length; i++) {
			int ax = a[i];
			ImageButton v = (ImageButton) activity.findViewById(ax);
			v.setImageResource(a2[i]);
			if(i+1 == index){
				switch (ax) {
				case R.id.vbhdpibutton:
					v.setImageResource(R.drawable.voicebox_hdpi_pressed);
					break;
				case R.id.mentionhdpibutton:
					v.setImageResource(R.drawable.mention_hdpi_pressed);
					break;
				case R.id.mshdpibutton:
					v.setImageResource(R.drawable.myspeaqs_hdpi_pressed);
					break;
				case R.id.searchhdpibutton:
					v.setImageResource(R.drawable.search_hdpi_pressed);
					break;
				default:
					break;
				}
				
			}
		}
		
	}
	@Override
	public void jumpToVoiceBox() {
		
		on(1);
		while (indexView > 1) {
			toNextView();
		}
	}

	@Override
	public void jumpToSearch(View v) {
		on(4);
		while (indexView < 4) {
			toPreviousView();
		}
	}

	@Override
	public void jumpToMySpeaqs() {
		on(3);
		while (indexView > 3) {
			toNextView();
		}
		while (indexView < 3) {
			toPreviousView();
		}
	}

	@Override
	public void jumpToMention() {
		on(2);
		while (indexView > 2) {
			toNextView();
		}
		while (indexView < 2) {
			toPreviousView();
		}

	}

	@Override
	public void handleTouch(MotionEvent touchevent) {
		switch (touchevent.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			setXToLastX(touchevent);
			break;
		}
		case MotionEvent.ACTION_UP: {
			setCurrentX(touchevent);

			if (isLastXLessThanCurrentX()) {
				if (isDisplayedChildNull())
					break;
				toNextView();
				on(getIndexView());
			}
			if (isLastXGreaterThanCurrentX()) {
				if (isDisplayedChildNotNull())
					break;
				toPreviousView();
				on(getIndexView());
			}
			break;
		}
		}
	}

}
