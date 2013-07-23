package hilmanshini.speaqs;

import speaqs.hilmanshini.service.SpeaqsBoxService;
import speaqs.hilmanshini.service.SpeaqsBoxServiceImpl;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class SpeaqsBoxActivity extends Activity  {

    SpeaqsBoxService speaqsBoxService = new SpeaqsBoxServiceImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
       setContentView(R.layout.welcome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

   

    public void onUpdateStatusDialogMentionButtonClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onUpdateStatusDialogSpeaqsButtonClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onMentionBackPressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
