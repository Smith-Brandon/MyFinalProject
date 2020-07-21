package hibernate_package;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// This is the JUNIT Test for getDiv on MainPage
class TestDivCalc {
		@Test
		void testGetDiv() {
			Float result = MainPage.getDiv((float)12.000, (float)3.0000, 13); 
			assertEquals((float)4.68, result);
		}
}
