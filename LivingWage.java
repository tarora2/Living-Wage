import java.util.*;
import java.io.*;

/**
* Living and Minimum Wage
* @author Tushar Arora
*/
public class LivingWage{

    /** Total number of hours work in a year **/
    public static final int HOUR = 2080;

    /**
    * Prompts the user to display all states, search by state, minimum, or living wage, or display
    * all the gap states in the file. Catches the exceptions
    * Prompts the user for an option until they press "q"
    * @param args line arguments 
    */
    public static void main(String[] args){
        String response = "d";
        double minWa = 0.0;
        double maxWa = 0.0;
        int mini = 0;
        int maxi = 0;
        File f = new File(args[0]);
        if(args.length == 1 && f.exists()){
            Scanner console = new Scanner(System.in);
            while (!response.equals("q")) {
                System.out.println();
                System.out.println("Living Wage Information - Please enter an option below.");
                System.out.println();
                System.out.println("D - Display all states");
                System.out.println("S - Search by state");
                System.out.println("M - Search by minimum wage range");
                System.out.println("L - Search by living wage range");
                System.out.println("G - Display gap states");
                System.out.println("Q - Quit the program");
                System.out.println();
                System.out.print("Option: ");
                response  = console.next();
                response = response.toLowerCase();
                if(response.equals("d")){
                    try {
                        StateInfo[] states = getStateList(args[0]);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    System.out.println(getAllStates(getStateList(args[0])));
                }
                else if(response.equals("s")) {
                    try {
                        StateInfo[] states = getStateList(args[0]);
                    }
                    catch (IllegalArgumentException e) { 
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    System.out.print("State name (starts with/is): ");
                    String name = console.next();
                    System.out.println(searchByState(name, getStateList(args[0])));
                }
                else if(response.equals("m")){
                    try {
                        StateInfo[] states = getStateList(args[0]);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    System.out.print("Minimum Wage: ");
                    if (console.hasNextDouble()) { 
                        minWa = console.nextDouble();
                    }
                    else {
                        System.out.println("Invalid Minimum Value.");
                        continue;
                    }
                    System.out.print("Maximum Wage: ");   
                    if (console.hasNextDouble()) {
                        maxWa = console.nextDouble();
                    }
                    else {
                        System.out.println("Invalid Maximum Value.");
                        continue;
                    }
                    if(minWa <= maxWa){
                        String list = searchByMinimumWageRange(minWa,maxWa,getStateList(args[0]));
                        System.out.println(list);
                    }
                    else {
                        System.out.println("The minimum value is greater than the maximum value.");
                        continue;
                    }
                }
                else if(response.equals("l")){
                    try {
                        StateInfo[] states = getStateList(args[0]);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    System.out.print("Minimum value: ");
                    if (console.hasNextInt()) {
                        mini = console.nextInt();
                    }
                    else {
                        System.out.println("Not an integer value");
                        continue;
                    }
                    System.out.print("Maximum value: ");
                    if (console.hasNextInt()) {
                        maxi = console.nextInt();
                    }
                    else {
                        System.out.println("Not an Integer value");
                        continue;
                    }
                    if (mini <= maxi) {
                        String list = searchByLivingWageRange(mini,maxi,getStateList(args[0]));
                        System.out.println(list);
                    }
                    else {
                        System.out.println("The minimum value is greater than the maximum value.");
                        continue;
                    }
                }
                else if(response.equals("g")){
                    try {
                        StateInfo[] states = getStateList(args[0]);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    System.out.println(getGapStates(getStateList(args[0])));
                }
                else if(response.equals("q")){
                    System.out.println();
                    System.out.println("Goodbye!");
                    System.exit(1);
                }
                else {
                    System.out.println("Invalid Option!");
                }
            }
        }
        else if (args.length != 1) {
            System.out.println("Usage: java -cp bin LivingWage filename");
        }
        else {
            System.out.println("Unable to access input file: " + args[0]);
        }
    }
    
    /**
    * Creates an array of StateInfo objects for the command line argument file
    * @param filename name of the file from the command line argument
    * @return returns an array of StateInfo objects
    */
    public static StateInfo[] getStateList(String filename){
        int count = 0;
        Scanner line = null;
        try {
            line = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to access input file: " + filename);
        }
        while(line.hasNextLine()){
            line.nextLine();
            count++;
        }
        StateInfo[] stateList = new StateInfo[count];
        Scanner scan = null;
        try {
            scan = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        for(int i = 0; i < stateList.length; i++){
            Scanner lineScanner = new Scanner(scan.nextLine());
            lineScanner.useDelimiter(",");
            String name = lineScanner.next();
            double minimum = lineScanner.nextDouble();
            int living = lineScanner.nextInt();
            stateList[i] = new StateInfo(name, minimum, living);
        }
        return stateList;
    }
    
    /**
    * Creates a string table of all the states in the file
    * @param states array of StateInfo objects
    * @return returns the table of all the StateInfo objects
    */
    public static String getAllStates(StateInfo[] states){
        String list = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n";
        for(int i = 0; i < states.length; i++) {
            list += String.format("%-15s%6.02f%9d\n", states[i].getName(), 
            states[i].getMinimumWage(), 
            states[i].getLivingWage());
        }
        return list;
    }
    
    /**
    * Creates a string table of all the states in the file the user searched for
    * @param name the beginning letters, or name of the state, the user is searching for
    * @param states array of StateInfo objects
    * @return returns the table of all the StateInfo objects the user searched for
    */
    public static String searchByState(String name, StateInfo[] states){
        String list = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n";
        for(int i = 0; i < states.length; i++){
            if(states[i].getName().toLowerCase().startsWith(name.toLowerCase())){
                list += String.format("%-15s%6.02f%9d\n", states[i].getName(), 
                states[i].getMinimumWage(), states[i].getLivingWage());
            }
        }
        return list;
    }
    
    /**
    * Creates a string table of all the states that fall within the minimum wage range the user 
    * input.
    * @param min minimum of the minimum wage range
    * @param max maximum of the minimum wage range
    * @param states array of StateInfo objects
    * @return returns the table of all the states that fall within the minimum wage range
    */
    public static String searchByMinimumWageRange(double min, double max, StateInfo[] states){
        String list = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n";
        for(int i = 0; i < states.length; i++){
            if(states[i].getMinimumWage() <= max && states[i].getMinimumWage() >= min){
                list += String.format("%-15s%6.02f%9d\n", states[i].getName(), 
                states[i].getMinimumWage(), states[i].getLivingWage());
            }
        }
        return list;
    }
    
    /**
    * Creates a string table of all the states that fall within the living wage range the user 
    * input.
    * @param min minimum of the living wage range
    * @param max maximum of the living wage range
    * @param states array of StateInfo objects
    * @return returns the table of all the states that fall within the living wage range
    */
    public static String searchByLivingWageRange(int min, int max, StateInfo[] states){
        String list = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n";
        for(int i = 0; i < states.length; i++){
            if(states[i].getLivingWage() <= max && states[i].getLivingWage() >= min){
                list += String.format("%-15s%6.02f%9d\n", states[i].getName(), 
                states[i].getMinimumWage(), states[i].getLivingWage());
            }
        }
        return list;
    }
    
    /**
    * Creates a string table of all the states that have the income earned by two working adults
    * at minimum wage for 2080 hours a year be less than the living wage (gap states)
    * @param states array of StateInfo objects
    * @return returns the gap states
    */
    public static String getGapStates(StateInfo[] states){
        String list = "               Minimum  Living\n" + "   State        Wage     Wage\n"
                + "   -----       -------  ------\n";
        for(int i = 0; i < states.length; i++){
            if((states[i].getMinimumWage() * HOUR * 2) < states[i].getLivingWage()){
                list += String.format("%-15s%6.02f%9d\n", states[i].getName(), 
                states[i].getMinimumWage(), states[i].getLivingWage());
            }
        }
        return list;
    }
}
