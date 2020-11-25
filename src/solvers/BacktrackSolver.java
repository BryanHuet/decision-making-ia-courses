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
       // if(var!=null && ! affectPartielle.containsKey(var)){
        if (var != null) {
            for(Object value : var.getDomain()) {
                affectPartielle.put(var, value);
                if (isConsistent(affectPartielle)) {
                    if (this.getAffectations().size() == affectPartielle.size()) {
                        System.out.println("LE RETURN "+affectPartielle);
                        return affectPartielle;
                    }else{
                        if(SRA(affectPartielle,queue)!=null){
                            System.out.println("pasé par ici");
                            return SRA(affectPartielle,queue);
                       }
                    }
            }   }
        }
        if(this.getAffectations().size()==affectPartielle.size() && isConsistent(affectPartielle)){
            return affectPartielle;
        }
        System.out.println("MAIS NULL QUAND MEME"+affectPartielle);
        return null;
    }

    public Map<Variable, Object> solve(){
        System.out.println("\nUN APPELE\n");
        Map<Variable, Object> solution = new HashMap<>();
        LinkedList<Variable> queue = new LinkedList<>(this.getAffectations());
        if((this.getAffectations().isEmpty() && this.getConstraints().isEmpty())){
            return solution;
        }
        solution=this.SRA(solution,queue);
        System.out.println(this.getAffectations()+" for "+solution);
        return solution;

    }

}
