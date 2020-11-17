package representation;
import java.util.*;

public class BooleanVariable extends Variable{
  private static Set<Object> domaine = new HashSet<>();
  static {
    domaine.add(true);
    domaine.add(false);
  }

  public BooleanVariable(String name){
    super(name, domaine);
  }

  public Set<Object>getDomain(){return domaine;}


}
