package planning;

import java.util.Map;
import representation.Variable;

public class BasicGoal implements Goal{

  private Map<Variable,Object> _goalState;

  public BasicGoal(Map<Variable,Object> goalState){
      _goalState=goalState;
  }

  public Map<Variable,Object> getGoal(){
    return _goalState;
  }


  public boolean isSatisfiedBy(Map<Variable,Object> state){
    if (_goalState.isEmpty()){
      return true;
    }
    if (state.size()<_goalState.size()){
      return false;
    }
    for (Map.Entry<Variable,Object> entry : _goalState.entrySet()) {
      return state.containsKey(entry.getKey()) && (state.get(entry.getKey())==entry.getValue());
    }
    return false;
  }


}
