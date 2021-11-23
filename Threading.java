import java.util.ArrayList;
import java.util.List;

public class Threading {

	// this server will make first character search from 33 to 127
	static List<MD5Thread> threads = new ArrayList<MD5Thread>();
	
	
	// this method will get intervals and start thread for each interval
	public static void start_threads(String hashcode,List<String> ints, int passwordLength) {
		for(String inte:ints) {
			MD5Thread thread = new MD5Thread(inte,hashcode,passwordLength);
			threads.add(thread);
		}
		// starting threads
		for(var Thread:threads) {
			Thread.start();
		}
	}
	
	// method to stop threads
 	 public static void stop_threads() {
		for(MD5Thread thread:threads) {
            thread.interrupt();
		}
	}  
	
	// method to start the server
	public static void start_server(String hashcode,int n, int passwordLength) {
		List<String> intervals = intervals(n);
		start_threads(hashcode,intervals,passwordLength);
	}
	
	// this method takes number of threads and returns intervals to search 
	public static List<String> intervals(int n) {
		List<String> inter = new ArrayList<String>();
		int d = 127 - 33;
		int p = d/n;
		int x=33;
		List<Integer> steps = new ArrayList<Integer>();
		for(int i=33;i<127;i+=p) {
			x=i;
			steps.add(x);
		}
		if(steps.get(steps.size()-1)<126) {
			steps.set(steps.size()-1,126);
		}
		
		for(int i=0;i<steps.size()-1;i++) {
			int a = steps.get(i);
			int b;
			try {
				b = steps.get(i+1);
			}
			catch(Exception e) {b=steps.get(i);}
			//System.out.println(a+"-"+b);
			inter.add(a+"-"+b);
		}
		return inter;
	}
}
