import junit.framework.*;

public class FormerTest extends TestCase {

	public void testCalculer() throws Exception {
		String expected = "\nTeam 1, 5 pts\n"
						+ "B4 B\n"
						+ "B3 C\n"
						+ "\nTeam 2, 5 pts\n"
						+ "B5 A\n"
						+ "B2 D\n";
		
		Former former = new Former("inputTestCalculer.txt", 2);
		assertEquals(expected, former.result);
	}
	
}
