package representation;
import java.util.*;


public class DifferenceConstraint implements Constraint{

  private Variable _v1;
  private Variable _v2;
  private Set<Variable> _scope = new HashSet<>();

  public DifferenceConstraint(Variable v1, Variable v2){
      _v1=v1;
      _v2=v2;
      _scope.add(_v1);
      _scope.add(_v2);
  }

    public Set<Variable> getScope(){
      return _scope;
    }

    public String toString(){
      return "DifferenceConstraint";
    }
  public boolean isSatisfiedBy(Map<Variable,Object> instance){
    if (instance.containsKey(_v1) && instance.containsKey(_v2))
    {

      return instance.get(_v1) != instance.get(_v2) ? true : false;

    }
    throw new IllegalArgumentException();
  }

}
