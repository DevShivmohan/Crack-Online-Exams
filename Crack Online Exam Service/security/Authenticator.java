package security;

public interface Authenticator {
	abstract public void authenticationSuccess();

	abstract public void authenticationFailed(String error);
}
