package security;

public interface TaskListener {
	abstract public void onSuccess(String message);

	abstract public void onFailure(String error);
}
