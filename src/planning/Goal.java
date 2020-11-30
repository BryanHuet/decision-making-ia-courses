package planning;

import java.util.Map;

import representation.Variable;


public interface Goal {

    boolean isSatisfiedBy(Map<Variable, Object> state);

    Map<Variable, Object> getGoal();


}
