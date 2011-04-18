package vooga.arcade.view.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VoogaViewerTest {

	String tom = "tom";
	VoogaViewer sv = new VoogaViewer(tom, tom, null, null);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShowError() {
		sv.showError(tom);
	}

}
