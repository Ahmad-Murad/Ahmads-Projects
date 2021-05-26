package Testing;

import org.json.*;

import java.util.ArrayList;

/**
 * A class used to contain all results for a given test.
 */
public class TestResults {

    public int numTags;
    public int numFailiures;

    public ArrayList<JSONObject> failedTags;
    public ArrayList<JSONObject> passedTags;

    public TestResults(){
        failedTags = new ArrayList<>();
        passedTags = new ArrayList<>();
    }

    public double getAccuracy(){
        if(numTags == 0) return 1.0; //100 percent if we haven't done anything
        return 1.0 - ((double)numFailiures / numTags);
    }

    public TestResults mergeResults(TestResults other){
        TestResults ret = new TestResults();

        ret.numTags = this.numTags + other.numTags;
        ret.numFailiures = this.numFailiures + other.numFailiures;

        ret.failedTags.addAll(other.failedTags);
        ret.failedTags.addAll(this.failedTags);
        ret.passedTags.addAll(other.passedTags);
        ret.passedTags.addAll(this.passedTags);

        return ret;
    }

    /**
     * Returns a sorted array of the missed tokens along with their number of occurences
     * @return
     */
    public ArrayList<Miss> GetMisses(){
        ArrayList<Miss> misses = new ArrayList<>();

        for(JSONObject failedTag : failedTags){
            /* If this is already contained, get a reference to it */
            Miss existing = null;
            for(Miss miss : misses){
                if(miss.token.equals(failedTag.getString("token"))){
                    existing = miss;
                    break;
                }
            }

            /* Update the number of misses */
            if(existing == null){
                existing = new Miss();
                existing.token = failedTag.getString("token");
            }
            existing.numMisses += 1;
        }


        /* Sort highest to lowest and return */
        misses.sort((o1, o2) -> o2.numMisses - o1.numMisses);
        return misses;
    }

    @Override
    public String toString(){
        double percent = getAccuracy() * 100.0;
        return "[RESULTS] Accuracy: " + String.format("%.2f%%",percent) + " Number of Missed Tags: " + numFailiures + " Total Tags: " + numTags;
    }

    public class Miss {

        public String token;
        public int numMisses;

        @Override
        public String toString(){
            return "[token: " + token + " misses: " + numMisses + "]";
        }
    }
}
