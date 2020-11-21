package representation;
import java.util.*;


public class DifferenceConstraint implements Constraint{

  private Variable v1;
  private Variable v2;
  private Set<Variable> scope = new HashSet<>();

  public DifferenceConstraint(Variable v1, Variable v2){
      this.v1 =v1;
      this.v2 =v2;
      scope.add(this.v1);
      scope.add(this.v2);
  }

    public Set<Variable> getScope(){
      return scope;
    }

    public String toString(){
      return "DifferenceConstraint";
    }
  public boolean isSatisfiedBy(Map<Variable, Object> instance){
    if (instance.containsKey(v1) && instance.containsKey(v2))
    {

      return instance.get(v1) != instance.get(v2);

    }
    throw new IllegalArgumentException();
  }

}
