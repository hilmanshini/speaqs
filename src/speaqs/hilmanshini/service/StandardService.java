package speaqs.hilmanshini.service;

import speaqs.hilmanshini.tool.AndroidTool;
import android.app.Activity;

public class StandardService implements Service{
	Activity activity;
	public StandardService(Activity activity) {
		this.activity = activity;
		// TODO Auto-generated constructor stub
	}
	public void makeFullScreen(){
		AndroidTool.makeActivityFullScreen(activity);
	}
}
