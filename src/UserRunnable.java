import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author stan
 *
 */
public class UserRunnable extends Applet implements Runnable{
	Font myfont;
	Thread kick;
	int i;
	
	public void init() {
		myfont = new Font("Serif", Font.BOLD, 30);
	}
	public void start() {
		if (kick == null) {			// スレッドが作成されていない場合
			kick = new Thread(this);// スレッドの作成
			kick.start();			// スレッドのスタート
		}
	}
	// スレッドあ実行する処理
	public void run() {
		while (true) {
			i++;
			if (i > 100) i = 0;
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
		}
	}
	public void paint(Graphics g) {
		g.setFont(myfont);
		g.drawString("i = " + i, 10, 50);
	}
	public void stop() {
		if (kick != null) {			// スレッドが作成されている場合
			kick.stop();			// スレッドのストップ
			kick = null;
		}
	}
}
