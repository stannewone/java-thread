import java.util.Stack;

/**
 * @author stan
 * @description 
 */

class SyncStack {
	private int idx = 0;
	private char [] buf = new char[5];
	
	// メソッド実行時にロック・フラグを獲得する。獲得できない場合ロックする。
	public synchronized void push(char c) { // スタックがいっぱいの場合、プッシュしてない
		if (idx == buf.length) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		notify();
		buf[idx] = c;
		idx++;
	}
	// メソッド実行時にロック・フラグを獲得する。獲得できない場合ロックする。
	public synchronized char pop() {
		if (idx == 0) { // スタックが空の場合、ポップ動作が実行されない
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		notify();
		idx--;
		return buf[idx];
	}
}

// スタックへデータをプッシュするThreadクラス
class Producer extends Thread {
	SyncStack theStack;
	
	char c[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
	public Producer(SyncStack s) { // コンストラクタ
		theStack = s;
	}
	
	public void run() {
		// 10回プッシュ
		for (int i = 0; i < 10; i++) {
			theStack.push(c[i]);
			System.out.println("Pushed: " + c[i]);
			try {
				Thread.sleep((long)(Math.random() * 1000)); // 待ち時間
			} catch (InterruptedException e) {}
		}
	}
}
// スタックからデータをポップするThreadクラス
class Costumer extends Thread {
	SyncStack theStack;
	public Costumer(SyncStack s) {
		theStack = s;
	}
	public void run() {
		// １０回ポップ
		char c;
		for (int i = 0; i < 10; i++) {
			c = theStack.pop();
			System.out.println("Popped: " + c);
			try {
				Thread.sleep((long)Math.random()*1000);
			} catch (InterruptedException e) {}
			
		}
	}
}
public class CompleteSyncTest {
	public static void main(String args[]) {
		SyncStack stack = new SyncStack();
		Producer source = new Producer(stack);
		Costumer sink = new Costumer(stack);
		
		source.start();
		sink.start();
	}

}
