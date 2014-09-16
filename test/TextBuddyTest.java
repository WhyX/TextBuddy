import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	public String testFileName = "testFile";
	
	// list of test inputs and expected outputs
	public String[] test1 = {"add", "earth", "and", "moon"};
	public String[] test2 = {"add", "1", "32", "54"};
	public String[] test3 = {"add", "earth", "SUN", "1000"};
	public String[] test4 = {"delete", "3"};
	public String[] test5 = {"delete", "1"};
	public String[] test6 = {"delete", "50"};
	public String[] test7 = {"display"};
	public String[] test8 = {"display"};
	public String[] test9 = {"display"};	
	public String[] test10 = {"clear"};
	public String[] test11 = {"clear"};
	public String[] test12 = {"clear"};
	public String expected1 = "added to testFile: \"earth and moon\"";
	public String expected2 = "added to testFile: \"1 32 54\"";
	public String expected3 = "added to testFile: \"earth SUN 1000\"";
	public String expected4 = "testFile is empty";
	public String expected5 = "deleted from testFile: \"earth and moon\"";
	public String expected6 = "Index out of range. Please try again";
	public String expected7 = "testFile is empty";
	public String expected8 = "all items in testFile displayed successfully";
	public String expected9 = "all items in testFile displayed successfully";
	public String expected10 = "testFile is empty";
	public String expected11 = "all content deleted from testFile";
	public String expected12 = "all content deleted from testFile";
	
	@Test
	public void testAddText() {
		TextBuddy tester = new TextBuddy(testFileName);
		
		// test case 1
		assertEquals("adding item is not successful", expected1, tester.proceedUserAction(test1));
		
		// test case 2
		assertEquals("adding item is not successful", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		assertEquals("adding item is not successful", expected3, tester.proceedUserAction(test3));
	}

	@Test
	public void testDeleteText() {
		TextBuddy tester = new TextBuddy(testFileName);
		
		// test case 4
		assertEquals("deleting item is not successful", expected4, tester.proceedUserAction(test4));
		
		// test case 5
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		assertEquals("deleting item is not successful", expected5, tester.proceedUserAction(test5));
		
		// test case 6
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		assertEquals("deleting item is not successful", expected6, tester.proceedUserAction(test6));
		
	}

	@Test
	public void testDisplayAllText() {
		TextBuddy tester = new TextBuddy(testFileName);
		
		// test case 7
		assertEquals("deleting item is not successful", expected7, tester.proceedUserAction(test7));
		
		// test case 8
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		assertEquals("deleting item is not successful", expected8, tester.proceedUserAction(test8));
		
		// test case 9
		tester.proceedUserAction(new String[] {"add", "jump", "over", "the", "fence"});
		assertEquals("deleting item is not successful", expected9, tester.proceedUserAction(test9));
		
	}

	@Test
	public void testClearAllText() {
		TextBuddy tester = new TextBuddy(testFileName);
		
		// test case 10
		assertEquals("deleting item is not successful", expected10, tester.proceedUserAction(test10));
		
		// test case 11
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		assertEquals("deleting item is not successful", expected11, tester.proceedUserAction(test11));
		
		// test case 12
		tester.proceedUserAction(new String[] {"add", "jump", "over", "the", "fence"});
		assertEquals("deleting item is not successful", expected12, tester.proceedUserAction(test12));
		
	}

}
