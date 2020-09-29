package planning;

import representation.Variable;
import java.util.Map;
import java.util.HashMap;
public class BasicAction implements Action{

  private Map<Variable,Object> _precondition;
  private Map<Variable,Object> _effet;
  private int _cout;

  public BasicAction(Map<Variable,Object> precondition, Map<Variable,Object> effet,
                     int cout){
                    _precondition=precondition;
                    _effet=effet;
                    _cout=cout;
                     }


  public int getCost(){
    return _cout;
  }


  public String toString(){
    return ""+_precondition;
  }
  public boolean isApplicable(Map<Variable,Object> state){
    if (_precondition.isEmpty()){
      return true;
    }
    if (state.size()<_precondition.size()){
      return false;
    }
    for (Map.Entry<Variable,Object> entry : _precondition.entrySet()) {
      return state.containsKey(entry.getKey()) && (state.get(entry.getKey())==entry.getValue());
    }
    return false;
  }

  public Map<Variable,Object> successor(Map<Variable,Object> state){
    Map<Variable,Object> state2 = new HashMap<>();
    state2.putAll(state);
    for (Map.Entry<Variable,Object> entry : _effet.entrySet()) {
      state2.put(entry.getKey(),entry.getValue());
    }
    return state2;

  }


}
