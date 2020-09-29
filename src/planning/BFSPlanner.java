package planning;
import java.util.*;
import representation.Variable;

public class BFSPlanner implements Planner{

  private Map<Variable,Object> _initialState;
  private Set<Action> _actions;
  private Goal _goal;

  public BFSPlanner(Map<Variable,Object>initalState, Set<Action> actions, Goal goal){
    _initialState=initalState;
    _actions=actions;
    _goal=goal;
  }

    public Map<Variable,Object> getInitialState(){
      return _initialState;
    }

    public Set<Action> getActions(){
      return _actions;
    }

    public Goal getGoal(){
      return _goal;
    }

    public List<Action> plan(){
      Map<Map<Variable,Object>,Map<Variable,Object>> father=new HashMap<>();
      Map<Map<Variable,Object>,Action> plan= new HashMap<>();
      Set<Map<Variable,Object>> open = new HashSet<>();
      Set<Map<Variable,Object>> closed = new HashSet<>();
      open.add(this.getInitialState());
      father.put(this.getInitialState(),null);
    


      return null;
    }
}
