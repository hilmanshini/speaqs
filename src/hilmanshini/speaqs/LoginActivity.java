package hilmanshini.speaqs;

import speaqs.hilmanshini.service.LoginService;
import speaqs.hilmanshini.service.LoginServiceImpl;
import android.app.Activity;
import android.os.Bundle;

public class LoginActivity extends Activity implements LoginActivityListener{
	LoginService loginService = new LoginServiceImpl(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loginService.makeFullScreen();
		loginService.setViewToLogin();
		loginService.bindButtonLoginAndSignup(this);
	}

	@Override
	public void onLoginButtonClick() {
		loginService.disableLoginButton();
		loginService.startLogin(this);
	}
	public void onGetResponseLoginFromServer(String response){
		loginService.validateResponse(response);
		if(loginService.isResponseSuccess()){
			loginService.toSpeaqsbBoxActivity();
		} else {
			loginService.alertLoginError();
		}
	}
	
}
