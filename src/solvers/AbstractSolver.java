package solvers;
import representation.Variable;
import representation.Constraint;
import java.util.*;

public abstract class AbstractSolver implements Solver{

  private Set<Variable> _affectations;
  private Set<Constraint> _constraints;

  public AbstractSolver(Set<Variable> affectations, Set<Constraint> constraints){
      _affectations=affectations;
      _constraints=constraints;
  }
  public Set<Constraint> get_constraints(){
    return _constraints;
  }
  public Set<Variable> get_affectations(){
    return _affectations;
  }

  public void setAffectations(Set<Variable> affectations){
    _affectations=affectations;
  }
  public boolean isConsistent(Map<Variable, Object> varPartielles){

    for (Constraint c : _constraints){
      for(Variable v : c.getScope()){
        if(! varPartielles.containsKey(v)){
          return true;
        }
      }
        if (! c.isSatisfiedBy(varPartielles)){
          return false;
        }
    }

    return true;
  }



}
