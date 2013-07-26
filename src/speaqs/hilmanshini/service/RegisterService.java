package speaqs.hilmanshini.service;

import hilmanshini.speaqs.RegisterActivityListener;

public interface RegisterService extends Service{

	void bindRegisterButton(RegisterActivityListener registerActivityListener);

	void setViewToRegister();

	void startRegister(RegisterActivityListener registerActivityListener);

	void disableButtonRegister();

	void parseResponse(String response);

	boolean isResponseFromServerSuccess();

	void alertThatRegisterSuccess(
			RegisterActivityListener registerActivityListener);

}
