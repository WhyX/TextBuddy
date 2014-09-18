import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	public String testFileName = "testFile";
	public TextBuddy tester = new TextBuddy(testFileName);
	
	@Test
	public void testAddText() {
		String[] test1 = {"add", "earth", "and", "moon"};
		String[] test2 = {"add", "1", "30", "55"};
		String[] test3 = {"add", "earth", "SUN", "thousand"};
		String expected1 = "Added successfully to testFile: \"earth and moon\"";
		String expected2 = "Added successfully to testFile: \"1 30 55\"";
		String expected3 = "Added successfully to testFile: \"earth SUN thousand\"";
				
		// test case 1
		assertEquals("fail to add text", expected1, tester.proceedUserAction(test1));
		
		// test case 2
		assertEquals("fail to add text", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		assertEquals("fail to add text", expected3, tester.proceedUserAction(test3));
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
		tester.proceedUserAction(new String[] {"add", "math", "and", "physic"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		assertEquals("fail to delete text", expected2, tester.proceedUserAction(test2));
		
		// test case 3
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		tester.proceedUserAction(new String[] {"add", "math", "and", "physic"});
		assertEquals("index out of range", expected3, tester.proceedUserAction(test3));
	}

	@Test
	public void testDisplayTextList() {
		String[] test = {"display"};
		String expected1 = "testFile is empty";
		String expected2 = "1. earth and moon\n2. math and physic\n3. I am fine.";
		String expected3 = "1. earth and moon\n2. math and physic\n3. I am fine.\n4. JUMP OVER THE FENCE";
		
		// test case 1
		assertEquals("test file should be empty", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "math", "and", "physic"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		assertEquals("display result is wrong", expected2, tester.proceedUserAction(test));
		
		// test case 3
		tester.proceedUserAction(new String[] {"add", "JUMP", "OVER", "THE", "FENCE"});
		assertEquals("display result is wrong", expected3, tester.proceedUserAction(test));
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
		tester.proceedUserAction(new String[] {"add", "math", "and", "physic"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		assertEquals("fail to clear all texts", expected2, tester.proceedUserAction(test));	
	}
	
	@Test
	public void testSortTextList() {
		String[] test = {"sort"};
		String expected1 = "After sorting:\n1. earth and moon\n2. jump over the fence\n3. nice";
		String expected2 = "After sorting:\n1. 20 problems\n2. earth and moon\n3. jump over the fence\n4. nice";
		String expected3 = "After sorting:\n1. Apple juice\n2. JUMPED OVER THE MOON\n3. earth and moon\n4. jump over the fence\n5. nice";
		String expected4 = "After sorting:\n1. 3 FRUIt\n2. FLU\n3. FRUIT\n4. FlU\n5. fruit";		
		String expected5 = "After sorting:\n1. 3 FRUIt\n2. FRUIT\n3. fRUIT\n4. fRUit\n5. fRuIt\n6. frUIT\n7. fruit";
		String expected6 = "testFile is empty";
		
		// test case 1
		tester.proceedUserAction(new String[] {"add", "nice"});
		tester.proceedUserAction(new String[] {"add", "earth", "and", "moon"});
		tester.proceedUserAction(new String[] {"add", "jump", "over", "the", "fence"});
		assertEquals("fail to sort text list", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "20", "problems"});
		assertEquals("fail to sort text list", expected2, tester.proceedUserAction(test));
		
		// test case 3
		tester.proceedUserAction(new String[] {"delete", "1"});
		tester.proceedUserAction(new String[] {"delete", "0"});
		tester.proceedUserAction(new String[] {"add", "JUMPED", "OVER", "THE", "MOON"});
		tester.proceedUserAction(new String[] {"add", "Apple", "juice"});
		assertEquals("fail to sort text list", expected3, tester.proceedUserAction(test));
		
		// test case 4
		tester.proceedUserAction(new String[] {"clear"});
		tester.proceedUserAction(new String[] {"add", "FRUIT"});
		tester.proceedUserAction(new String[] {"add", "FLU"});
		tester.proceedUserAction(new String[] {"add", "FlU"});
		tester.proceedUserAction(new String[] {"add", "3", "FRUIt"});
		tester.proceedUserAction(new String[] {"add", "fruit"});
		assertEquals("fail to sort text list", expected4, tester.proceedUserAction(test));
		
		// test case 5
		tester.proceedUserAction(new String[] {"delete", "2"});
		tester.proceedUserAction(new String[] {"delete", "3"});
		tester.proceedUserAction(new String[] {"add", "frUIT"});
		tester.proceedUserAction(new String[] {"add", "fRUit"});
		tester.proceedUserAction(new String[] {"add", "fRuIt"});
		tester.proceedUserAction(new String[] {"add", "fRUIT"});
		assertEquals("fail to sort text list", expected5, tester.proceedUserAction(test));
		
		// test case 6
		tester.proceedUserAction(new String[] {"clear"});
		assertEquals("text file should be empty", expected6, tester.proceedUserAction(test));
	}
	
	@Test
	public void testSearchText() {
		String[] test = {"search", "Apple"};
		String expected1 = "testFile is empty";
		String expected2 = "Apple is not found in testFile";
		String expected3 = "Search result:\n1. apple is good";
		
		// test case 1
		assertEquals("test file should be empty", expected1, tester.proceedUserAction(test));
		
		// test case 2
		tester.proceedUserAction(new String[] {"add", "apple", "is", "good"});
		tester.proceedUserAction(new String[] {"add", "math", "and", "physic"});
		tester.proceedUserAction(new String[] {"add", "I", "am", "fine."});
		assertEquals("key word is not contained in any of the texts", expected2, tester.proceedUserAction(test));
		
		// test case 3
		tester.proceedUserAction(new String[] {"add", "JUMP", "OVER", "THE", "FENCE"});
		assertEquals("search result is wrong", expected3, tester.proceedUserAction(test));
	}
}
