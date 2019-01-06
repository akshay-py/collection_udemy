package javacodegeeks;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Semaphore;

public class ConnectionLimiter {
	private final Semaphore semaphore;

	private ConnectionLimiter(int maxConcurrentRequests) {
		this.semaphore = new Semaphore(maxConcurrentRequests);
	}

	public URLConnection acquire(URL url) throws IOException, InterruptedException {
		semaphore.acquire();
		return url.openConnection();
	}

	public void release(URLConnection conn) {
		try {
			/*
			 * ... clean up here
			 */
		} finally {
			semaphore.release();
		}
	}
}
