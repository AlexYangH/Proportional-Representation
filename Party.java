package Sam;

/**
 * @author Alex Yang
 * party objects to be instantiated annonymously in the arraylist in driver class
 */

import java.text.DecimalFormat;

public class Party {
    private String name;
    private int votes;
    private int seats;
    private int remainder;
    private double quotient;
    protected double votePercentage;

    private DecimalFormat percentFormat = new DecimalFormat("###.##");

    /**
     * constructor - Party
     * @param name name of party
     * @param votes votes received
     */
    public Party(String name, int votes){
        this.name = name;

        if(name.length()<15){
            for(int i=15-name.length(); i>0; i--){
                this.name += " ";
            }
        }

        this.votes = votes;
        seats=0;
    }

    public int getVotes(){
        return votes;
    }

    public int getSeats(){
        return seats;
    }

    public void addSeats(int seats){
        this.seats += seats;
    }

    public void seatsReset(){
        seats = 0;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public int getRemainder(){
        return remainder;
    }

    public double getQuotient() {
        return quotient;
    }

    public void setQuotient() {
        quotient = (double)votes / (2*seats+1);
    }

    public void displayResults(){
        System.out.println(name + "- votes: "+votes+" "+
                percentFormat.format(votePercentage)+"% seats: " + seats);
    }

}
