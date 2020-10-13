package planning;
import java.util.*;
import representation.*;
public class AStarPlanner implements Planner{

    private Map<Variable,Object> _etatInit;
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
        return _etatInit;
    }
    public Set<Action> getActions(){
        return _actions;
    }
    public Goal getGoal(){
        return _goal;
    }

    public List<Action> getBfsPlan(Map<Map<Variable,Object>,Action> plan,
                                     Map<Map<Variable,Object>,Map<Variable,Object>> father,
                                     Map<Variable,Object> instance){
        
        List<Action> planToReturn = new ArrayList<>();
        while(instance != null){
            planToReturn.add(plan.get(instance));
            instance=father.get(instance);
        }
        Collections.reverse(planToReturn);
        return planToReturn;

    }


    public List<Action> Astar(){
        Map<Map<Variable,Object>,Action> plan = new HashMap<>();
        Map<Map<Variable,Object>,Map<Variable,Object>> father=new HashMap<>();
        Map<Map<Variable,Object>,Float> distance = new HashMap<>();
        Map<Map<Variable,Object>,Float> value = new HashMap<>();
        PriorityQueue<Map<Variable,Object>> open = new PriorityQueue<>(new Compare2StateH(distance,value));
        
        open.add(_etatInit);
        father.put(_etatInit,null);
        distance.put(_etatInit,(float)0);
        value.put(_etatInit,_heuristic.estimate(_etatInit));

        while(! open.isEmpty()){
            Map<Variable,Object> instance = open.poll();
            if(_goal.isSatisfiedBy(instance)){
                return getBfsPlan(plan,father,instance);
            }else{
                for(Action act : _actions){
                    if (act.isApplicable(instance)){
                        Map<Variable,Object> next = act.successor(instance);
                        if (! distance.containsKey(next)){
                            distance.put(next,(float)10000);
                          }
                        if(distance.get(next)>distance.get(instance)+act.getCost()){
                            distance.put(next,distance.get(instance)+act.getCost());
                            value.put(next,distance.get(next)+_heuristic.estimate(next));
                            father.put(next,instance);
                            plan.put(next,act);
                        }
                    }
                }
            }
        }


        return null;

    }


}