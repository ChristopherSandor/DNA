

import java.util.ArrayList;

/**
 * DNA is the carrier of genetic information of living things and is used quite
 * a few ways in todays age. What can we find out when looking at DNA?
 * To name a few, we can find out to whom it belongs and potential
 * relationships for families.
 * 
 * DNA is made up of a sequence of molecules, Some portions are the same or
 * similar amongst all humans and other portions have a higher diversity.
 * The areas we can look at to identify individuals are called STRs (Short
 * Tandem Repeats), this is where short DNA segments are repeated back to back
 * ex. AGATAGATAGATACGTACGT Here you see one STR called AGAT repeated three
 * times before being stopped by the STR ACGT repeated twice (this is just one
 * case out of many representations)
 * 
 * Using multiple STRs we can narrow our search down to be more specific to any
 * kind of information we are interested in.
 * 
 * @author Seth Kelley
 * @author Aastha Gandhi
 */

public class DNA {

    // These two instance variables are initialized and populated by 
    // createDatabaseOfProfiles() and readSTRsOfInterest()
    private Profile[] database;       // Holds all of the profile objects.
    private String[] STRsOfInterest; // Holds all of the STRs as Strings we are interested in looking for.
                                      // These STRs are going to be used to process the DNA of everyone in the database.

    /**
     * Initialize the array of Profile objects and the STRs of interest.
     * 
     * @param databaseFile The file containing all of the names and their DNA
     *                     sequences
     * @param strsFile     The file with all the STRs of interest
     */
    public DNA ( String databaseFile, String STRsFile ) {

        /*** DO NOT EDIT ***/
        createDatabaseOfProfiles(databaseFile); // Calls createDatabase method to initialize the database array
        readSTRsOfInterest(STRsFile); // Calls readAllSTRs method to initialize the allSTRs array
    }

    /**
     * Create the database array of profiles and insert all the profiles from file.
     * 
     * Each profile includes a persons' name and two DNA sequences.
     * 
     * 1. Reads the number of profiles from the input file AND create the database array to
     *    hold that number profiles. 
     * 2. Reads people profiles from the input file @filename.
     * 3. For each person in the file
     *      a. Creates a Profile object with the information from file (see input file format below).
     *         Set the profile C1_STRs and C2_STRs to null.
     *      b. Insert the newly created profile into the next position in the database array (instance variable).
     * 
     * Input file format:
     *      - 1 line containing an integer with the number of profiles/people in the file, call that number p.
     *      - for each p profiles in the file
     *          - 1 line containing the person's name
     *          - 1 line containing the first sequence of STRs
     *          - 1 line containing the second sequence of STRs
     * 
     * You can use StdIn.readLine() to read 1 (one) line from the file.
     * StdIn.setFile() opens the file filename for reading.
     * 
     * @param filename The input file containing the persons name and DNA sequences
     */
    public void createDatabaseOfProfiles ( String filename ) {

        StdIn.setFile(filename); // DO NOT remove this line, keep it as the first line in the method.

        int p = Integer.parseInt(StdIn.readLine());
        database = new Profile[p];

        for(int i = 0; i < p; i++){
            String name = StdIn.readLine();
            String sequence1 = StdIn.readLine();
            String sequence2 = StdIn.readLine();
            STR[] one = null;
            STR[] two = null;

            database[i] = new Profile(name, one, two, sequence1, sequence2);

        }
    }

    /**
     * Create the STRsOfInterest array of STR and insert all STRs from file.
     * 
     * 1. Reads the number of STRs from the input file AND create the STRsOfInterest array to
     *    hold that number STRs. 
     * 2. For each STR in the file
     *      Insert into STRsOfInterest
     * 
     * Input file format:
     *      - 1 line containing an integer with the number of STRs in the file, call that number s.
     *      - s lines of STRs
     * 
     * You can use StdIn.readLine() to read 1 (one) line from the file.
     * StdIn.setFile() opens the file filename for reading.
     * 
     * @param filename The input file containing all the STRs
     */
    public void readSTRsOfInterest ( String filename ) {

        StdIn.setFile(filename); // DO NOT remove this line, keep as the first line in the method.
        int p = Integer.parseInt(StdIn.readLine());
        STRsOfInterest = new String[p];
        
        for(int i = 0; i < p; i++){
            String str = StdIn.readLine();
            STRsOfInterest[i] = str;
        }
    }

    /**
     * Creates the Profile for the unknown DNA sequence from filename.
     * 
     * 1. Set the Profile name to "Unknown" because they are currently Unknown.
     * 2. Set the Profile S1_STRs and S2_STRs to null (later to be calculated).
     * 3. Set the Profile sequence1 to be the first line of the file.
     * 4. Set the Profile sequence2 to be the second line of the file.
     * 5. Return the Profile object
     * 
     * File format (only two lines):
     *      - first line containing a DNA sequence
     *      - second line containing a DNA sequence
     * 
     * @param filename The input file for the unknown DNA sequence
     * @return Returns a Profile object for the unknown DNA sequence
     */
    public Profile createUnknownProfile ( String filename ) {

	StdIn.setFile(filename); // DO NOT remove this line, keep as the first line in the method.

    String name = "Unknown";
    String sequence1 = StdIn.readLine();
    String sequence2 = StdIn.readLine();

    Profile unknown = new Profile(name, null, null, sequence1, sequence2);

    return unknown;
    }

    /**
     * Given a DNA sequence and a singular STR, this method will create a
     * STR Object with the STR name and the longest number of repeats of that STR 
     * within the DNA sequence.
     * 
     * @param sequence The DNA sequence (String) to be looked at
     * @param STR      The STR (String) to look for in the DNA sequence
     * @return         The STR object with the name and longest number of repeats
     */
    public STR findSTRInSequence ( String sequence, String STR ) {

        int STRcount = 0;
        int STRcountMAX = 0;

        for(int i = 0; i <= (sequence.length() - STR.length()); i++){
            String cutString = sequence.substring(i, i + STR.length());
            if(STR.equals(cutString)){
                STRcount++;
                i+= STR.length()-1;
                if(STRcount > STRcountMAX){
                    STRcountMAX = STRcount;
                }
            } 
            else {
                STRcount = 0;
            }

        }

        STR strObject = new STR(STR, STRcountMAX);

        return strObject; // update the return value
    }

    /**
     * Compute the STRs (S1_STRs and S2_STRs) for the profile.
     * 
     * USE the findSTRInSequence method.
     * 
     * @param profile The profile of the that the method will compute the STRs array for
     * @param allSTRs The list of STRs to be looked for in the profiles DNA sequences
     */
    public void createProfileSTRs ( Profile profile, String[] allSTRs ) {

        String seqOne = profile.getSequence1();
        String seqTwo = profile.getSequence2();

        STR[] arrayOne = new STR[allSTRs.length];
        STR[] arrayTwo = new STR[allSTRs.length];

        // Adds items and STRs to lists, respectfully
        for(int i = 0; i < allSTRs.length; i++){
            arrayOne[i] = findSTRInSequence(seqOne, allSTRs[i]);
        }

        for(int i = 0; i < allSTRs.length; i++){
            arrayTwo[i] = findSTRInSequence(seqTwo, allSTRs[i]);
        }

        profile.setS1_STRs(arrayOne);
        profile.setS2_STRs(arrayTwo);
    }

    /**
     * Call createProfileSTRs() for each profile in the database.
     */
    public void createDatabaseSTRs() {

        for(int i = 0; i < database.length; i++){
            createProfileSTRs(database[i], STRsOfInterest);
        }

    }
    

    /**
     * Compares two STR arrays to determines if they are identical.
     * 
     * Two STR arrays are identical if for every i in the array, the objects 
     * at s1[i] and s2[i] contain the same information. 
     *      - s1[0] matches s2[0], and
     *      - s1[1] matches s2[1], and so on.
     * 
     * Assume the @s1 and @s2 are of the same length.
     * 
     * @param s1 STR array from one profile.
     * @param s2 STR array from another profile.
     * @return Returns true if the objects in the arrays are a complete match, otherwise false
     */
    public boolean identicalSTRs ( STR[] s1, STR[] s2 ) {

        for(int i = 0; i < s1.length; i++){
            if(!s1[i].equals(s2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to find a profile in the database that matches the
     * unkown profile's array of STRs found in sequence1.
     * 
     * Use identicalSTRs()
     * 
     * @param unknownProfileS1_STRs The sequence1 STRs of the person the method is searching for.
     * @return                      Returns an ArrayList with all matching profile(s). It will return 
     *                              an empty ArrayList if no match is found.
     */
    public ArrayList<Profile> findMatchingProfiles ( STR[] unknownProfileS1_STRs ) {

        ArrayList<Profile> list = new ArrayList<>();

        for(int i = 0; i < database.length; i++){
            if(identicalSTRs(database[i].getS1_STRs(), unknownProfileS1_STRs) == true){
                list.add(database[i]);
            }
        }
	    return list;
    }

    /**
     * 
     * A punnet square is a simple way of discovering all of the potential combinations of 
     * genotypes that can occur in children, given the genotypes of their parents.
     * 
     * This method acts as a punnet square checker to check if all the STRs in 
     * the array match between the parents and offspring for any one square in the
     * punnet square.
     * 
     * This method used in the findPossibleParents method. 
     *
     * @param firstParent                The STRs of one parent
     * @param inheritedFromFirstParent   The one pairing of STRs for the offspring
     * @param secondParent               The STRs of the other parent
     * @param inheritedFromSecondParent  The second pairing of STRs for the offspring
     * @return Returns true if:
     *           - the STRs from the first parent matches the offspring STRs inherited from the first parent.
     *           AND
     *          - the STRs from the second parent matches the offspring STRs inherited from the second parent.
     */
    public boolean punnetSquare( STR[] firstParent,  STR[] inheritedFromFirstParent, 
                                 STR[] secondParent, STR[] inheritedFromSecondParent ) {

        /* DO NOT EDIT */

        for ( int i = 0; i < firstParent.length; i++ ) {

            if ( !(firstParent[i].equals(inheritedFromFirstParent[i]) && secondParent[i].equals(inheritedFromSecondParent[i])) ) {
                return false; // Returns false if there is a discrepency
            }
        }
        return true; 
    }

    /**
     * Looks at the STR sequences of any given person and tries to find the
     * potential relatives (parents) of that person based on their STR sequences
     * 
     * @param S1_STRs  The first list of STRs contained by the offspring that one
     *                 parent passed down
     * @param S2_STRs  The second list of STRs contained by the offspring that the
     *                 other parent passed down
     * @return Returns the array of profiles that are related
     */
    public ArrayList<Profile> findPossibleParents ( STR[] S1_STRs, STR[] S2_STRs ) {

        /* FIX THIS METHOD */

         ArrayList<Profile> possibleParent1 = new ArrayList<>();
         ArrayList<Profile> possibleParent2 = new ArrayList<>();

         for (int i = 0; i < database.length; i++ ) {

            if (identicalSTRs(database[i].getS2_STRs(), S1_STRs)) {
                possibleParent1.add(database[i]);
            }
            if (identicalSTRs(database[i].getS1_STRs(), S2_STRs)) {
                 possibleParent2.add(database[i]);
             }
            if (identicalSTRs(database[i].getS1_STRs(), S1_STRs)) {
                 possibleParent1.add(database[i]);
             }
            if (identicalSTRs(database[i].getS2_STRs(), S2_STRs)) {
                 possibleParent2.add(database[i]);
             }
        }

        ArrayList<Profile> parentList = new ArrayList<>();

         for (int p1 = 0; p1 < possibleParent1.size(); p1++ ) {

            for (int p2 = 0; p2 < possibleParent2.size(); p2++ ) {

                if (!possibleParent1.get(p1).equals(possibleParent2.get(p2)) ) {

                    if ( punnetSquare(possibleParent2.get(p2).getS2_STRs(), S2_STRs, possibleParent1.get(p1).getS2_STRs(), S1_STRs)) {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } else if (punnetSquare(possibleParent2.get(p2).getS1_STRs(), S1_STRs, possibleParent1.get(p1).getS1_STRs(), S1_STRs)) {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } else if (punnetSquare(possibleParent2.get(p2).getS2_STRs(), S2_STRs, possibleParent1.get(p1).getS2_STRs(), S2_STRs)) {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    } else if (punnetSquare(possibleParent2.get(p2).getS1_STRs(), S2_STRs, possibleParent1.get(p1).getS1_STRs(), S1_STRs)) {
                        parentList.add(possibleParent1.get(p1));
                        parentList.add(possibleParent2.get(p2));
                    }
                }
            }
        }
        return parentList;
    }

    /**
     * Getter for the database/profiles instance variable
     * 
     * @return The database instance variable
     */
    public Profile[] getDatabase() {

        /* DO NOT EDIT */

        return database;
    }

    /**
     * Getter for allSTRs instance variable
     * 
     * @return The allSTRs instance variable
     */
    public String[] getSTRsOfInterest() {

        /* DO NOT EDIT */

        return STRsOfInterest;
    }
}
