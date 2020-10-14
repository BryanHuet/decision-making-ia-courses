package planning;
import java.util.*;
import representation.*;

public class Compare2StateH implements Comparator<Map<Variable,Object>>{

  private Map<Map<Variable,Object>,Float> _dist;
  private Map<Map<Variable,Object>,Float> _value;

  public Compare2StateH(Map<Map<Variable,Object>,Float> dist,Map<Map<Variable,Object>,Float> value){
    _dist=dist;
    _value=value;
  }

  @Override
  public int compare(Map<Variable,Object> act1, Map<Variable,Object> act2){
    if ((_dist.get(act1)+_value.get(act1)) > (_dist.get(act2)+_value.get(act2))){
      return 1;
    }else{
      if ((_dist.get(act1)+_value.get(act1)) < (_dist.get(act2)+_value.get(act2))){
        return -1;
      }
    }
    return 0;
  }

}
