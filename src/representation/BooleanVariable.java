package representation;
import java.util.*;
public class BooleanVariable extends Variable{
  private String _name;
  private static Set<Object> _domaine = new HashSet<>();
  static {
    _domaine.add(true);
    _domaine.add(false);
  }

  public BooleanVariable(String name){
    super(name,_domaine);
  }

  public Set<Object>getDomain(){return _domaine;}


}
