package planning;

import representation.Variable;

import java.util.Map;
import java.util.HashMap;

public class BasicAction implements Action {

    private final Map<Variable, Object> precondition;
    private final Map<Variable, Object> effet;
    private final int cout;

    public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effet,
                       int cout) {
        this.precondition = precondition;
        this.effet = effet;
        this.cout = cout;
    }


    public int getCost() {
        return cout;
    }


    public String toString() {
        return "PRE : " + precondition + " EFF : " + effet;
    }

    public boolean isApplicable(Map<Variable, Object> state) {
        if (precondition.isEmpty()) {
            return true;
        }
        if (state.size() < precondition.size()) {
            return false;
        }
        boolean ok = true;
        for (Map.Entry<Variable, Object> entry : precondition.entrySet()) {
            ok = ok && state.containsKey(entry.getKey()) && (state.get(entry.getKey()) == entry.getValue());
        }
        return ok;
    }

    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        Map<Variable, Object> state2 = new HashMap<>(state);
        for (Map.Entry<Variable, Object> entry : effet.entrySet()) {
            state2.put(entry.getKey(), entry.getValue());
        }
        return state2;

    }


}
