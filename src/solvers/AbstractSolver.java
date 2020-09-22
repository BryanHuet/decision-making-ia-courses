package solvers;
import representation.Variable;
import representation.Constraint;
import java.util.*;

public abstract class AbstractSolver implements Solver{

  Set<Variable> _affectations;
  Set<Constraint> _constraints;

  public AbstractSolver(Set<Variable> affectations, Set<Constraint> constraints){
      _affectations=affectations;
      System.out.println(affectations);
      _constraints=constraints;
  }

  public Set<Variable> get_affectations(){
    return _affectations;
  }
  public boolean isConsistent(Map<Variable, Object> varPartielles){
    for (Constraint c : _constraints){
      if (c.getScope()==_affectations){
        if (! c.isSatisfiedBy(varPartielles)){
          return false;

        }
      }
    }
    return true;
  }



}
