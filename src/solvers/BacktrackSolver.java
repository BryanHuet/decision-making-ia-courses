package solvers;
import representation.Variable;
import representation.Constraint;
import java.util.*;

public class BacktrackSolver extends AbstractSolver{

    public BacktrackSolver(Set<Variable> affectations, Set<Constraint> constraints){
      super(affectations,constraints);
    }

    public boolean SRA(Map<Variable, Object> affectPartielle, LinkedList<Variable> queue){

        if(this.getAffectations().isEmpty() && this.getConstraints().isEmpty()){
            return true;
        }
        Variable var = queue.poll();
        if(! affectPartielle.containsKey(var)&&var!=null){
            for(Object value : var.getDomain()) {
                affectPartielle.put(var, value);
                if (isConsistent(affectPartielle)) {
                    if (this.getAffectations().size() == affectPartielle.size()) {
                        return true;
                    } else {
                        if(SRA(affectPartielle, queue)){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public Map<Variable, Object> solve(){
        Map<Variable, Object> solution = new HashMap<>();
        LinkedList<Variable> queue = new LinkedList<>(getAffectations());
        boolean ok = SRA(solution,queue);
        if(solution.size()==this.getAffectations().size()){
            return solution;
        }
        return  ok ? solution : null;

    }

}
