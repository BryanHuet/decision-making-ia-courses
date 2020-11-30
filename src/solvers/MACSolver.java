package solvers;

import java.util.*;

import representation.*;


public class MACSolver extends AbstractSolver {


    public MACSolver(Set<Variable> variables, Set<Constraint> constraints) {
        super(variables, constraints);
    }


    public boolean MAC(Map<Variable, Set<Object>> instancePart) {
        Map<Variable, Object> instanceComp = new HashMap<>();

        if (!(new ArcConsistency(this.getConstraints())).enforceArcConsistency(instancePart)) {
            return false;
        }

        for (Map.Entry<Variable, Set<Object>> entry : instancePart.entrySet()) {
            instanceComp.put(entry.getKey(), entry.getValue());
        }

        if (!isConsistent(instanceComp)) {
            return false;
        }
        if (instanceComp.size() == this.getAffectations().size()) {
            return true;
        } else {
            for (Variable var : this.getAffectations()) {
                if (!instanceComp.containsKey(var)) {
                    HashSet<Object> setOb = new HashSet<>(var.getDomain());
                    instancePart.put(var, setOb);
                    return MAC(instancePart);
                }
            }
        }

        return false;
    }

    public Map<Variable, Object> solve() {
        Map<Variable, Object> instanceComp = new HashMap<>();
        Map<Variable, Set<Object>> instancePart = new HashMap<>();

        if (!MAC(instancePart)) {
            return instanceComp;
        }

        for (Map.Entry<Variable, Set<Object>> entry : instancePart.entrySet()) {
            instanceComp.put(entry.getKey(), entry.getValue());
        }
        return instanceComp;

    }
}
