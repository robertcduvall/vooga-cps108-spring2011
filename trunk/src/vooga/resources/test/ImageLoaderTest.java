package vooga.resources.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import junit.framework.TestCase;

import org.junit.*;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

import vooga.core.VoogaGame;
import vooga.resources.images.ImageLoader;


public class ImageLoaderTest extends TestCase{
	private static final String TEST_FILE = "src/vooga/resources/test/test.xml";
	
	private ImageLoader loader;

	@Before
	public void setUp () {
		BaseLoader bsLoader = new BaseLoader(new BaseIO(this.getClass()), Color.MAGENTA);
		File testFile = new File(TEST_FILE);
		System.out.println(testFile.exists());
		loader = new ImageLoader(testFile, bsLoader);
	}
	
	@Test
	public void test() {
		
	}
}
