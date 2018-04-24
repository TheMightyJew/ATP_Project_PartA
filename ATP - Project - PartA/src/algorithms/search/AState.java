package algorithms.search;

import java.util.Comparator;

abstract public class AState implements Comparable {
    public AState() {
    }
    abstract public boolean isGoalState(ISearchable game);

    @Override
    abstract  public String toString();
    @Override
    abstract public boolean equals(Object obj);
}
