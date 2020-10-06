package planning;
import java.util.*;
import representation.*;

public class Compare2State implements Comparator<Map<Variable,Object>>{

  Map<Map<Variable,Object>,Integer> _dist;

  public Compare2State(Map<Map<Variable,Object>,Integer> dist){
    _dist=dist;
  }

  @Override
  public int compare(Map<Variable,Object> act1, Map<Variable,Object> act2){
    if (_dist.get(act1)>_dist.get(act2)){
      return 1;
    }else{
      if (_dist.get(act1)<_dist.get(act2)){
        return -1;
      }
    }
    return 0;
  }

}
