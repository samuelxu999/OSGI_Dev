package osgi.test.event.testevent;

import java.util.Dictionary;
import org.osgi.service.event.Event;

public class MyEvent extends Event{
	public static final String MY_TOPIC = "osgi/test/event/testevent/MyEvent";
	private String _topic="DefaultEvent";
	
	public MyEvent(String arg0, Dictionary<String,?> arg1) {
		super(MY_TOPIC, arg1);
		if(arg0=="0") {
			this._topic="MyPostEvent";
		}	
		if(arg0=="1") {
			this._topic="MySentEvent";
		}	
	}
	public MyEvent() {
		super(MY_TOPIC, (Dictionary<String,?>)null);
	}
	public String toString() {
		//return MY_TOPIC;
		return _topic;
	}
}
