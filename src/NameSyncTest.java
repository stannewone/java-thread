

/**
 * @author stan
 * @description 
 */
class NameList {
	private String name;
	public synchronized void put(String str) {
		name = str;
		notify();
	}
	public synchronized String get() {
		if (name == null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		notify();
		return name;	
	}
}

class ReadName extends Thread {
	NameList nameList;
	public ReadName(NameList arg) {
		nameList = arg;
	}
	public void run() {
		try {
			Thread.sleep((long)Math.random()*1000);
		} catch (InterruptedException e) {}
		System.out.println("read NAME: " + nameList.get());
	}
	
}
class WriteName extends Thread {
	NameList nameList;
	String wName = "TANAKA";
	public WriteName(NameList arg) {
		nameList = arg;
	}
	public void run() {
		try {
			Thread.sleep((long)Math.random()*1000);
		} catch (InterruptedException e) {}
		nameList.put(wName);
		System.out.println("write NAME: " + wName);
	}
	
}
public class NameSyncTest {
	public static void main(String args[]) {
		NameList nameList = new NameList();
		ReadName readName = new ReadName(nameList);
		WriteName writeName = new WriteName(nameList);
		readName.start();
		writeName.start();
	}

}
