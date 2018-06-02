package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Solution implements Serializable {
    private ArrayList<AState> answer;

    public ArrayList<AState> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<AState> answer) {
        this.answer = answer;
    }

    public Solution(ArrayList<AState> answer) {
        this.answer = answer;
    }

    public ArrayList<AState> getSolutionPath() {
        return answer;
    }
}
