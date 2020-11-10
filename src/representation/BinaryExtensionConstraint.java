package representation;
import java.util.*;

public class BinaryExtensionConstraint implements Constraint {

  private Variable _v1;
  private Variable _v2;
  private Set<Variable> _scope = new HashSet<>();
  private Set<BinaryTuple> _extend = new HashSet<>();

  public BinaryExtensionConstraint(Variable v1, Variable v2) {
    _v1 = v1;
    _v2 = v2;
    _scope.add(_v1);
    _scope.add(_v2);
  }

  public void addTuple(Object obj1, Object obj2) {
    _extend.add(new BinaryTuple(obj1, obj2));
  }

  public Set<Variable> getScope() {
    return _scope;
  }

  public boolean isSatisfiedBy(Map<Object, Object> instance) {
    if(instance.containsKey(_v1) && instance.containsKey(_v2)){
      return _extend.contains(new BinaryTuple(instance.get(_v1), instance.get(_v2)));
    }
    throw new IllegalArgumentException();
  }
}
