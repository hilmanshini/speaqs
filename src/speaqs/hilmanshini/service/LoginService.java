package speaqs.hilmanshini.service;

import hilmanshini.speaqs.LoginActivityListener;

public interface LoginService extends Service{

	void bindButtonLoginAndSignup(LoginActivityListener loginActivityListener);

	void setViewToLogin();

	void disableLoginButton();

	void startLogin(LoginActivityListener loginActivityListener);

	void validateResponse(String response);

	void toSpeaqsbBoxActivity();

	void alertLoginError();

	boolean isResponseSuccess();

	

}
