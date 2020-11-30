package representation;

import java.util.*;

public class BinaryExtensionConstraint implements Constraint {

    private Variable v1;
    private Variable v2;
    private Set<Variable> scope = new HashSet<>();
    private Set<BinaryTuple> extend = new HashSet<>();

    public BinaryExtensionConstraint(Variable v1, Variable v2) {
        this.v1 = v1;
        this.v2 = v2;
        scope.add(this.v1);
        scope.add(this.v2);
    }

    public void addTuple(Object obj1, Object obj2) {
        extend.add(new BinaryTuple(obj1, obj2));
    }

    public Set<Variable> getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return " BinaryExtensionConstraint{ " +
                "v1= " + v1 +
                ", v2= " + v2 +
                ", scope= " + scope +
                ", extend= " + extend +
                " } ";
    }

    public boolean isSatisfiedBy(Map<Variable, Object> instance) {
        if (instance.containsKey(v1) && instance.containsKey(v2)) {
            for (BinaryTuple tuple : this.extend) {
                if (tuple.equals(new BinaryTuple(instance.get(v1), instance.get(v2)))) {
                    return true;
                }
            }
            return false;
        }
        throw new IllegalArgumentException();
    }
}
