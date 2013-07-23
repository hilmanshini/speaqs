package speaqs.hilmanshini.tool;

import android.content.*;
import android.os.*;
import android.widget.*;

public abstract class AsyncConcurrentHttpLoad extends AsyncTask {
    public static boolean cancelled = false;
    Object lockedObject;
    ConcurrentListener uilistener;

    public AsyncConcurrentHttpLoad(ConcurrentListener uilistener) {
        this.lockedObject = used;
        this.uilistener = uilistener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute(); //To change body of generated methods, choose Tools | Templates.
        prepare();
    }

    protected Object doInBackground(Object[] p1) {
        Object result = null;
        synchronized (HttpTool.httpClient) {
            used = true;
            
            if(!cancelled){
            result = doLongOperation();    
            }
            
            used = false;

        }


        // TODO: Implement this method

        return result;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if(!cancelled){
            uilistener.applyUi(result);
        }

    }
    
    public void tunngu(int time) {
        try {
            synchronized (this) {
                wait(time);
            }

        } catch (InterruptedException e) {
        }
    }
    public static volatile Boolean used = false;

    public abstract Object doLongOperation();
    public abstract void prepare();

    public static interface ConcurrentListener {

        public void applyUi(Object result);
        public void prepare();
        public Object doLongOperation();
    }
}
