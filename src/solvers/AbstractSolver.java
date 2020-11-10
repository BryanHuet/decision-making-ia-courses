package solvers;
import representation.Variable;
import representation.Constraint;
import java.util.*;

public abstract class AbstractSolver implements Solver{

  private Set<Variable> affectations;
  private Set<Constraint> constraints;

  public AbstractSolver(Set<Variable> affectations, Set<Constraint> constraints){
      this.affectations =affectations;
      this.constraints =constraints;
  }
  public Set<Constraint> getConstraints(){
    return this.constraints;
  }
  public Set<Variable> getAffectations(){
    return this.affectations;
  }

  public void setAffectations(Set<Variable> affectations){
    this.affectations =affectations;
  }
  public boolean isConsistent(Map<Object, Object> varPartielles){
    boolean ok=true;
    for (Variable v: affectations){
      ok = ok && (! v.getDomain().isEmpty());
    }
    if(! ok){
      return false;
    }
    for (Constraint c : constraints){
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
