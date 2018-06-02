package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgoritm {
    protected Queue<AState> tor;
    protected Map<String,AState> visited;
    public BreadthFirstSearch() {
        name="BreadthFirstSearch";
        nodesAvaluated=0;
        tor=new LinkedList<AState>();
        visited=new LinkedHashMap<>();
    }

    @Override
    public Solution solve(ISearchable game) {
        ArrayList<AState> ans=new ArrayList<>();
        AState current=game.getStartState();
        tor.add(game.getStartState());
        visited.put(game.getStartState().toString(),null);
        List<AState> temp=new ArrayList<>();
        while(current.equals(game.getGoalState())==false){
            current=tor.remove();
            temp=game.getAllSuccessors(current);
            for (AState stati: temp) {
                if (!visited.containsKey(stati.toString())) {
                    tor.add(stati);
                    visited.put(stati.toString(), current);
                }
            }
        }
        while(current!=null){
            ans.add(current);
            current=visited.get(current.toString());
        }
        nodesAvaluated=visited.size();
        return new Solution(ans);
    }
}
