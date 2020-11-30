package planning;

import java.util.*;

import representation.*;

public class AStarPlanner implements Planner {

    private final Map<Variable, Object> etatInit;
    private final Set<Action> actions;
    private final Goal goal;
    private final Heuristic heuristic;

    public AStarPlanner(Map<Variable, Object> etatInit, Set<Action> actions, Goal goal, Heuristic heuristic) {
        this.etatInit = etatInit;
        this.actions = actions;
        this.goal = goal;
        this.heuristic = heuristic;
    }

    public List<Action> plan() {

        return this.Astar();
    }

    public Map<Variable, Object> getInitialState() {
        return etatInit;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public Goal getGoal() {
        return goal;
    }

    public List<Action> getBfsPlan(Map<Map<Variable, Object>, Action> plan,
                                   Map<Map<Variable, Object>, Map<Variable, Object>> father,
                                   Map<Variable, Object> instance) {

        List<Action> planToReturn = new ArrayList<>();
        while (instance != null) {
            if (plan.containsKey(instance)) {
                planToReturn.add(plan.get(instance));
            }
            instance = father.get(instance);
        }
        Collections.reverse(planToReturn);
        return planToReturn;

    }


    public List<Action> Astar() {

        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
        Map<Map<Variable, Object>, Float> distance = new HashMap<>();
        Map<Map<Variable, Object>, Float> value = new HashMap<>();
        PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>(new Compare2StateH(distance, value));


        open.add(etatInit);
        father.put(etatInit, null);
        distance.put(etatInit, (float) 0);
        value.put(etatInit, heuristic.estimate(etatInit));

        while (!open.isEmpty()) {
            Map<Variable, Object> instance = open.poll();
            if (goal.isSatisfiedBy(instance)) {
                return getBfsPlan(plan, father, instance);
            } else {
                for (Action act : actions) {
                    if (act.isApplicable(instance)) {
                        Map<Variable, Object> next = act.successor(instance);
                        if (!distance.containsKey(next)) {
                            distance.put(next, (float) 10000);

                        }
                        if (distance.get(next) > (distance.get(instance) + act.getCost())) {

                            distance.put(next, distance.get(instance) + act.getCost());
                            value.put(next, distance.get(next) + heuristic.estimate(next));
                            father.put(next, instance);
                            plan.put(next, act);
                            open.add(next);

                        }

                    }
                }
            }
        }


        return null;

    }


}