package planning;

import java.util.*;

import representation.Variable;

public class DFSPlanner implements Planner {

    private final Map<Variable, Object> initialState;
    private final Set<Action> actions;
    private final Goal goal;

    public DFSPlanner(Map<Variable, Object> initalState, Set<Action> actions, Goal goal) {
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


    public List<Action> DFS(Map<Variable, Object> instance, LinkedList<Action> plan, HashSet<Map<Variable, Object>> closed) {

        if (this.getGoal().isSatisfiedBy(instance)) {
            return plan;
        } else {
            for (Action act : this.getActions()) {
                if (act.isApplicable(instance)) {
                    Map<Variable, Object> next = act.successor(instance);
                    if (!closed.contains(next)) {
                        plan.push(act);
                        closed.add(next);
                        List<Action> subplan = DFS(next, plan, closed);
                        if (subplan != null && !subplan.isEmpty()) {
                            return subplan;
                        } else {
                            if (!plan.isEmpty()) {
                                plan.pop();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    public List<Action> plan() {
        LinkedList<Action> plan = new LinkedList<>();
        HashSet<Map<Variable, Object>> closed = new HashSet<>();
        closed.add(this.getInitialState());
        return DFS(this.getInitialState(), plan, closed);
    }


}
