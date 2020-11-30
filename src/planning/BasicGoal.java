package planning;

import java.util.Map;

import representation.Variable;

public class BasicGoal implements Goal {

    private final Map<Variable, Object> goalState;

    public BasicGoal(Map<Variable, Object> goalState) {
        this.goalState = goalState;
    }

    public Map<Variable, Object> getGoal() {
        return goalState;
    }

    public String toString() {
        return "" + goalState;
    }

    public boolean isSatisfiedBy(Map<Variable, Object> state) {
        if (goalState.isEmpty()) {
            return true;
        }
        if (state.size() < goalState.size()) {
            return false;
        }
        boolean ok = true;
        for (Map.Entry<Variable, Object> entry : goalState.entrySet()) {
            ok = ok && state.containsKey(entry.getKey()) && (state.get(entry.getKey()) == entry.getValue());
        }
        return ok;
    }


}
