package solvers;
import representation.Variable;
import representation.Constraint;
import java.util.*;

public class BacktrackSolver extends AbstractSolver{

    private Set<Variable> _affectations;
    private Set<Constraint> _constraints;

    public BacktrackSolver(Set<Variable> affectations, Set<Constraint> constraints){
      super(affectations,constraints);
      _affectations=affectations;
      _constraints=constraints;
    }

    public boolean SRA(Map<Variable,Object> affectPartielle, LinkedList<Variable> queue){
      Variable curseur;
      if (queue.isEmpty()){
        return false;
      }
      curseur=queue.poll();

      if (! affectPartielle.containsKey(curseur)){
        System.out.println("\nDOMAINE: "+curseur.get_domaine()+"\n");

        for(Object values: curseur.get_domaine()){
          affectPartielle.put(curseur,values);
          if (isConsistent(affectPartielle)){
            if (_affectations.size()==affectPartielle.size()){

              return true;
            }
            if (SRA(affectPartielle,queue)){
              return true;
            }
          }
        }
      }
    return false;
    }

    public Map<Variable,Object> solve(){
      Map<Variable,Object> solution = new HashMap<>();
      LinkedList<Variable> queue = new LinkedList<>();
      if (get_affectations() != null){
        queue.addAll(get_affectations());
        SRA(solution,queue);
      }
      return solution;

    }

}
