package planning;

import java.util.*;

import representation.Variable;

public class BFSPlanner implements Planner {

    private final Map<Variable, Object> initialState;
    private final Set<Action> actions;
    private final Goal goal;

    public BFSPlanner(Map<Variable, Object> initalState, Set<Action> actions, Goal goal) {
        initialState = initalState;
        this.actions = actions;
        this.goal = goal;
    }

    public Map<Variable, Object> getInitialState() {
        return initialState;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public Goal getGoal() {
        return goal;
    }

    public String toString() {
        return "initial State: " + initialState + " actions: " + actions + " goal: " + goal;
    }

    public List<Action> getBFSPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father, Map<Map<Variable, Object>, Action> plan, Map<Variable, Object> goal) {
        List<Action> BFSplan = new LinkedList<>();
        while (goal != null) {
            if (plan.containsKey(goal)) {
                BFSplan.add(plan.get(goal));
            }
            goal = father.getOrDefault(goal, null);
        }
        Collections.reverse(BFSplan);
        return BFSplan;
    }

    public List<Action> plan() {
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        LinkedList<Map<Variable, Object>> open = new LinkedList<>();
        Set<Map<Variable, Object>> closed = new HashSet<>();

        open.add(this.getInitialState());
        father.put(this.getInitialState(), null);
        if (goal.isSatisfiedBy(initialState)) {
            return new ArrayList<>();
        }
        while (!open.isEmpty()) {
            Map<Variable, Object> instance = open.pop();
            closed.add(instance);

            for (Action act : this.getActions()) {
                if (act.isApplicable(instance)) {
                    Map<Variable, Object> next = act.successor(instance);

                    if (!closed.contains(next) && !open.contains(next)) {
                        father.put(next, instance);
                        plan.put(next, act);
                        if (this.getGoal().isSatisfiedBy(next)) {
                            return this.getBFSPlan(father, plan, next);
                        } else {
                            open.add(next);
                        }
                    }
                }
            }
        }
        return null;
    }
}
