package hilmanshini.speaqs;

import speaqs.hilmanshini.service.RegisterService;
import speaqs.hilmanshini.service.RegisterServiceImpl;
import android.app.Activity;
import android.os.Bundle;

public class RegisterActivity extends Activity implements RegisterActivityListener{
	RegisterService registerService = new RegisterServiceImpl(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerService.makeFullScreen();
		registerService.setViewToRegister();
		registerService.bindRegisterButton((RegisterActivityListener)this);
		
	}
	public void onRegisterButtonClick(){
		registerService.startRegister((RegisterActivityListener)this);
		registerService.disableButtonRegister();
	}
	public void onGetRegisterResponseFromServer(String response){
		registerService.parseResponse(response);
		if(registerService.isResponseFromServerSuccess()){
			registerService.alertThatRegisterSuccess((RegisterActivityListener)this);
		}
	}
	@Override
	public void onBackPressed() {
		
	}
}
