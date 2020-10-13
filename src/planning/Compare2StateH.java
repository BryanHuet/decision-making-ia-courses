package planning;
import java.util.*;
import representation.*;

public class Compare2StateH implements Comparator<Map<Variable,Object>>{

  private Map<Map<Variable,Object>,Integer> _dist;
  private Heuristic _heuristic;

  public Compare2StateH(Map<Map<Variable,Object>,Integer> dist,Heuristic heuristic){
    _dist=dist;
    _heuristic=heuristic;
  }

  @Override
  public int compare(Map<Variable,Object> act1, Map<Variable,Object> act2){
    if (_dist.get(act1)+_heuristic.estimate(act1)>_dist.get(act2)+_heuristic.estimate(act2)){
      return 1;
    }else{
      if (_dist.get(act1)+_heuristic.estimate(act1)<_dist.get(act2)+_heuristic.estimate(act2)){
        return -1;
      }
    }
    return 0;
  }

}
