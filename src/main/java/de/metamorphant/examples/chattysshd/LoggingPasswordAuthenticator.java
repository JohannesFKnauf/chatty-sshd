package de.metamorphant.examples.chattysshd;

import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingPasswordAuthenticator implements PasswordAuthenticator {
	private static Logger logger = LoggerFactory.getLogger(LoggingPasswordAuthenticator.class);

	@Override
	public boolean authenticate(String username, String password, ServerSession session)
			throws PasswordChangeRequiredException, AsyncAuthException {
		logger.debug(String.format("Login attempt by user '%s' with password '%s'\n", username, password));
		return false; // Authentication with our dummy server always fails
	}
}
