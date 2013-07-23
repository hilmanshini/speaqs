/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speaqs.hilmanshini.tool;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 *
 * @author webdevelop
 */
public class AndroidTool {

    public static void makeActivityFullScreen(Activity act) {
        act.requestWindowFeature(Window.FEATURE_NO_TITLE);
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public static DisplayMetrics getDisplayMax(Activity act){
        DisplayMetrics d = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(d);
        return d;
    }
    public static View loadLayout(Context c,int id){
        LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return li.inflate(id, null);
    }
}
