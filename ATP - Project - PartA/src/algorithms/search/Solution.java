package algorithms.search;

import java.util.List;

public class Solution {
    private List<AState> answer;

    public List<AState> getAnswer() {
        return answer;
    }

    public void setAnswer(List<AState> answer) {
        this.answer = answer;
    }

    public Solution(List<AState> answer) {
        this.answer = answer;
    }
}
