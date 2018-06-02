package algorithms.search;

abstract class ASearchingAlgoritm implements ISearchingAlgorithm {
    protected int nodesAvaluated;
    protected String name;
    public ASearchingAlgoritm() {
    }
    public String getName(){
        return name;
    }
    public int getNumberOfNodesEvaluated() {
        return nodesAvaluated;
    }
    abstract public Solution solve(ISearchable game);

}
