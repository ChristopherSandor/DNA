import java.util.ArrayList;

/**
 * Driver to test various methods of the DNA class
 * 
 * You don't need to but you may update this class.
 * 
 * @author Seth Kelley
 * @author Aastha Gandhi
 */
public class Driver {
    public static void main(String[] args) {
        String setup[] = { "Setup Constructor", "New STRs File" }; // List of setup methods
        String methods[] = { "Create Unknown Profile", "Calculate Databases STRs", "Calculate Unknown Profile STRs",
                "Display Database Profiles", "Display Unknown Profile",
                "Find Matching Profile", "Find Possible Parents", "Quit" }; // List of methods to test
        String options[] = { "Test new STRs file", "Test new method on same files",
                "Quit" }; // List of options to choose from

        int repeatChoice = 1; // Controls the options to change file, retest methods or quit
        DNA studentDNASimulation = null; // The DNA Object the students will test methods on
        Profile studentUnknownProfile = null; // The unknown profile to run tests on

        // Enter loop to setup
        // Listed as a label here to break out of loop if needed
        outer: do {
            StdOut.println("\nPlease setup the constructor, than you may change to new files at a later time.\n");

            // Displays setup choices
            for (int i = 0; i < setup.length; i++) {
                System.err.printf("%d. %s\n", i + 1, setup[i]);
            }

            // Prompts user for choice from setup menu
            StdOut.print("\nEnter a number: ");
            int choice1 = StdIn.readInt();
            StdIn.readLine();
            StdOut.println();

            // Switch statement for the pick appropriate choice the user entered
            switch (choice1) {
                case 1:
                    studentDNASimulation = setupDNASimulation(); // Setup constructor
                    break;
                case 2:
                    newReadAllSTRs(studentDNASimulation); // Reads new allSTRs file | must have constructor setup first
                    break;
                default:
                    StdOut.println("Not a valid method to test!");
            }
            // Check for if contructor is null or not
            if (studentDNASimulation != null) {
                // Loops to tests methods if construcor isn't null
                do {
                    StdOut.println("\nWhat method would you like to test?\n");

                    StdIn.resync();
                    // Lists the possible methods to test
                    for (int i = 0; i < methods.length; i++) {
                        System.err.printf("%d. %s\n", i + 1, methods[i]);
                    }

                    // Prompt user for which method to test
                    StdOut.print("\nEnter a number: ");
                    int choice2 = StdIn.readInt();
                    StdIn.readLine();
                    StdOut.println();
                    // Switch the choice to test desired method
                    switch (choice2) {
                        case 1:
                            // Create unknown profile
                            studentUnknownProfile = testCreateUnknownProfile(studentDNASimulation);
                            break;
                        case 2:
                            // Creates STRs for all database profiles
                            testCalculateDatabaseSTRs(studentDNASimulation);
                            break;
                        case 3:
                            // Creates STRs for unknown profile
                            testCalculateUnknownProfileSTRs(studentDNASimulation, studentUnknownProfile);
                            break;
                        case 4:
                            // Displays the database profiles
                            displayDatabaseProfiles(studentDNASimulation);
                            break;
                        case 5:
                            // Displays the unknown profile
                            displayUnknownProfile(studentUnknownProfile);
                            break;
                        case 6:
                            // Tests matching profile
                            testFindMatchingProfile(studentDNASimulation, studentUnknownProfile);
                            break;
                        case 7:
                            // Tests possible parents
                            testFindPossibleParents(studentDNASimulation, studentUnknownProfile);
                            break;
                        case 8:
                            break outer; // Exits program
                        default:
                            StdOut.println("Not a valid method to test!");
                    }

                    StdIn.resync();
                    // Displays options to user
                    StdOut.println("\nWhat would you like to do now?\n");
                    for (int i = 0; i < options.length; i++) {
                        System.err.printf("%d. %s\n", i + 1, options[i]);
                    }

                    // Promts user for choice
                    StdOut.print("\nEnter a number: ");
                    repeatChoice = StdIn.readInt();
                    StdIn.readLine();
                    StdOut.println();

                    // Loops or exits depending on last choice
                } while (repeatChoice == 2);
            }
        } while (repeatChoice == 1);
    }

    /**
     * Initialization of the DNA Object for the student to do methods on
     * 
     * @return The DNA object
     */
    private static DNA setupDNASimulation() {
        StdOut.print("Enter the database file name: ");
        String database = StdIn.readLine();
        StdOut.print("Enter the STRs file name: ");
        String allSTRs = StdIn.readLine();

        DNA myDNASimulation = new DNA(database, allSTRs);

        return myDNASimulation;
    }

    /**
     * Checks to see is the DNA instance variable has been initialized
     * 
     * @param studentDNASimulation DNA Object to check initialization on
     * @return True if not initialized, false otherwise
     */
    private static boolean constructorNullCheck(DNA studentDNASimulation) {
        if (studentDNASimulation == null) {
            StdOut.println("Constructor not initialized yet!");
            return true;
        }
        return false;
    }

    /**
     * Checks to see if unknown profile has been initialized
     * 
     * @param studentUnknownProfile Unknown Profile to check initialization on
     * @return True if not initialized, false otherwise
     */
    private static boolean unknownProfileNullCheck(Profile studentUnknownProfile) {
        if (studentUnknownProfile == null) {
            StdOut.println("Unknown Profile not initialized yet!");
            return true;
        }
        return false;
    }

    /**
     * If a new allSTRs file is desired to be tested this method will prompt for a
     * new file
     * 
     * @param studentDNASimulation DNA Object to check initialization on
     */
    private static void newReadAllSTRs(DNA studentDNASimulation) {
        // Must be initialized first
        if (constructorNullCheck(studentDNASimulation))
            return;

        StdOut.print("Enter the new STRs file name: ");
        String allSTRs = StdIn.readLine();
        studentDNASimulation.readSTRsOfInterest(allSTRs);
    }

    /**
     * Creates the unknown profile
     * 
     * @param studentDNASimulation DNA Object to create the profile
     * @return The unknown profile
     */
    private static Profile testCreateUnknownProfile(DNA studentDNASimulation) {
        StdOut.print("Enter the file name of the Unknown Profile DNA: ");
        String unknownDNA = StdIn.readLine();

        Profile myUnknownProfile = studentDNASimulation.createUnknownProfile(unknownDNA);
        StdOut.println("Creating Unknown Profile...");
        StdOut.println(
                "Unknown Profile created successfully.\nSelect \"Calculate Unknown Profile STRs\" to find add STRs to the Unknown Profile");

        return myUnknownProfile;
    }

    /**
     * Calculates the STRs for all the profiles in the database
     * 
     * @param studentDNASimulation DNA Object to call the method to calculate
     *                             database STRs and get allSTRs
     */
    private static void testCalculateDatabaseSTRs(DNA studentDNASimulation) {
        studentDNASimulation.createDatabaseSTRs();
        StdOut.println("Sending profiles to the lab to determine their STRs...");
        StdOut.println(
                "Database STRs for each Profile have been calculated.\nSelect \"Display Database Profiles\" to see the profiles.");
    }

    /**
     * Calculates the STRs of the unknown profile
     *
     * @param studentDNASimulation  DNA Object to call the methods to calculate the
     *                              STRs
     * @param studentUnknownProfile Unknown profile to to be used for creation of
     *                              their STRs
     */
    private static void testCalculateUnknownProfileSTRs(DNA studentDNASimulation, Profile studentUnknownProfile) {
        // Must be initialized first
        if (unknownProfileNullCheck(studentUnknownProfile))
            return;

        studentDNASimulation.createProfileSTRs(studentUnknownProfile,
                studentDNASimulation.getSTRsOfInterest());
        StdOut.println("Sending Unknown Profile to the lab to determine their STRs...");
        StdOut.println(
                "Unknown Profile STRs have been calculated.\nSelect \"Display Unknown Profile\" to see the Unknown Profile");
    }

    /**
     * Displays the Database Profiles in full
     * 
     * @param studentDNASimulation DNA Object to get the database of profiles to
     *                             display
     */
    private static void displayDatabaseProfiles(DNA studentDNASimulation) {
        StdOut.println("All Profiles in the database:");
        for (int i = 0; i < studentDNASimulation.getDatabase().length; i++) {
            StdOut.println(studentDNASimulation.getDatabase()[i]);
        }
    }

    /**
     * Displays the unknown profile to display
     * 
     * @param studentUnknownProfile The profile to be displaayed
     */
    private static void displayUnknownProfile(Profile studentUnknownProfile) {
        if (unknownProfileNullCheck(studentUnknownProfile))
            return;

        StdOut.println("Unknown persons profile:");
        StdOut.println(studentUnknownProfile);
    }

    /**
     * Method to test for matching profile of the unknown profile
     * 
     * @param studentDNASimulation  DNA Object to call find matching profile method
     *                              and gets the database to use
     * @param studentUnknownProfile The unknown profile to pass in their C1 STRs
     */
    private static void testFindMatchingProfile(DNA studentDNASimulation, Profile studentUnknownProfile) {
        if (unknownProfileNullCheck(studentUnknownProfile))
            return;

        ArrayList<Profile> matches = studentDNASimulation.findMatchingProfiles(studentUnknownProfile.getS1_STRs());

        StdOut.println("Matching Profile(s) found:");

        // For each loop to print all possible matches
        for (Profile p : matches)
            StdOut.println(p);

    }

    /**
     * Method to test for finding possible parents
     * 
     * @param studentDNASimulation  DNA Object to call finding parents method and
     *                              getting the database to use
     * @param studentUnknownProfile Unknown profile to pass in the STRs for S1 and
     *                              S2
     */
    private static void testFindPossibleParents(DNA studentDNASimulation, Profile studentUnknownProfile) {
        if (unknownProfileNullCheck(studentUnknownProfile))
            return;

        ArrayList<Profile> parentsList = studentDNASimulation.findPossibleParents(
                studentUnknownProfile.getS1_STRs(),
                studentUnknownProfile.getS2_STRs());

        StdOut.println("Possible parents found:");
        for (int i = 0; i < parentsList.size(); i += 2) {
            StdOut.println("\n" + parentsList.get(i).getName() + ", " + parentsList.get(i + 1).getName());
        }
    }
}
