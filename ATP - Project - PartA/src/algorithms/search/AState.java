package algorithms.search;

import java.util.Comparator;

abstract public class AState implements Comparable {
    private Integer cost;
    public AState(int prio) {
        cost=prio;
    }
    abstract public boolean isGoalState(ISearchable game);

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    abstract  public String toString();
    @Override
    abstract public boolean equals(Object obj);
}
