package osgi.test.event.testevent;

import java.util.Dictionary;
import org.osgi.service.event.Event;

public class MyEvent extends Event{
	public static final String MY_TOPIC = "osgi/test/event/testevent/MyEvent";
	public MyEvent(String arg0, Dictionary arg1) {
		super(MY_TOPIC, arg1);
	}
	public MyEvent() {
		super(MY_TOPIC, (Dictionary)null);
	}
	public String toString() {
		return "MyEvent";
	}
}
