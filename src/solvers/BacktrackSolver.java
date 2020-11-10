package solvers;
import representation.Variable;
import representation.Constraint;
import java.util.*;

public class BacktrackSolver extends AbstractSolver{

    public BacktrackSolver(Set<Variable> affectations, Set<Constraint> constraints){
      super(affectations,constraints);
    }

    public boolean SRA(Map<Object, Object> affectPartielle, HashSet<Variable> queue){

        for(Variable var : queue){
            if(! affectPartielle.containsKey(var)){
                for(Object value : var.getDomain()) {
                    affectPartielle.put(var, value);
                    if (isConsistent(affectPartielle)) {
                        if (getAffectations().size() == affectPartielle.size()) {
                            return true;
                        } else {
                            if(SRA(affectPartielle, queue)){
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public Map<Object, Object> solve(){
      Map<Object, Object> solution = new HashMap<>();
        if (getAffectations() != null){
            HashSet<Variable> queue = new HashSet<>(getAffectations());
            SRA(solution,queue);
      }
      return solution;

    }

}
