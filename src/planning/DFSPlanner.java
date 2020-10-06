package planning;
import java.util.*;
import representation.Variable;

public class DFSPlanner implements Planner{

  private Map<Variable,Object> _initialState;
  private Set<Action> _actions;
  private Goal _goal;

  public DFSPlanner(Map<Variable,Object>initalState, Set<Action> actions, Goal goal){
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


    public List<Action> DFS(Map<Variable,Object> instance,LinkedList<Action> plan, Set<Map<Variable,Object>> closed){
      
        if (_goal.isSatisfiedBy(instance)){
          return plan;
        }
        for(Action act: this.getActions()){
          if(act.isApplicable(instance)){
            Map<Variable,Object> next = act.successor(instance);
            if(! closed.contains(next)){
              closed.add(next);
              plan.push(act);
              List<Action> subplan = DFS(next,plan,closed);
              if(! subplan.isEmpty()){
                return subplan;
              }else{
                plan.pop();
              }
            }
          }
        }
        return null;
    }


    public List<Action> plan(){
      return DFS(this.getInitialState(),new LinkedList<Action>(), new HashSet<Map<Variable,Object>>());
    }




}
