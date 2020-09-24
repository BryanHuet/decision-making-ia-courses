package solvers;
import java.util.*;
import representation.*;

public class ArcConsistency{

  Set<Constraint> _constraints;


  public ArcConsistency(Set<Constraint>constraints){
    _constraints=constraints;
  }

  public static boolean filter(Variable var1, Set<Object> scopeVar1, Variable var2, Set<Object> scopeVar2, Constraint c){
    boolean changement=false;
    Set<Object>toDel=new HashSet<>();
    for(Object v1: scopeVar1){
      boolean viable=false;
      for (Object v2: scopeVar2){
        Map<Variable,Object> instance = new HashMap<>();
        instance.put(var1,v1);
        instance.put(var2,v2);
        if(c.isSatisfiedBy(instance)){
          viable=true;
          break;
        }
      }
      if (! viable){
        toDel.add(v1);
        changement=true;
      }
    }
    for (Object ob: toDel){
      scopeVar1.remove(ob);
    }
    return changement;
  }

  public static boolean enforce(Constraint c, Map<Variable,Set<Object>> domaine){
    Iterator value=c.getScope().iterator();
    Variable v1=(Variable) value.next();
    Variable v2=(Variable) value.next();
    boolean changement;
    changement = filter(v1,domaine.get(v1),v2,domaine.get(v2),c) ? true : false;
    changement = changement | filter(v2,domaine.get(v2),v1,domaine.get(v1),c) ? true : false;
    return changement;
  }

  public boolean enforceArcConsistency(Map<Variable,Set<Object>> domaines){
    boolean changement=false;
    for (Constraint c: _constraints){
      changement = changement | enforce(c,domaines);
    }

    return changement;
  }



}
