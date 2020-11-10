package representation;
import java.util.*;

public class Rule implements Constraint{

  private BooleanVariable _boolVar1;
  private Boolean _bool1;
  private BooleanVariable _boolVar2;
  private Boolean _bool2;
  private Set<Variable> _scope = new HashSet<>();

  public Rule(BooleanVariable boolVar1, boolean bool1,BooleanVariable boolVar2, boolean bool2){
    _boolVar1=boolVar1;
    _bool1=bool1;
    _boolVar2=boolVar2;
    _bool2=bool2;
    _scope.add(_boolVar1);
    _scope.add(_boolVar2);
  }

  public Set<Variable> getScope(){
    return _scope;
  }

  public boolean isSatisfiedBy(Map<Object, Object> instance){

    if (instance.containsKey(_boolVar1) && instance.containsKey(_boolVar2))
    {
      if (_bool1 && _bool2){
        if((boolean) instance.get(_boolVar1) && ! (boolean) instance.get(_boolVar2)){
          return false;
        }
      }
      if (_bool1 && ! _bool2){
        if ( (boolean) instance.get(_boolVar1) &&  (boolean) instance.get(_boolVar2)){
          return false;
        }
      }
      if (! _bool1 && _bool2){
        if (! (boolean) instance.get(_boolVar1) && ! (boolean) instance.get(_boolVar2)){
          return false;
        }
      }
      if (! _bool1 && ! _bool2){
        if (! (boolean) instance.get(_boolVar1) && (boolean) instance.get(_boolVar2)){
          return false;
        }
      }
      return true;
    }
    throw new IllegalArgumentException();
  }


}
