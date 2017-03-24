package osgi.test.event.consumer;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class MyEventHandler implements EventHandler{
	@Override
	public void handleEvent(Event event) {
		System.out.println("consumer handle event started--"+event.toString());
		//sleep 5 seconds to test post event
		try {
			Thread.currentThread();
			Thread.sleep(5*1000);
		} 
		catch (InterruptedException e) {
			System.out.println("Exception happen in consumer handle event:\n"+e.getMessage());
		}
		System.out.println("consumer handle event ok--"+event.toString());
	}
}
