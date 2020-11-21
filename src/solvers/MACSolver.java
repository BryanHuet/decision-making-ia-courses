package solvers;
import java.util.*;
import representation.*;


public class MACSolver extends AbstractSolver{

  private Set<Variable> _variables;
  private Set<Constraint> _constraints;

  public MACSolver(Set<Variable> variables, Set<Constraint> constraints){
    super(variables,constraints);
    _variables=variables;
    _constraints=constraints;
  }

  public Map<Variable, Object> solve(){
    Map<Variable, Object> instanceComp = new HashMap<>();
    Map<Variable,Set<Object>> instancePart = new HashMap<>();
    if (! _variables.isEmpty() && ! _constraints.isEmpty()){
      for(Variable v: _variables){
        if (! v.getDomain().isEmpty()){
        instancePart.put(v,v.getDomain());
        (new ArcConsistency(_constraints)).enforceArcConsistency(instancePart);
        }
      }
      for(Variable v: _variables){
        if (! v.getDomain().isEmpty()){
          instanceComp.put(v,v.getDomain());
        }
      }
    }
    return instanceComp;

  }
}
