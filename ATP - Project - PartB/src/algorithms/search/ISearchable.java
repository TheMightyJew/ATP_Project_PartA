package algorithms.search;

import javax.swing.plaf.nimbus.State;
import java.util.List;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    List<AState> getAllSuccessors(AState stati);
}
