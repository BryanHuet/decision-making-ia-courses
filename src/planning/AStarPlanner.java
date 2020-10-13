package planning;
import java.util.*;

public class AStarPlanner implements Planner{

    private Map<Variable,Object> _etatInit,
    private Set<Action> _actions;
    private Goal _goal;
    private Heuristic _heuristic;

    public AStarPlanner(Map<Variable,Object> etatInit, Set<Action> actions, Goal goal, Heuristic heuristic){
        _etatInit = etatInit;
        _actions=actions;
        _goal=goal;
        _heuristic=heuristic;
    }

    public List<Action> plan(){
        return null;
    }
    public Map<Variable,Object> getInitialState(){
        return _etatInit
    }
    public Set<Action> getActions(){
        return _actions;
    }
    public Goal getGoal(){
        return _goal;
    }

    public List<Action> Astar(){
        Map<Map<Variable,Object>,Action> plan = new HashMap<>();
        Map<Map<Variable,Object>,Map<Variable,object>> father=new HashMap<>();
        Map<Map<Variable,Object>,Float> distance;
        
    }


}