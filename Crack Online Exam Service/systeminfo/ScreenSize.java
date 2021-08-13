package systeminfo;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenSize extends ServiceConfig {
	private Dimension dimension;

	public ScreenSize() {
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
	}

	public int getSreenHeight() {
		return dimension.height;
	}

	public int getScreenWidth() {
		return dimension.width;
	}
}
