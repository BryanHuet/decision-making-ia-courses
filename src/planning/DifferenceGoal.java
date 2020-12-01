package planning;

import representation.Variable;

import java.util.Map;

public class DifferenceGoal implements Goal {


    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> state) {
        for (Map.Entry<Variable, Object> entry : state.entrySet()) {
            for (Map.Entry<Variable, Object> entry2 : state.entrySet()) {
                if (entry2.getKey() != entry.getKey()) {
                    if (entry2.getValue() == null || entry2.getValue() == entry.getValue()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Map<Variable, Object> getGoal() {
        return null;
    }
}
