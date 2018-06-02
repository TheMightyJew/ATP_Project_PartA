package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgoritm {
    private Stack<AState> tor;
    private Map<String,AState> visited;
    public DepthFirstSearch() {
        name="DepthFirstSearch";
        nodesAvaluated=0;
        tor=new Stack<>();
        visited=new LinkedHashMap<>();
    }
    @Override
    public Solution solve(ISearchable game) {
        ArrayList<AState> ans=new ArrayList<>();
        AState current=game.getStartState();
        tor.add(game.getStartState());
        visited.put(game.getStartState().toString(),null);
        List<AState> temp=new ArrayList<>();
        while(!(current.equals(game.getGoalState()))){
            current=tor.pop();
            temp=game.getAllSuccessors(current);
            for (AState stati: temp) {
                if (!visited.containsKey(stati.toString())) {
                    tor.push(stati);
                    visited.put(stati.toString(), current);
                }
            }
        }
        while(current!=null){
            ans.add(current);
            current=visited.get(current.toString());
        }
        nodesAvaluated=visited.size();
        return new Solution(ans);    }

}
