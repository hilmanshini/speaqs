package speaqs.hilmanshini.tool;

import android.content.*;
import android.widget.*;

public class AsyncConcurrentFactory extends AsyncConcurrentHttpLoad {
    
    private AsyncConcurrentFactory(ConcurrentListener listener) {
        super(listener);
    }
    public static void cancel(){
        cancelled = true;
    }
    public Object doLongOperation() {
        return uilistener.doLongOperation();
    }

    public static void startConcurrentAsyncTask(ConcurrentListener listener) {
        cancelled = false;
        System.err.println(" concurr "+listener.getClass().getName());
        AsyncConcurrentFactory thread = new AsyncConcurrentFactory(listener);
        thread.execute("");
    }

    @Override
    public void prepare() {
        uilistener.prepare();
    }
}
