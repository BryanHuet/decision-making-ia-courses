package solvers;
import representation.Variable;
import representation.Constraint;
import java.util.*;

public class BacktrackSolver extends AbstractSolver{

    public BacktrackSolver(Set<Variable> affectations, Set<Constraint> constraints){
      super(affectations,constraints);
    }

    public Map<Variable, Object> SRA(Map<Variable, Object> affectPartielle, LinkedList<Variable> queue){

        Variable var = queue.poll();

        while(affectPartielle.containsKey(var)){
            var=queue.poll();
        }

        if (var != null) {
            for(Object value : var.getDomain()) {
                affectPartielle.put(var, value);

                if (isConsistent(affectPartielle)) {
                    if (this.getAffectations().size() == affectPartielle.size()) {
                        return affectPartielle;
                    }else{
                        if(SRA(affectPartielle,queue)!=null){
                            return affectPartielle;
                       }
                    }
                }
                affectPartielle.remove(var);
                if(!queue.contains(var)){
                    queue.add(var);
                }
            }
        }

        return null;
    }

    public Map<Variable, Object> solve(){
        Map<Variable, Object> solution = new HashMap<>();
        LinkedList<Variable> queue = new LinkedList<>(this.getAffectations());
        if((this.getAffectations().isEmpty() && this.getConstraints().isEmpty())){
            return solution;
        }
        solution=this.SRA(solution,queue);
        return solution;

    }

}
