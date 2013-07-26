package speaqs.hilmanshini.service;

import speaqs.hilmanshini.tool.AndroidTool;
import hilmanshini.speaqs.R;
import hilmanshini.speaqs.SpeaqsBoxActivityListener;
import android.R.attr;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import hilmanshini.speaqs.TestActivity;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpeaqsBoxServiceImpl extends StandardService implements
		SpeaqsBoxService {

	private static final int speaqs_interval_seconds = 5;
	protected static final String RESULT_LOAD_IMAGE = null;
	private TableLayout ContainerSpeaqsBox,MentionContainerSpeaqsBox;

	public SpeaqsBoxServiceImpl(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadContainerSpeaqsBox() {
		TableLayout container = (TableLayout) activity
				.findViewById(R.id.container);
		this.ContainerSpeaqsBox = container;
		MentionContainerSpeaqsBox = (TableLayout) activity
				.findViewById(R.id.mention_container);
	}

	public void initView() {
		// activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.setContentView(R.layout.speaqsbox);

	}

	@Override
	public void addSample(SpeaqsBoxActivityListener vt) {
		View v = createOneSpeaqsLayoutSound(vt);
		ContainerSpeaqsBox.addView(v);
		System.err.println("slog : adding 1 speaqslayout");
		v = createOneSpeaqsLayoutSound(vt);
		ContainerSpeaqsBox.addView(v);
		System.err.println("slog : adding 1 speaqslayout");
		v = createOneSpeaqsLayoutSound(vt);
		ContainerSpeaqsBox.addView(v);
		System.err.println("slog : adding 1 speaqslayout");
		v.findViewById(R.id.replyx);
	}

	protected View createOneSpeaqsLayoutSound(final SpeaqsBoxActivityListener vt) {
		View v = AndroidTool.loadLayout(activity, R.layout.singlespeaqs);
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		View grid1 = v.findViewById(R.id.singlegrid1);
		View grid2 = v.findViewById(R.id.singlegrid2);
		LayoutParams l = new LayoutParams(width, 120);
		grid1.setLayoutParams(l);
		grid2.setLayoutParams(l);
		OnClickListener c = new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				switch (v.getId()) {
				case R.id.replyx:
					vt.onReplyClick(v);
					break;
				case R.id.sharex:
					vt.onShareClicK(v);
					break;
				case R.id.crownx:
					vt.onFavClick(v);
					break;
				default:
					break;
				}

			}
		};
		ImageButton b1 = (ImageButton) v.findViewById(R.id.replyx);
		LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(width / 3,
				120);
		b1.setOnClickListener(c);
		b1.setLayoutParams(rl);
		
		ImageButton b2 = (ImageButton) v.findViewById(R.id.crownx);
		b2.setLayoutParams(rl);
		b2.setOnClickListener(c);
		ImageButton b3 = (ImageButton) v.findViewById(R.id.sharex);
		b3.setLayoutParams(rl);
		b3.setOnClickListener(c);
		return v;
	}

	protected View createOneSpeaqsLayoutNoSound(final SpeaqsBoxActivityListener vt) {
		View v = AndroidTool.loadLayout(activity, R.layout.singlespeaqsnosound);
		Display d = activity.getWindowManager().getDefaultDisplay();
		int width = d.getWidth();
		View grid1 = v.findViewById(R.id.singlegrid1);
		View grid2 = v.findViewById(R.id.singlegrid2);
		LayoutParams l = new LayoutParams(width, 120);
		grid1.setLayoutParams(l);
		grid2.setLayoutParams(l);
		OnClickListener c1 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				switch (v.getId()) {
				case R.id.replyx:
					vt.onReplyClick(v);
					break;
				case R.id.sharex:
					vt.onShareClicK(v);
					break;
				case R.id.crownx:
					vt.onFavClick(v);
					break;
				default:
					break;
				}

			}
		};
		
		ImageButton b1 = (ImageButton) v.findViewById(R.id.replyx);
		LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(width / 3,
				120);
		b1.setOnClickListener(c1);
		b1.setLayoutParams(rl);
		
		ImageButton b2 = (ImageButton) v.findViewById(R.id.crownx);
		b2.setLayoutParams(rl);
		b2.setOnClickListener(c1);
		ImageButton b3 = (ImageButton) v.findViewById(R.id.sharex);
		b3.setLayoutParams(rl);
		b3.setOnClickListener(c1);
		return v;
	}

	@Override
	public void bindUpdateStatusButton(
			final SpeaqsBoxActivityListener speaqsBoxActivityListener) {
		View v = activity.findViewById(R.id.update_status_speaqs_button);
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				speaqsBoxActivityListener.onUpdateStatusClick(v);
			}
		});
	}

	UpdateStatusDialog activeUpdateStatusDialog = null;

	@Override
	public void showDialogUpdateStatus(
			SpeaqsBoxActivityListener speaqsBoxActivity) {
		// TODO Auto-generated method stub
		activeUpdateStatusDialog = new UpdateStatusDialog(activity,
				speaqsBoxActivity);
		activeUpdateStatusDialog.showDialog();
		activeUpdateStatusDialog.findViewById(R.id.melodi_icon).setVisibility(
				100);
	}

	@Override
	public void submitDataUpdateStatusToServer(
			SpeaqsBoxActivityListener speaqsBoxActivity) {
	}

	@Override
	public void dismisUpdateStatusDialog(
			SpeaqsBoxActivityListener speaqsBoxActivity) {
		activeUpdateStatusDialog.dismiss();

	}

	@Override
	public void bindMenuButton(final SpeaqsBoxActivityListener testActivity) {

		OnClickListener e = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.vbhdpibutton) {
					testActivity.OnVoiceBoxButtonClick(v);
				} else if (v.getId() == R.id.mentionhdpibutton) {
					testActivity.onMentionButtonMenuClick(v);
				} else if (v.getId() == R.id.mshdpibutton) {
					testActivity.onMySpeaqsMenuClick(v);
				} else if (v.getId() == R.id.searchhdpibutton) {
					testActivity.onSearchMenuClick(v);
				}
			}
		};
		activity.findViewById(R.id.vbhdpibutton).setOnClickListener(e);
		activity.findViewById(R.id.mentionhdpibutton).setOnClickListener(e);
		activity.findViewById(R.id.mshdpibutton).setOnClickListener(e);
		activity.findViewById(R.id.searchhdpibutton).setOnClickListener(e);
	}

	Dialog activeMentionDialog;

	public void showMentionDialog(TestActivity aThis) {

		activeUpdateStatusDialog.dismiss();
		activeMentionDialog = new Dialog(activity,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		activeMentionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activeMentionDialog.setContentView(R.layout.mention_dialog);

		activeMentionDialog.setCancelable(true);
		activeMentionDialog
				.setOnKeyListener(new DialogInterface.OnKeyListener() {
					public boolean onKey(DialogInterface di, int i, KeyEvent ke) {
						if (i == KeyEvent.KEYCODE_BACK) {
							activeUpdateStatusDialog.show();
							activeMentionDialog.dismiss();
							return true;
						}
						return true;
					}
				});
		activeMentionDialog.show();
		TextView tv = (TextView) activeMentionDialog
				.findViewById(R.id.mention_edittext);
		tv.addTextChangedListener(new TextWatcher() {
			public void beforeTextChanged(CharSequence cs, int i, int i1, int i2) {
			}

			public void onTextChanged(CharSequence cs, int i, int i1, int i2) {
			}

			public void afterTextChanged(Editable edtbl) {
				TableLayout v = (TableLayout) activeMentionDialog
						.findViewById(R.id.mention_container);
				View e = AndroidTool.loadLayout(activity,
						R.layout.singlemention);

				v.addView(e);
			}
		});
	}

	Dialog activeSpeaqsAnythingNow;

	public void showSpeaqsDialog(TestActivity aThis) {
		activeUpdateStatusDialog.dismiss();

		activeSpeaqsAnythingNow = new Dialog(activity,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		activeSpeaqsAnythingNow.requestWindowFeature(Window.FEATURE_NO_TITLE);

		activeSpeaqsAnythingNow.setContentView(R.layout.speakanythingnow);
		activeSpeaqsAnythingNow.setCancelable(false);
		activeSpeaqsAnythingNow.show();
		activeUpdateStatusDialog.findViewById(R.id.melodi_icon)
				.setVisibility(0);
		// Declare the timer
		final Timer t = new Timer();
		// Set the schedule function and rate

		t.scheduleAtFixedRate(new TimerTask() {
			private int i = 0;

			@Override
			public void run() {
				if (i > 6000) {
					t.cancel();
					t.purge();

				} else {
					Message g = new Message();
					g.getData().putInt("sec", i);
					i += 1000;
					speaqsHandler.sendMessage(g);
				}

			}
		},
		// Set how long before to start calling the TimerTask (in milliseconds)
				0,
				// Set the amount of time between each execution (in
				// milliseconds)
				1000);

	}

	Handler speaqsHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg); // To change body of generated methods,
										// choose Tools | Templates.
			int i = msg.getData().getInt("sec");
			if (i <= 5000) {
				TextView tv = (TextView) activeSpeaqsAnythingNow
						.findViewById(R.id.speaqs_anything_now_content_text);
				StringBuilder sb = new StringBuilder();
				sb.append("SPEAQS FOR ");
				sb.append(i / 1000);
				sb.append(" seconds ");
				tv.setText(sb);
				activeSpeaqsAnythingNow.invalidateOptionsMenu();
			} else {
				activeSpeaqsAnythingNow.dismiss();
				activeUpdateStatusDialog.showDialog();
			}

		}
	};

	public void makeButtonMenuFit() {
		DisplayMetrics e = AndroidTool.getDisplayMax(activity);
		View v = activity.findViewById(R.id.vbhdpibutton);
		v.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4,
				dpToPx(120)));
		View v2 = activity.findViewById(R.id.mentionhdpibutton);
		v2.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4,
				dpToPx(120)));
		View v3 = activity.findViewById(R.id.mshdpibutton);
		v3.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4,
				dpToPx(120)));
		View v4 = activity.findViewById(R.id.searchhdpibutton);
		v4.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4,
				dpToPx(120)));

	}

	public int dpToPx(int dp) {
		DisplayMetrics displayMetrics = activity.getResources()
				.getDisplayMetrics();
		int px = Math.round(dp
				* (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}

	public void handleOnBack() {
		if (activeMentionDialog != null) {
			if (activeMentionDialog.isShowing()) {
				activeUpdateStatusDialog.show();
			}
		}

	}

	@Override
	public void pickGallery() {
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		startACtivityFOrResult(i, 1);
	}

	@Override
	public boolean isPickedGallertMyProfileNull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showMentionDialog(SpeaqsBoxActivityListener aThis) {
		Dialog g = new Dialog(activity);
		g.requestWindowFeature(Window.FEATURE_NO_TITLE);
		g.setContentView(R.layout.mention_dialog);
		g.setCancelable(true);
		g.show();
	}

	Dialog speaqsDialog;

	@Override
	public void showSpeaqsDialog(SpeaqsBoxActivityListener aThis) {
		speaqsDialog = new Dialog(activity,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		speaqsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		speaqsDialog.setContentView(R.layout.speakanythingnow);
		speaqsDialog.setCancelable(false);
		speaqsDialog.show();
		new dtr(aThis).start();
	}

	class dtr extends Thread {
		private h h;
		SpeaqsBoxActivityListener activityListener;

		public dtr(SpeaqsBoxActivityListener activityListener) {
			this.activityListener = activityListener;
			h = new h(activityListener);
		}

		@Override
		public void run() {
			for (int i = 0; i < 6; i++) {
				try {
					h.sendEmptyMessage(0);
					Thread.currentThread().sleep(1000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class h extends Handler {
		SpeaqsBoxActivityListener activityListener;

		public h(SpeaqsBoxActivityListener activityListener) {
			// TODO Auto-generated constructor stub
			this.activityListener = activityListener;
		}

		int i = 0;

		public void dispatchMessage(Message msg) {
			TextView tv = (TextView) speaqsDialog
					.findViewById(R.id.speaqs_anything_now_content_text);
			System.err.println("netlog " + i);

			tv.setText("Speak For " + (5 - ((i + 1) / 1000)) + " second(s) ");
			i += 1000;
			if (i == 6000) {

				View v = activeUpdateStatusDialog
						.findViewById(R.id.melodi_icon);
				v.setVisibility(0);
				speaqsDialog.dismiss();
				activityListener.onFinishRecord();
			}
		};
	}

	@Override
	public void bindPickImageMyProfile(
			final SpeaqsBoxActivityListener activityListener) {
		Button b = (Button) activity
				.findViewById(R.id.button_image_change_to_server);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				activityListener.onPickImageMyProfileClick();
			}
		});
	}

	private void startACtivityFOrResult(Intent i, int ix) {
		activity.startActivityForResult(i, ix);
	}

	Bitmap pickedBitmapInGallery;

	@Override
	public void handleOnActivityResult(int requestCode, int resultCode,
			Intent data, SpeaqsBoxActivityListener listenerw) {
		System.err.println("netlog reqCode" + requestCode + " resultCode "
				+ resultCode + " " + data);
		if (requestCode == 1 && resultCode == Activity.RESULT_OK
				&& null != data) {
			System.err.println("netlog passed");
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = activity.getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			pickedBitmapInGallery = BitmapFactory.decodeFile(picturePath);
			listenerw.onFinishPickImageMyProfile();
		}

	}

	@Override
	public boolean isImageBitmapFromGalleryExists() {
		if (pickedBitmapInGallery == null) {
			return false;
		} else {
			return true;
		}
	}

	String responseFromServer;

	@Override
	public void sendImageChangeToServer(
			SpeaqsBoxActivityListener activityListener) {
		responseFromServer = "success";
		activityListener.onGetResponseImageChangeFromServer();
	}

	@Override
	public boolean isResponseImageChangeServerSuccess() {
		if (responseFromServer.equals("success")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object getResponseImageChangeFromServer() {
		return responseFromServer;
	}

	@Override
	public void alertSpeaqsDialogResponse(Object responseImageChangeFromServer) {
		Toast.makeText(activity, responseFromServer.toString(), 4000).show();
	}

	@Override
	public void changeImageProfileWithPickedInGallery() {
		ImageView v = (ImageView) activity
				.findViewById(R.id.image_profile_pic_edit_profile);
		v.setImageBitmap(pickedBitmapInGallery);

	}

	@Override
	public void makeSearchContainerFit() {
		TableLayout v = (TableLayout) activity
				.findViewById(R.id.container_search);
		v.setLayoutParams(new TableLayout.LayoutParams(AndroidTool
				.getDisplayMax(activity).widthPixels, v.getHeight()));
		System.err.println("netlog displaying "
				+ AndroidTool.getDisplayMax(activity).widthPixels + " x "
				+ v.getHeight());
		TableRow t = (TableRow) AndroidTool.loadLayout(activity,
				R.layout.singlesearch);
		v.addView(t);
		t = (TableRow) AndroidTool.loadLayout(activity, R.layout.singlesearch);
		v.addView(t);
		t = (TableRow) AndroidTool.loadLayout(activity, R.layout.singlesearch);
		v.addView(t);
	}



	@Override
	public void submitFavorite(View v,SpeaqsBoxActivityListener a) {
		Toast.makeText(activity, "submitting", 4000).show();
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.onFavSubmitGetResponse(v);
	}

	@Override
	public void makeActiveUpdateStatusMelodiButtonAvailable() {
		View v = activeUpdateStatusDialog.findViewById(R.id.melodi_icon);
		v.setVisibility(0);
		
	}

	@Override
	public boolean isAlreadyFavorite() {
		return false;
	}

	@Override
	public void cancelFavorite(View v,
			SpeaqsBoxActivityListener SpeaqsBoxActivityListener) {
		Toast.makeText(activity, "submitting", 4000).show();
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SpeaqsBoxActivityListener.onFavSubmitGetResponse(v);
		
	}

	@Override
	public void makeUnFav(View v) {
		ImageButton vx = (ImageButton) v.findViewById(R.id.crownx);
		vx.setImageResource(R.drawable.crown_cancel);
	}

	@Override
	public void makeFav(View v) {
		ImageButton vx = (ImageButton) v.findViewById(R.id.crownx);
		vx.setImageResource(R.drawable.crown);
	}

	
}
