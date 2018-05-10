package com.liferay.training;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.Authenticator;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author krisztian.rostas
 */
@Component(immediate = true, property = { "key=auth.pipeline.post" }, service = Authenticator.class)
public class EmailAddressAuthenticator implements Authenticator {

	@Override
	public int authenticateByEmailAddress(long companyId, String emailAddress, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap) throws AuthException {
		// TODO Auto-generated method stub
		return validateEmailAdress(emailAddress);
	}

	@Override
	public int authenticateByScreenName(long companyId, String screenName, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap) throws AuthException {

		String emailAddress = _userLocalService.fetchUserByScreenName(companyId, screenName).getEmailAddress();

		return validateEmailAdress(emailAddress);
	}

	@Override
	public int authenticateByUserId(long companyId, long userId, String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {
		//
		String emailAddress = _userLocalService.fetchUserById(userId).getEmailAddress();

		return validateEmailAdress(emailAddress);
	}

	private int validateEmailAdress(String emailAddress) throws AuthException {

		if (emailAddress == null) {

			String msg = "Email address validator is unavailable, cannot authenticate user";
			System.out.println(msg);

			throw new AuthException(msg);
		}

		if (emailAddress.equals("test@liferay.com")) {
			System.out.println("*** Test user has been successfully logged in! ***");
			return Authenticator.SUCCESS;
		}

		System.out.println("*** Only test user can log into the portal, all other users are BANNED ***");
		return Authenticator.FAILURE;

	}

	@Reference
	private volatile UserLocalService _userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(EmailAddressAuthenticator.class);
}