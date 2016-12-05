
/**
 * @author stan
 * create a simple thread using subclass of Thread
 */
class MyThread extends Thread {
	String message;
	int delay;
	
	public MyThread(String s, int d) {
		message = s;
		delay = d;
	}
	// スレッドが実行する処理
	public void run() {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {	
		}
		System.out.println(message + ":" + delay);
	}
}
public class SimpleThread {
	
	public static void main(String args[]) {
		MyThread mt1, mt2;
		// スレッドの作成
		mt1 = new MyThread("Hello", 5000);
		mt2 = new MyThread("Bye", 10000);
		
		// スレッドのスタート
		mt1.start();
		mt2.start();
	}
	

}
