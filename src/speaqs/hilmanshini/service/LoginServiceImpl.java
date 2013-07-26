package speaqs.hilmanshini.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import speaqs.hilmanshini.tool.AsyncConcurrentFactory;
import speaqs.hilmanshini.tool.AsyncConcurrentHttpLoad;
import speaqs.hilmanshini.tool.HttpTool;
import speaqs.hilmanshini.tool.AsyncConcurrentHttpLoad.ConcurrentListener;
import hilmanshini.speaqs.LoginActivityListener;
import hilmanshini.speaqs.R;
import hilmanshini.speaqs.TestActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginServiceImpl extends StandardService implements LoginService {

	public LoginServiceImpl(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindButtonLoginAndSignup(
			final LoginActivityListener loginActivityListener) {
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.login_button_login) {
					loginActivityListener.onLoginButtonClick();
				}
			}
		};
	}

	@Override
	public void setViewToLogin() {
		activity.setContentView(R.layout.login);
	}

	@Override
	public void disableLoginButton() {
		activity.findViewById(R.id.login_button_login).setVisibility(100);
	}

	@Override
	public void startLogin(LoginActivityListener loginActivityListener) {
		EditText v = (EditText) activity
				.findViewById(R.id.login_username_edittext);
		String usernameText = v.getText().toString();
		EditText v2 = (EditText) activity
				.findViewById(R.id.login_password_edittext);
		String passwordText = v2.getText().toString();

		AsyncConcurrentFactory.startConcurrentAsyncTask(new LoginAsync(
				usernameText, passwordText, loginActivityListener));
	}

	private class LoginAsync implements ConcurrentListener {
		String username, password;
		LoginActivityListener listener;

		public LoginAsync(String username, String password,
				LoginActivityListener listener) {
			this.username = username;
			this.password = password;
			this.listener = listener;
		}

		@Override
		public void applyUi(Object result) {
			if (result != null) {
				listener.onGetResponseLoginFromServer(result.toString());
			} else {
				listener.onGetResponseLoginFromServer(null);
			}

		}

		@Override
		public void prepare() {
		}

		@Override
		public Object doLongOperation() {
			String loginUri = activity.getString(R.string.app_config_login_url);
			List<NameValuePair> ee = new ArrayList();
			NameValuePair usernameV = new BasicNameValuePair("username",
					username);
			NameValuePair passwordV = new BasicNameValuePair("password",
					password);
			String data = HttpTool.postData(loginUri, ee, false);
			return data;
		}

	}

	private enum RESPONSE_SERVER_LOGIN {
		SUCCESS, USERNAME_NOT_EXISTS, PASSWORD_WRONG
	}

	RESPONSE_SERVER_LOGIN s;

	@Override
	public void validateResponse(String response) {
		if (response != null) {
			if (response.equals("SUCCESS")) {
				s = RESPONSE_SERVER_LOGIN.SUCCESS;
			} else if (response.equals("USERNAME_NOT_EXISTS")) {
				s = RESPONSE_SERVER_LOGIN.USERNAME_NOT_EXISTS;
			} else if (response.equals("PASSWORD_WRONG")) {
				s = RESPONSE_SERVER_LOGIN.PASSWORD_WRONG;
			}
		}
	}

	@Override
	public void toSpeaqsbBoxActivity() {
		activity.finish();
		Intent i = new Intent(activity, TestActivity.class);
		activity.startActivity(i);
	}

	@Override
	public void alertLoginError() {
		Toast.makeText(activity, s.toString(), 4000).show();
	}

	@Override
	public boolean isResponseSuccess() {
		if (s == RESPONSE_SERVER_LOGIN.SUCCESS) {
			return true;
		} else {
			return false;
		}
	}

}
