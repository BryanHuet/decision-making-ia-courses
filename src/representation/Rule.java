package representation;

import java.util.*;

public class Rule implements Constraint {

    private BooleanVariable boolVar1;
    private Boolean bool1;
    private BooleanVariable boolVar2;
    private Boolean bool2;
    private Set<Variable> scope = new HashSet<>();

    public Rule(BooleanVariable boolVar1, boolean bool1, BooleanVariable boolVar2, boolean bool2) {
        this.boolVar1 = boolVar1;
        this.bool1 = bool1;
        this.boolVar2 = boolVar2;
        this.bool2 = bool2;
        scope.add(this.boolVar1);
        scope.add(this.boolVar2);
    }

    public Set<Variable> getScope() {
        return scope;
    }

    public boolean isSatisfiedBy(Map<Variable, Object> instance) {

        if (instance.containsKey(boolVar1) && instance.containsKey(boolVar2)) {
            if (bool1 && bool2) {
                if ((boolean) instance.get(boolVar1) && !(boolean) instance.get(boolVar2)) {
                    return false;
                }
            }
            if (bool1 && !bool2) {
                if ((boolean) instance.get(boolVar1) && (boolean) instance.get(boolVar2)) {
                    return false;
                }
            }
            if (!bool1 && bool2) {
                if (!(boolean) instance.get(boolVar1) && !(boolean) instance.get(boolVar2)) {
                    return false;
                }
            }
            if (!bool1 && !bool2) {
                if (!(boolean) instance.get(boolVar1) && (boolean) instance.get(boolVar2)) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalArgumentException();
    }


}
