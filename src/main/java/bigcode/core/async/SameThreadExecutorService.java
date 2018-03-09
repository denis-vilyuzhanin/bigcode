package bigcode.core.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SameThreadExecutorService implements ExecutorService {

	private static final SameThreadExecutorService INSTANCE = new SameThreadExecutorService();
	
	public SameThreadExecutorService getInstance() {
		return INSTANCE;
	}

	@Override
	public void execute(Runnable command) {
		command.run();
	}
	
	@Override
	public <T> Future<T> submit(Callable<T> task) {
		CompletableFuture<T> result = new CompletableFuture<>();
		try {
			result.complete(task.call());
		} catch (Exception e) {
			result.completeExceptionally(e);
		}
		return result;
	}

	@Override
	public Future<?> submit(Runnable task) {
		CompletableFuture<?> result = new CompletableFuture<>();
		try {
			task.run();
			result.complete(null);
		} catch (Exception e) {
			result.completeExceptionally(e);
		}
		return result;

	}

	@Override
	public <T> Future<T> submit(Runnable task, T futureResult) {
		CompletableFuture<T> result = new CompletableFuture<>();
		try {
			task.run();
			result.complete(futureResult);
		} catch (Exception e) {
			result.completeExceptionally(e);
		}
		return result;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return true;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		return doInvokeAll(tasks);
	}

	
	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		return doInvokeAll(tasks);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		for(Callable<T> task : tasks) {
			try {
				return task.call();
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return invokeAny(tasks);
	}

	@Override
	public boolean isShutdown() {
		return false;
	}

	@Override
	public boolean isTerminated() {
		return false;
	}

	@Override
	public void shutdown() {
	}

	@Override
	public List<Runnable> shutdownNow() {
		return null;
	}
	
	private <T> List<Future<T>> doInvokeAll(Collection<? extends Callable<T>> tasks) {
		List<Future<T>> results = new ArrayList<>(tasks.size());
		for(Callable<T> task : tasks) {
			CompletableFuture<T> future = new CompletableFuture<>();
			try {
				T result = task.call();
				future.complete(result);
			} catch (Exception e) {
				future.completeExceptionally(e);
			}
			results.add(future);
		}
		return results;
	}

	
}
