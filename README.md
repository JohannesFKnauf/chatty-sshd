# Chatty SSHd logs passwords

This code contains a little dummy SSH server built using [Apache Mina SSHD](https://mina.apache.org/sshd-project/). It logs all entered passwords.

## How to build and run Chatty SSHd

```console
mvn package
java -jar target/chatty-sshd-0.0.1-SNAPSHOT.jar
```

## How to test-drive Chatty SSHd

Start the dummy server:

```console
java -jar target/chatty-sshd-0.0.1-SNAPSHOT.jar
```

In another window:

```console
ssh -p 2224 dummy@localhost
```

You should see something along the lines of

```console
$ java -jar target/chatty-sshd-0.0.1-SNAPSHOT.jar 
WARNING: sun.reflect.Reflection.getCallerClass is not supported. This will impact performance.
22:59:45.962 [main] INFO  de.metamorphant.examples.chattysshd.ChattySshDaemonApp - Starting dummy sshd on bind_address:port 127.0.0.1:2224
22:59:45.969 [main] INFO  org.apache.sshd.common.io.DefaultIoServiceFactoryFactory - No detected/configured IoServiceFactoryFactory using Nio2ServiceFactoryFactory
22:59:59.682 [sshd-SshServer[5c669da8](port=2224)-nio2-thread-3] DEBUG de.metamorphant.examples.chattysshd.LoggingPasswordAuthenticator - Login attempt by user 'dummy' with password 'dummypassword'

23:00:04.445 [sshd-SshServer[5c669da8](port=2224)-nio2-thread-4] DEBUG de.metamorphant.examples.chattysshd.LoggingPasswordAuthenticator - Login attempt by user 'dummy' with password 'anotherpassword'

```
