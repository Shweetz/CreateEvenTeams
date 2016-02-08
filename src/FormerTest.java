import junit.framework.*;

public class FormerTest extends TestCase {

	public void test2Teams4Players() throws Exception {
		String expected = "\nTeam 1, 5 pts\n"
						+ "B4 B\n"
						+ "B3 C\n"
						+ "\nTeam 2, 5 pts\n"
						+ "B5 A\n"
						+ "B2 D\n";
		
		Former former = new Former("inputTest2Teams4Players.txt", 2);
		assertEquals(expected, former.result);
	}
	
	public void test2Teams7Players() throws Exception {
		String expected = "\nTeam 1, 21 pts\n"
						+ "G3 B\n"
						+ "P3 D\n"
						+ "\nTeam 2, 21 pts\n"
						+ "B3 A\n"
						+ "G3 F\n"
						+ "\nTeam 3, 21 pts\n"
						+ "S3 C\n"
						+ "B3 G\n";
		
		Former former = new Former("inputTest2Teams7Players.txt", 2);
		assertEquals(expected, former.result);
	}
	
}
