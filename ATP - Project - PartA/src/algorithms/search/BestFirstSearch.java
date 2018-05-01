package algorithms.search;

import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {
    public BestFirstSearch() {
        name="BestFirstSearch";
        nodesAvaluated=0;
        visited=new LinkedHashMap<>();
        tor=new PriorityQueue<>((o1,o2) -> o1.compareTo(o2));
    }
}
