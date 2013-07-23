package speaqs.hilmanshini.service;

import speaqs.hilmanshini.tool.AndroidTool;
import hilmanshini.speaqs.R;
import hilmanshini.speaqs.SpeaqsBoxActivityListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private TableLayout ContainerSpeaqsBox;

    public SpeaqsBoxServiceImpl(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void loadContainerSpeaqsBox() {
        TableLayout container = (TableLayout) activity.findViewById(R.id.container);
        this.ContainerSpeaqsBox = container;

    }

    public void initView() {
        //activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.setContentView(R.layout.speaqsbox);

    }

    @Override
    public void addSample() {
        View v = createOneSpeaqsLayoutSound();
        ContainerSpeaqsBox.addView(v);
        System.err.println("slog : adding 1 speaqslayout");
        v = createOneSpeaqsLayoutSound();
        ContainerSpeaqsBox.addView(v);
        System.err.println("slog : adding 1 speaqslayout");
        v = createOneSpeaqsLayoutSound();
        ContainerSpeaqsBox.addView(v);
        System.err.println("slog : adding 1 speaqslayout");

    }

    private View createOneSpeaqsLayoutSound() {
        View v = AndroidTool.loadLayout(activity, R.layout.singlespeaqs);
        Display d = activity.getWindowManager().getDefaultDisplay();
        int width = d.getWidth();
        View grid1 = v.findViewById(R.id.singlegrid1);
        View grid2 = v.findViewById(R.id.singlegrid2);
        LayoutParams l = new LayoutParams(width, 120);
        grid1.setLayoutParams(l);
        grid2.setLayoutParams(l);
        ImageButton b1 = (ImageButton) v.findViewById(R.id.button1);
        LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(width / 3, 120);
        b1.setLayoutParams(rl);
        ImageButton b2 = (ImageButton) v.findViewById(R.id.button2);
        b2.setLayoutParams(rl);
        ImageButton b3 = (ImageButton) v.findViewById(R.id.button3);
        b3.setLayoutParams(rl);
        return v;
    }

    @Override
    public void bindUpdateStatusButton(final SpeaqsBoxActivityListener speaqsBoxActivityListener) {
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
        activeUpdateStatusDialog = new UpdateStatusDialog(activity, speaqsBoxActivity);
        activeUpdateStatusDialog.showDialog();
        activeUpdateStatusDialog.findViewById(R.id.melodi_icon).setVisibility(100);
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
        activeMentionDialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        activeMentionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activeMentionDialog.setContentView(R.layout.mention_dialog);

        activeMentionDialog.setCancelable(true);
        activeMentionDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
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
                TableLayout v = (TableLayout) activeMentionDialog.findViewById(R.id.mention_container);
                View e = AndroidTool.loadLayout(activity, R.layout.singlemention);

                v.addView(e);
            }
        });
    }
    Dialog activeSpeaqsAnythingNow;

    public void showSpeaqsDialog(TestActivity aThis) {
        activeUpdateStatusDialog.dismiss();

        activeSpeaqsAnythingNow = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        activeSpeaqsAnythingNow.requestWindowFeature(Window.FEATURE_NO_TITLE);

        activeSpeaqsAnythingNow.setContentView(R.layout.speakanythingnow);
        activeSpeaqsAnythingNow.setCancelable(false);
        activeSpeaqsAnythingNow.show();
        activeUpdateStatusDialog.findViewById(R.id.melodi_icon).setVisibility(0);
        //Declare the timer
        final Timer t = new Timer();
//Set the schedule function and rate

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
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                1000);



    }
    Handler speaqsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg); //To change body of generated methods, choose Tools | Templates.
            int i = msg.getData().getInt("sec");
            if (i <= 5000) {
                TextView tv = (TextView) activeSpeaqsAnythingNow.findViewById(R.id.speaqs_anything_now_content_text);
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
        v.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4, dpToPx(80)));
        View v2 = activity.findViewById(R.id.mentionhdpibutton);
        v2.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4, dpToPx(80)));
        View v3 = activity.findViewById(R.id.mshdpibutton);
        v3.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4, dpToPx(80)));
        View v4 = activity.findViewById(R.id.searchhdpibutton);
        v4.setLayoutParams(new LinearLayout.LayoutParams(e.widthPixels / 4, dpToPx(80)));

    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public void handleOnBack() {
        System.err.println("netlog " + activeMentionDialog.isShowing());
        if (activeMentionDialog.isShowing()) {
            activeUpdateStatusDialog.show();
        }
    }
}
