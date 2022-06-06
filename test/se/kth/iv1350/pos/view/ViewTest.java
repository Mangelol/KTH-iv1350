package se.kth.iv1350.pos.view;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.ExternalSystemCreator;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class ViewTest {
	Printer printer;
	ExternalSystemCreator creator;
	private View testInstance;
	private PrintStream originalSysOut;
	private ByteArrayOutputStream printoutBuffer;
	
	public ViewTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@Before
	public void setUp() {
		printer = new Printer();
		creator = new ExternalSystemCreator();
		Controller contr = new Controller(creator);
		testInstance = new View(contr);
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@After
	public void tearDown() {
		printer = null;
		creator = null;
		testInstance = null;
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}
	
	@Test
	public void testRunFakeExecution() {
		testInstance.runFakeExecution();
		String printout = printoutBuffer.toString();
		String expectedOutput = "Start";
		assertTrue("The UI failed to start correctly", printout.contains(expectedOutput));
	}
}

