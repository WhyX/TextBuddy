import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	public String testFileName = "testFile";
	public TextBuddy tester = new TextBuddy(testFileName);
	
	@Test
	public void testAddText() {
		String[] test1 = {"add", "earth", "and", "moon"};
		String[] test2 = {"add", "1", "32", "54"};
		String[] test3 = {"add", "earth", "SUN", "1000"};
		String expected1 = "added successfully to testFile: \"earth and moon\"";
		String expected2 = "added successfully to testFile: \"1 32 54\"";
		String expected3 = "added successfully to testFile: \"earth SUN 1000\"";
				
		// test case 1
		assertEquals("adding item is not successful", expected1, tester.proceedUserAction(test1));
		
		// test case 2
		assertEquals("adding item is not successful", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		assertEquals("adding item is not successful", expected3, tester.proceedUserAction(test3));
	}

	@Test
	public void testDeleteText() {
		String[] test1 = {"delete", "3"};
		String[] test2 = {"delete", "1"};
		String[] test3 = {"delete", "50"};
		String expected1 = "testFile is empty";
		String expected2 = "deleted successfully from testFile: \"earth and moon\"";
		String expected3 = "Index out of range. Please try again";
		
		// test case 1
		assertEquals("deleting item is not successful", expected1, tester.proceedUserAction(test1));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		assertEquals("deleting item is not successful", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		assertEquals("deleting item is not successful", expected3, tester.proceedUserAction(test3));
	}

	@Test
	public void testDisplayTextList() {
		String[] test = {"display"};
		String expected1 = "testFile is empty";
		String expected2 = "1. earth and moon\n2. 32 and 74\n3. I am fine. Don't worry.";
		String expected3 = "1. earth and moon\n2. 32 and 74\n3. I am fine. Don't worry.\n4. JUMP OVER THE FENCE";
		
		// test case 1
		assertEquals("deleting item is not successful", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		assertEquals("deleting item is not successful", expected2, tester.proceedUserAction(test));
		
		// test case 3
		tester.proceedUserAction(new String[] {"add", "JUMP", "OVER", "THE", "FENCE"});
		assertEquals("deleting item is not successful", expected3, tester.proceedUserAction(test));
	}

	@Test
	public void testClearTextList() {
		String[] test = {"clear"};
		String expected1 = "testFile is empty";
		String expected2 = "all texts deleted from testFile successfully";
		
		// test case 1
		assertEquals("deleting item is not successful", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine.", "Don't", "worry."});
		assertEquals("deleting item is not successful", expected2, tester.proceedUserAction(test));	
	}
	
	@Test
	public void testSortTextList() {
		String[] test1 = {"add", "earth", "and", "moon"};
		String[] test2 = {"add", "1", "32", "54"};
		String[] test3 = {"add", "earth", "SUN", "1000"};
		String expected1 = "added to testFile: \"earth and moon\"";
		String expected2 = "added to testFile: \"1 32 54\"";
		String expected3 = "added to testFile: \"earth SUN 1000\"";
				
		// test case 1
		assertEquals("adding item is not successful", expected1, tester.proceedUserAction(test1));
		
		// test case 2
		assertEquals("adding item is not successful", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		assertEquals("adding item is not successful", expected3, tester.proceedUserAction(test3));
	}
}
