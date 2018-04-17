package algorithms.search;

abstract class ASearchingAlgoritm implements ISearchingAlgorithm {
    ISearchable game;

    public ASearchingAlgoritm(ISearchable game) {
        this.game = game;
    }

    abstract public Solution solve();

    public ISearchable getGame() {
        return game;
    }

    public void setGame(ISearchable game) {
        this.game = game;
    }
}
