package algorithms.search;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DepthFirstSearch extends ASearchingAlgoritm {
    protected Queue<AState> tor;
    protected Map<String,AState> visited;
    public DepthFirstSearch() {
        tor=new LinkedList<AState>();
        visited=new LinkedHashMap<>();
    }

    @Override
    public Solution solve(ISearchable game) {
        return null;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }
}
