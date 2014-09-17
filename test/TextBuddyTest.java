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
		String expected1 = "Added successfully to testFile: \"earth and moon\"";
		String expected2 = "Added successfully to testFile: \"1 32 54\"";
		String expected3 = "Added successfully to testFile: \"earth SUN 1000\"";
				
		// test case 1
		assertEquals("adding text is not successful", expected1, tester.proceedUserAction(test1));
		
		// test case 2
		assertEquals("adding text is not successful", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		assertEquals("adding text is not successful", expected3, tester.proceedUserAction(test3));
	}

	@Test
	public void testDeleteText() {
		String[] test1 = {"delete", "3"};
		String[] test2 = {"delete", "1"};
		String[] test3 = {"delete", "50"};
		String expected1 = "testFile is empty";
		String expected2 = "Deleted successfully from testFile: \"earth and moon\"";
		String expected3 = "Index out of range. Please try again";
		
		// test case 1
		assertEquals("test file should be empty", expected1, tester.proceedUserAction(test1));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		assertEquals("deleting text is not successful", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		assertEquals("index out of range", expected3, tester.proceedUserAction(test3));
	}

	@Test
	public void testDisplayTextList() {
		String[] test = {"display"};
		String expected1 = "testFile is empty";
		String expected2 = "1. earth and moon\n2. 32 and 74\n3. I am fine";
		String expected3 = "1. earth and moon\n2. 32 and 74\n3. I am fine.\n4. JUMP OVER THE FENCE";
		
		// test case 1
		assertEquals("test file should be empty", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		assertEquals("deleting text is not successful", expected2, tester.proceedUserAction(test));
		
		// test case 3
		tester.proceedUserAction(new String[] {"add", "JUMP", "OVER", "THE", "FENCE"});
		assertEquals("deleting text is not successful", expected3, tester.proceedUserAction(test));
	}

	@Test
	public void testClearTextList() {
		String[] test = {"clear"};
		String expected1 = "testFile is empty";
		String expected2 = "All texts deleted from testFile successfully";
		
		// test case 1
		assertEquals("test file should be empty", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "32", "and", "74"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		assertEquals("clearing all texts is not successful", expected2, tester.proceedUserAction(test));	
	}
	
	@Test
	public void testSortTextList() {
		String[] test = {"sort"};
		String expected1 = "After sorting:\n1. earth and moon\n2. jump over the moon\n3. nice";
		String expected2 = "After sorting:\n1. 35 problems\n2. earth and moon\n3. jump over the moon\n4. nice";
		String expected3 = "After sorting:\n1. Apple juice\n2. jump over the moon\n3. JUMPED OVER THE MOON\n4. nice";
				
		// test case 1
		tester.proceedUserAction(new String[] {"add", "nice"});
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "jump", "over", "the", "fence"});
		assertEquals("adding item is not successful", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "35", "problems"});
		assertEquals("adding item is not successful", expected2, tester.proceedUserAction(test));
		
		// test case 3
		tester.proceedUserAction(new String[] {"delete", "1"});
		tester.proceedUserAction(new String[] {"delete", "0"});
		tester.proceedUserAction(new String[] {"add", "JUMPED", "OVER", "THE", "MOON"});
		tester.proceedUserAction(new String[] {"add", "Apple", "juice"});
		assertEquals("adding item is not successful", expected3, tester.proceedUserAction(test));
	}
}
