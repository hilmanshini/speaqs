
package speaqs.hilmanshini.tool;

import android.app.*;
import android.content.*;
import android.media.*;
import android.util.*;
import android.view.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import speaqs.hilmanshini.R;

public class RecordTool {

    static MediaRecorder recorder;

    public static interface RecordListener {

        public void onFinish(byte[] x);
    }

    public static void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        File f = new File("/mnt/sdcard/speaqs/test.wav");
        if (!f.exists()) {
            File f2 = new File("/mnt/sdcard/speaqs/");
            try {
                f2.mkdir();
            } catch (Exception e) {
            }
            try {
                f.createNewFile();
            } catch (IOException e) {
            }
        }
        recorder.setOutputFile("/mnt/sdcard/speaqs/test.wav");
        //recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
    }
    public static Dialog activeAlertDialog;

    public static void showDialogYesOnly(Context c, String content, RecordListener listener) {
        Dialog d = new Dialog(c);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //d.setContentView(R.layout.speakit);
        d.setCancelable(false);
        activeAlertDialog = d;
        
        
        activeAlertDialog.show();
        new tr(listener).start();
    }

    static class tr extends Thread {

        RecordListener listener;

        public tr(RecordListener listener) {
            this.listener = listener;

        }

        public void run() {
            
                RecordTool.startRecording();
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RecordTool.class.getName()).log(Level.SEVERE, null, ex);
            }
                recorder.stop();
                File f = new File("/mnt/sdcard/speaqs/test.wav");
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    FileInputStream fis = new FileInputStream(f);
                    byte q[] = new byte[fis.available() + 1];
                    int i = 0;
                    int index = 0;
                    while (i != -1) {
                        i = fis.read();
                        byte b = (byte) i;
                        q[index] = b;
                        index++;
                    }

                    listener.onFinish(q);
                    //f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
            activeAlertDialog.dismiss();
        }
    }
    static MediaPlayer outPlayer;
    static Boolean outPlayerUsed = false;

    static class MediaPlayerListener implements Runnable {

        String data;
        Context c ;
        public MediaPlayerListener(String data) {
            this.c =  c;
        }

        public void run() {
            String byteData = data;
            
            System.err.println(byteData);
            byte[] sounds = Base64.decode(byteData, Base64.DEFAULT);
            if (outPlayer != null) {
                outPlayer = new MediaPlayer();
            }
            File f = new File("/mnt/sdcard/speaqs/testout.wav");
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(sounds);
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            new t().start();
        }
    }

    public static void startAudio(String base64) {
        
        
        Thread x = new Thread(new MediaPlayerListener(base64));
        x.start();
    }

    static class t extends Thread {

        public void run() {

            try {
                synchronized (outPlayerUsed) {
                    outPlayerUsed = true;
                    if (outPlayer == null) {
                        outPlayer = new MediaPlayer();
                    }
                    outPlayer.setDataSource("/mnt/sdcard/speaqs/testout.wav");

                    outPlayer.prepare();
                    outPlayer.start();

                    sleep(5000);
                    outPlayerUsed = false;
                }
                System.out.println("Yea");
            } catch (Exception e) {
                System.out.println("failed " + e.getStackTrace().toString());

                e.printStackTrace();
                System.out.println("failed " + e.getStackTrace().toString());
            }

        }
    }
}
