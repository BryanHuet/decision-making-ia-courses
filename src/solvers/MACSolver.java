package solvers;
import java.util.*;
import representation.*;


public class MACSolver extends AbstractSolver{


  public MACSolver(Set<Variable> variables, Set<Constraint> constraints){
    super(variables,constraints);
  }




  public Map<Variable, Object> solve(){
    Map<Variable, Object> instanceComp = new HashMap<>();
    Map<Variable,Set<Object>> instancePart = new HashMap<>();
      for(Variable v: this.getAffectations()){
          instancePart.put(v,v.getDomain());
         (new ArcConsistency(this.getConstraints())).enforceArcConsistency(instancePart);
      }
    for(Variable v: this.getAffectations()){
      if (! v.getDomain().isEmpty()){
        instanceComp.put(v,v.getDomain());
      }
    }

    return instanceComp;

  }
}
