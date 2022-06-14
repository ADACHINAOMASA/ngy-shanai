package lotdsp.entity.oldframework;

public interface Updatable<U> {

	public void update(U updater, UpdateInfo updateInfo);
	public void delete(UpdateInfo updateInfo);

}
