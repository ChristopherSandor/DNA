
/**
 * 
 * This class represents the Short Tandem Repeats. An STR is a short 
 * sequence of DNA bases that repeates back-to-back numerous times.
 * 
 * Used to create an STR object which will be used to differentiate the type
 * of STR being read and how many repeats there are in the DNA sequence.
 * 
 * DO NOT EDIT
 * 
 * @author Seth Kelley
 * @author Aastha Gandhi
 */
public class STR {

    // Instance variables
    private String STR;       // String value of the STR
    private int numOfRepeats; // Number of times the STR is repeated

    /**
     * Constructor for STR: initializes the instance variables
     * 
     * @param STR The STR name of the STR Object
     * @param numOfRepeats How many repeats this STR has
     */
    public STR(String STR, int numOfRepeats) {
        this.STR = STR;
        this.numOfRepeats = numOfRepeats;
    }

    /**
     * Gets the String name of the STR
     * 
     * @return The String name of this Object
     */
    public String getSTR() {
        return STR;
    }

    /**
     * Gets the length as an int of how many times this STR is repeated
     * 
     * @return The number of times the STR is repeated
     */
    public int getRepeats() {
        return numOfRepeats;
    }

    /**
     * Sets the length of the STR
     * 
     * @param numOfRepeats The number of repeats to set the STR to
     */
    public void setRepeats(int numOfRepeats) {
        this.numOfRepeats = numOfRepeats;
    }

    /**
     * String representation for the STR Object
     * 
     * @return a string that has the name of the STR, and the number of repeats
     */
    public String toString() {
        return "\nSTR Type: " + STR + "\tAmount of Repeats: " + numOfRepeats;
    }

    /**
     * Equals method for the STR Object
     * 
     * @return true if this object holds the same information as @other, false otherwise
     */
    public boolean equals(Object other) {

        if (other == null || !(other instanceof STR)) {
            return false;
        }
        
        STR o = (STR) other;
        return this.STR.equals(o.STR) && this.numOfRepeats == o.numOfRepeats;
    }
}
