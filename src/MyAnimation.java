import java.awt.Graphics;
import java.applet.*;
import java.awt.Image;

// スレッドのためのRunnableインタフェースを実装
public class MyAnimation extends Applet implements Runnable {
	Image im[] = new Image[10];
	Thread kick;
	AudioClip snd;
	int i, j = 0;
	
	public void init() {
		// イメージデータ、オーディオデータの獲得
		for (int i = 0; i <= 9; i++) 
			im[i] = getImage(getDocumentBase(), "images/T" + i + ".jpg");
		snd = getAudioClip(getDocumentBase(), "sounds/The Libertines - Don't Look Back Into The Sun.mp3");		
	}
	public void start() {
		if (kick == null) {
			// スレッドの作成、実行時に使用するrun()メソッドはthisのものを使用	
			kick = new Thread(this);
			kick.start();// スレッドの開始
		}
		snd.loop();
	}
	public void run() {
		while (true) {
			if (j > 9) j = 0;
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
			j++; // 次に表示するImage配列の添字
		}
	}
	public void paint(Graphics g) {
		g.drawImage(im[j], 10, 10, this);
	}
	public void stop() {
		if (kick != null) {
			kick.stop();
			kick = null;
		}
	}
	
	
}
