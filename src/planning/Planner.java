package planning;

import java.util.*;
import representation.Variable;

public interface Planner{

  List<Action> plan();
  Map<Variable,Object> getInitialState();
  Set<Action> getActions();
  Goal getGoal();


}
