package iis.core.security;

import iis.domain.User;
import iis.service.UserService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		try {
			String currentUserlogin = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
			User user = userService.findByLogin(currentUserlogin);
			String currentTime = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss").format(new Date()).toString();
			
			String loggedTime = user.getLogged();
			if (loggedTime == null) {
				loggedTime="";
			}
			if (loggedTime.length()>400) {
				loggedTime = loggedTime.substring(250);
			}
			loggedTime = loggedTime + currentTime + ";";
			user.setLogged(loggedTime);
			
			userService.update(user);
		} catch (Exception e) {
			//TODO check/log exception
		} finally {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
