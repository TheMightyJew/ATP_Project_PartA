package algorithms.search;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        name="BestFirstSearch";
        nodesAvaluated=0;
        tor=new LinkedList<AState>();
        tor=new PriorityQueue<>();
    }
}
