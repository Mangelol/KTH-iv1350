package se.kth.iv1350.pos.startup;
import org.junit.Before;
import org.junit.After;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class MainTest {
	private Main testInstance;
	private PrintStream originalSysOut;
	private ByteArrayOutputStream printoutBuffer;
	
	@Before
	public void setUp() {
		testInstance = new Main();
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}
	
	@After
	public void tearDown() {
		testInstance = null;
		printoutBuffer = null;
		System.setOut(originalSysOut);;
	}
	
	@Test
	public void testMain() {
		String[] args = null;
		Main.main(args);
		String printout = printoutBuffer.toString();
		String expectedOutput = "Started";
		assertEquals(printout.contains(expectedOutput), "The UI failed to start correctly");
	}
	

}
