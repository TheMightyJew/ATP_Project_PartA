package algorithms.search;

import java.util.List;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable game);
    String getName();
    int getNumberOfNodesEvaluated();
}
