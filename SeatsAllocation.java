package Sam;

import java.util.ArrayList;

/**
 * @author Alex Yang
 * Driver class displays number of seats assigned to each party in 2015 Alberta provincial election,
 * if Alberta used party list PR at the time.
 *
 * Alberta would probably be devided into multiple constituencies with magnitude between 15 to 30.
 * For sake of simplisity, I assume the whole province was just one 87-member-constituency.
 *
 * display results under largest remainder method(Hare quota) and highest quotient method(Sainte Lague)
 */
public class SeatsAllocation {
    public static ArrayList<Party> parties = new ArrayList<>();
    public static final int ALL_SEATS = 87;
    public static int seatsAvailable = ALL_SEATS;
    public static int allVotes = 0;

    public static void main(String[] args){
        parties.add(new Party("Social Credit", 832));
        parties.add(new Party("NDP", 603459));
        parties.add(new Party("Wild Rose", 360124));
        parties.add(new Party("PC", 412958));
        parties.add(new Party("Green", 7321));
        parties.add(new Party("Liberal", 62171));
        parties.add(new Party("Alberta", 33867));

        for(int i=0; i<parties.size(); i++){
            allVotes += parties.get(i).getVotes();
        }

        System.out.println("All votes: "+allVotes);
        LargestRemainder();
        SainteLague();

    }

    public static void LargestRemainder(){
        seatsReset();
        int HareQuota = allVotes/ALL_SEATS;
        for(int i=0; i<parties.size(); i++){
            parties.get(i).addSeats(parties.get(i).getVotes() / HareQuota);
            seatsAvailable -= parties.get(i).getSeats();
            parties.get(i).setRemainder(parties.get(i).getVotes() % HareQuota);
        }

        for(int i=0; i<parties.size(); i++){
            for(int j=0; j<parties.size()-1; j++){
                if(parties.get(j).getRemainder()<parties.get(j+1).getRemainder()){
                    Party temp = parties.get(j+1);
                    parties.set(j+1, parties.get(j));
                    parties.set(j, temp);
                }
            }
        }

        for(int i=0; i<parties.size() && seatsAvailable>0; i++){
            parties.get(i).addSeats(1);
            seatsAvailable--;
        }

        System.out.println("Largest Remainder method(Hare quota):\n");
        displayResults();


    }

    public static void SainteLague(){
        seatsReset();
        while(seatsAvailable>0){
            for(int i=0; i<parties.size(); i++){
                parties.get(i).setQuotient();
            }
            int highestIndex = 0;
            for(int i=0; i<parties.size()-1; i++){
                if(parties.get(i+1).getQuotient()>parties.get(highestIndex).getQuotient()){
                    highestIndex = i+1;
                }
            }
            parties.get(highestIndex).addSeats(1);
            seatsAvailable--;
        }

        System.out.println("\nHighest quotient method(Sainte Lague):\n");
        displayResults();
    }


    public static void seatsReset(){
        for(int i=0; i<parties.size(); i++){
            parties.get(i).seatsReset();
        }
        seatsAvailable = ALL_SEATS;
    }

    public static void displayResults(){
        for(int i=0; i<parties.size(); i++){
            for(int j=0; j<parties.size()-1; j++){
                if(parties.get(j).getVotes()<parties.get(j+1).getVotes()){
                    Party temp = parties.get(j+1);
                    parties.set(j+1, parties.get(j));
                    parties.set(j, temp);
                }
            }
        }

        for(int i=0; i<parties.size(); i++){
            parties.get(i).votePercentage = (double) parties.get(i).getVotes()/allVotes*100;
            parties.get(i).displayResults();
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){}
        }
    }


}
