import java.util.Arrays;

/**
 * Profile Class to store the the profile of the person. 
 * 
 * More specifically it holds the information about the 23rd pair of chromosomes 
 * that is used for identifying parent/offspring relationships.
 * 
 * The profile includes information such as name, STRs, and DNA sequence.
 * 
 * DO NOT EDIT
 * 
 * @author Seth Kelley
 * @author Aastha Gandhi
 */
public class Profile {

    // Instance variables refer to a Person
    private String name;      // name
    private String sequence1; // DNA sequence from one parent       (first sequence READ from profile.txt)
    private String sequence2; // DNA sequence from the other parent (second sequence READ from profile.txt)
    private STR[]  S1_STRs;   // array of STRs found in sequence1
    private STR[]  S2_STRs;   // array of STRs found in sequence2

    /*
     * Default constructor initializes instance variable to default values.
     */ 
    public Profile () {
        this.name = "None";
        this.S2_STRs = new STR[0];
        this.S1_STRs = new STR[0];
        this.sequence1 = "";
        this.sequence2 = "";
    }

    /*
     * Constructor initializes instance variables to parameter values.
     * 
     * @param name
     * @param C1_STRs
     * @param C2_STRs
     * @param sequence1
     * @param sequence2
     */ 
    public Profile (String name, STR[] S1_STRs, STR[] S2_STRs, String sequence1, String sequence2) {
        this.name = name;
        this.S1_STRs = S1_STRs;
        this.S2_STRs = S2_STRs;
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;

    }

    /*
     * Returns the string representation of the Profile object.
     * 
     * @return the string representation of the Profile object
     */ 
    public String toString() {
        String str = "\n____________________\n";
        str += "\nName: " + name + "\n";
        str += "\nSequence 1 STRs: " + Arrays.toString(S1_STRs).replace("[", "").replace("]", "").replace(",", "")
                + "\n";
        str += "\nSequence 2 STRs: " + Arrays.toString(S2_STRs).replace("[", "").replace("]", "").replace(",", "")
                + "\n";
        str += "\nChromosome 1 sequence: " + sequence1 + "\n";
        str += "Chromosome 2 sequence: " + sequence2;
        str += "\n____________________\n";

        return str;
    }

    /*
     * @return person's name
     */ 
    public String getName() {
        return name;
    }

    /*
     * @return person's first STR array
     */ 
    public STR[] getS1_STRs() {
        return S1_STRs;
    }

    /*
     * @return person's second STR array
     */ 
    public STR[] getS2_STRs() {
        return S2_STRs;
    }

    /*
     * @return person's fthe full DNA sequence
     */ 
    
    public String getSequence1() {
        return sequence1;
    }

    
    public String getSequence2() {
        return sequence2;
    }

    /*
     * Sets the first STR array of the person's profile object
     * 
     * @param S1_STRs 
     */ 
    public void setS1_STRs (STR[] S1_STRs) {
        this.S1_STRs = S1_STRs;
    }

    /*
     * Sets the second STR array of the person's profile object
     * 
     * @param S2_STRs 
     */
    public void setS2_STRs(STR[] S2_STRs) {
        this.S2_STRs = S2_STRs;
    }

    /*
     * Returns true if this object equals other, false otherwise
     * 
     * @param other the object to be compared with.
     * @return true if @other is the same as @this, false otherwise.
     */ 
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Profile)) {
            return false;
        }
        Profile o = (Profile) other;
        return name.equals(o.name) &&
                Arrays.equals(S1_STRs, o.getS1_STRs()) &&
                Arrays.equals(S2_STRs, o.getS2_STRs()) &&
                sequence1.equals(o.getSequence1()) &&
                sequence2.equals(o.getSequence2());
    }

}
