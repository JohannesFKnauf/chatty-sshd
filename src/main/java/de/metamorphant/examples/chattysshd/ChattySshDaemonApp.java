package de.metamorphant.examples.chattysshd;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.AbstractGeneratorHostKeyProvider;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.subsystem.SubsystemFactory;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChattySshDaemonApp {
	private static final String BIND_ADDRESS = "127.0.0.1";      // only listen to localhost
	private static final int SSHD_PORT = 2224;
	private static Logger logger = LoggerFactory.getLogger(ChattySshDaemonApp.class);

	public static void main(String[] argv) {
		SshServer sshd = SshServer.setUpDefaultServer();
		sshd.setHost(BIND_ADDRESS);
		sshd.setPort(SSHD_PORT);
		
		// If the host key does not exist yet, it will be auto-created
		AbstractGeneratorHostKeyProvider hostKeyProvider = new SimpleGeneratorHostKeyProvider(Path.of("hostkey.ser"));
		hostKeyProvider.setAlgorithm("RSA");
		sshd.setKeyPairProvider(hostKeyProvider);

		List<SubsystemFactory> namedFactoryList = new ArrayList<SubsystemFactory>();
		namedFactoryList.add(new SftpSubsystemFactory());
		sshd.setSubsystemFactories(namedFactoryList);

		sshd.setPasswordAuthenticator(new LoggingPasswordAuthenticator());

		try {
			logger.info("Starting dummy sshd on bind_address:port " + BIND_ADDRESS + ":" + SSHD_PORT);
			sshd.start();
			while (true) {
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
