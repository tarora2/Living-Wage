import java.util.Arrays;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Program to test the methods GetStateList(), GetAllStates(),
 * SearchByStates(), SearchByMinimumWageRange(), SearchByLivingWageRange(),
 * and GapStates() from LivingWage.java
 *
 * @author Tushar Arora
 */
public class LivingWageTest extends TestCase {

    public static final StateInfo IDAHO = new StateInfo("Idaho", 7.25, 52990);
    
    public static final StateInfo NEW_YORK = new StateInfo("New York", 9.7, 64705);
    
    public static final StateInfo OHIO = new StateInfo("Ohio", 8.15, 50423);
    
    public static final StateInfo[] THREE_STATES = { IDAHO, NEW_YORK, OHIO };

    public static final StateInfo FLORIDA = new StateInfo("Florida", 7.5, 50000);
    
    public static final StateInfo MIAMI = new StateInfo("Miami", 10.5, 65000);
    
    public static final StateInfo WASHINGTON = new StateInfo("Washington", 8.15, 63000);
    
    public static final StateInfo[] MY_STATES = { FLORIDA, MIAMI, WASHINGTON };

	/**
	 * Testing GetStateList method - three_states.csv
	 */
    @Test
    public void testGetStateListthreestates() {
        String filename = "three_states.csv";
        StateInfo[] actual = LivingWage.getStateList(filename);
        assertTrue(filename, Arrays.equals(THREE_STATES, actual));
    }

	/**
	 * Testing GetStateList method - my_states.csv
	 */
    @Test
    public void testGetStateListmystates() {
        String filename = "my_states.csv";
        StateInfo[] actual = LivingWage.getStateList(filename);
        assertTrue(filename, Arrays.equals(THREE_STATES, actual));
    }

	/**
	 * Testing GetAllStates method - three_states.csv
	 */
    @Test
    public void testGetAllStatesthreestates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Idaho            7.25    52990\n"
                + "New York         9.70    64705\n" + "Ohio             8.15    50423\n";
        String actual = LivingWage.getAllStates(THREE_STATES);
        assertEquals("Three states", expected, actual);
    }

	/**
	 * Testing GetAllStates method - my_states.csv
	 */
    @Test
    public void testGetAllStatesmystates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Florida          7.50    50000\n"
                + "Miami            10.5    65000\n" + "Washington       8.15    63000\n";
        String actual = LivingWage.getAllStates(MY_STATES);
        assertEquals("My States", expected, actual);
    }

	/**
	 * Testing SearchByState method - three_states.csv
	 */
    @Test
    public void testSearchByStatethreestates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Ohio             8.15    50423\n";
        String actual = LivingWage.searchByState("o", THREE_STATES);
        assertEquals("Three states", expected, actual);
    }

	/**
	 * Testing SearchByState method - my_states.csv
	 */
    @Test
    public void testSearchByStatemystates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Miami            10.5    65000\n";
        String actual = LivingWage.searchByState("m", MY_STATES);
        assertEquals("My states", expected, actual);
    }

	/**
	 * Testing SearchByMinimumWageRange method - three_states.csv
	 */
    @Test
    public void testSearchByMinimumWageRangethreestates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Idaho            7.25    52990\n"
                + "Ohio             8.15    50423\n";
        String actual = LivingWage.searchByMinimumWageRange(7, 9, THREE_STATES);
        assertEquals("Three states", expected, actual);
    }

	/**
	 * Testing SearchByMinimumWageRange method - my_states.csv
	 */
    @Test
    public void testSearchByMinimumWageRangemystates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Miami            10.5    65000\n";
        String actual = LivingWage.searchByMinimumWageRange(10, 12, MY_STATES);
        assertEquals("My states", expected, actual);
    }

	/**
	 * Testing SearchByLivingWageRange method - three_states.csv
	 */
    @Test
    public void testSearchByLivingWageRangethreestates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "New York         9.70    64705\n";
        String actual = LivingWage.searchByLivingWageRange(60000, 70000, THREE_STATES);
        assertEquals("Three states", expected, actual);
    }

	/**
	 * Testing SearchByLivingWageRange method - my_states.csv
	 */
    @Test
    public void testSearchByLivingWageRangemystates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Florida          7.5    50000\n";
        String actual = LivingWage.searchByLivingWageRange(45000, 55000, MY_STATES);
        assertEquals("My states", expected, actual);
    }

	/**
	 * Testing GetGapStates method - three_states.csv
	 */
    @Test
    public void testGetGapStatesthreestates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Idaho            7.25    52990\n"
                + "New York         9.7    64705\n" + "Ohio             8.15    50423\n";
        String actual = LivingWage.getGapStates(THREE_STATES);
        assertEquals("Three states", expected, actual);
    }

	/**
	 * Testing GetGapStates method - my_states.csv
	 */
    @Test
    public void testGetGapStatesmystates() {
        String expected = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n" + "Florida          7.50    52990\n"
                + "Miami            8.5     64705\n" + "Washington       8.15    50423\n";
        String actual = LivingWage.getGapStates(MY_STATES);
        assertEquals("My states", expected, actual);
    }

}
