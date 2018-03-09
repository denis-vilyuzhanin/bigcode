package bigcode.core.async;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class AsyncComponent {

	private static final ExecutorService DEFAULT_EXECUTOR = new SameThreadExecutorService();
	static {
		// TODO: put logic to change default executor based on system properties
	}
	private ExecutorService executor = DEFAULT_EXECUTOR;

	protected void async(Runnable task) {
		executor.execute(task);
	}

	protected <T> CompletableFuture<T> async(Callable<T> computation) {
		CompletableFuture<T> future = new CompletableFuture<>();
		executor.submit(() -> {
			try {
				future.complete(computation.call());
			} catch (Exception e) {
				future.completeExceptionally(e);
			}
		});
		return future;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

}
