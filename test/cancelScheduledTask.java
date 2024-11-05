import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


public class cancelScheduledTask{
	static ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(10);
	static ExecutorService regular = Executors.newFixedThreadPool(2);
	static ConcurrentHashMap<String,  AtomicReference<ScheduledFuture<?>>> log = new ConcurrentHashMap<>();

	public static void main(String args[]) {
		// Submit to 'scheduled'
		ScheduledFuture<?> future = scheduled.scheduleWithFixedDelay(() -> {
			int s = 0;
			for (int i = 0; i < 10000; i++) {
				s++;
			}
			System.out.println("done once!");
		}, 80, 50, TimeUnit.MILLISECONDS);

		// log the task:
		String name = "s";
		cancelScheduledTask.log.put(name, new AtomicReference<>(future));

		regular.submit(() -> {
			try { 
				Thread.sleep(1000);
			} catch(Exception e) {
				e.printStackTrace();
			}
			ScheduledFuture<?> ftr;
			// Lookup for task-entry:
			while(log.get("s") == null) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// Cancel the task:
			ftr = log.get("s").get();
			ftr.cancel(false);
			while(!ftr.isCancelled()) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// Print out whether the task was cancelled:
			System.out.println("The Task was successfully canceled!");
			
			cancelScheduledTask.scheduled.shutdown();
			cancelScheduledTask.regular.shutdown();
		});
	}
}
