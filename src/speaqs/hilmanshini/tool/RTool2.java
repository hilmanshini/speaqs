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
import static speaqs.hilmanshini.tool.HttpTool.postDataGetStream;

public class RTool2 {

    public static View getLayout(int id, Context c, ViewGroup root) {
        LayoutInflater li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        View av = li.inflate(id, root);
        return av;
    }
    static MediaRecorder recorder;

    public static interface RecordListener {

        public void onFinish(byte[] x);
    }

    public static void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
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
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
    }
    public static AlertDialog activeAlertDialog;

    public static void showDialogYesOnly(Context c, String content, RecordListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(content)
                .setCancelable(false);
        activeAlertDialog = builder.create();

        activeAlertDialog.show();
        new tr(listener).start();
    }

    static class tr extends Thread {

        RecordListener listener;

        public tr(RecordListener listener) {
            this.listener = listener;

        }

        public void run() {
            try {
                startRecording();
                sleep(5000);
                recorder.stop();
                File f = new File("/mnt/sdcard/speaqs/test.wav");
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
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
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activeAlertDialog.dismiss();
        }
    }
    public static MediaPlayer outPlayer;
    static Boolean outPlayerUsed = false;

    static class MediaPlayerListener implements Runnable {

        InputStream idStatus;

        public MediaPlayerListener( MediaPlayer.OnCompletionListener oc) {
            this.idStatus = idStatus;
        }

        public void run() {

            

            
            new t().start();
        }
    }

    public static void startAudio( MediaPlayer.OnCompletionListener listener) {
        outPlayer = new MediaPlayer();
        Thread x = new Thread(new MediaPlayerListener(listener));
            x.start();
        
    }

    public static void pausePlayer() {
        outPlayer.pause();
    }

    static class t extends Thread {

        public void run() {

            try {
                synchronized (outPlayerUsed) {
                    outPlayerUsed = true;
                        outPlayer = new MediaPlayer();

                    outPlayer.setDataSource("/mnt/sdcard/downloadedfile.wav");

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
