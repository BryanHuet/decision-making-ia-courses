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

    public List<Action> getBFSPlan(Map<Map<Variable,Object>,Map<Variable,Object>> father,Map<Map<Variable,Object>,Action> plan, Map<Variable,Object> goal){
      List<Action> BFSplan=new LinkedList<>();
      while(goal!=null){
        BFSplan.add(plan.get(goal));
        if(father.containsKey(goal)){
          goal=father.get(goal);
        }else{
          goal=null;
        }
      }
      Collections.reverse(BFSplan);
      return BFSplan;
    }

    public List<Action> plan(){
      Map<Map<Variable,Object>,Map<Variable,Object>> father=new HashMap<>();
      Map<Map<Variable,Object>,Action> plan= new HashMap<>();
      LinkedList<Map<Variable,Object>> open = new LinkedList<>();
      Set<Map<Variable,Object>> closed = new HashSet<>();
      open.add(this.getInitialState());
      father.put(this.getInitialState(),null);
      while(! open.isEmpty()){
        Map<Variable,Object> instance = open.pop();
        closed.add(instance);
        for(Action act: this.getActions()){
          Map<Variable,Object> next = act.successor(instance);
          if(! closed.contains(next) && ! open.contains(next)){
            father.put(next,instance);
            plan.put(next,act);
            if(this.getGoal().isSatisfiedBy(next)){
              return this.getBFSPlan(father,plan,this.getGoal().getGoal());
            }else{
              open.add(next);
            }
          }
        }
      }
      return null;
    }
}
