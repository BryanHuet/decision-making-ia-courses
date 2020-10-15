package planning;
import java.util.*;
import representation.*;

public class DijkstraPlanner implements Planner{

  private Map<Variable,Object> _initialState;
  private Set<Action> _actions;
  private Goal _goal;

  public DijkstraPlanner(Map<Variable,Object>initalState, Set<Action> actions, Goal goal){
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
  public String toString(){
    return "initial State: "+_initialState+" actions: "+_actions+" goal: "+_goal;
  }
  public List<Action> getDijkstraPlan(Map<Map<Variable,Object>,Map<Variable,Object>> father, Map<Map<Variable,Object>,Action> plan,List<Map<Variable,Object>> goals,Map<Map<Variable,Object>,Integer> distance){
    List<Action> dijPlan=new LinkedList<>();
    Map<Variable,Object> goal=null;
    int tmp = 10000;
    for (Map<Variable,Object> state : goals){
      if (tmp>distance.get(state)){
        goal=state;
        tmp=distance.get(state);
      }
    }
    while(goal!=null){
      if(plan.containsKey(goal)){
      dijPlan.add(plan.get(goal));}
      goal=father.get(goal);
    }
    Collections.reverse(dijPlan);
    return dijPlan;
  }

  public List<Action> dijkstra(Map<Map<Variable,Object>,Action> plan, Map<Map<Variable,Object>,Integer> distance, Map<Map<Variable,Object>,Map<Variable,Object>> father){
    List<Map<Variable,Object>> goals= new ArrayList<>();
    PriorityQueue<Map<Variable,Object>> open = new PriorityQueue<>(new Compare2State(distance));
    father.put(_initialState,null);
    distance.put(_initialState,0);
    open.add(_initialState);

    while (! open.isEmpty()){
      Map<Variable,Object> instance = open.poll();
      if(_goal.isSatisfiedBy(instance)){
        goals.add(instance);
      }
      for (Action act: _actions){
        if (act.isApplicable(instance)){
          Map<Variable,Object> next = act.successor(instance);

          if (! distance.containsKey(next)){
            distance.put(next,10000);
          }
          if(distance.get(next) > distance.get(instance) + act.getCost()){
            distance.put(next,distance.get(instance) + act.getCost());
            father.put(next,instance);
            plan.put(next,act);
            open.add(next);

          }
        }
      }
    }
    return goals.isEmpty() ? null : this.getDijkstraPlan(father,plan,goals,distance);
  }

  public List<Action> plan(){
    return dijkstra(new HashMap<>(),new HashMap<>(),new HashMap<>());
  }




}
