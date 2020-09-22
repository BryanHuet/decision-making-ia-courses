package representation;
import java.util.Set;

public class Variable{
  private String _name;
  private Set<Object> _domaine;

  public Variable(String name, Set<Object> domaine){
    _name=name;
    _domaine=domaine;
  }

  public String get_name(){return _name;}

  public Set<Object> get_domaine() {
    return _domaine;
  }
  @Override
  public boolean equals(Object other){
    if ((_name != null) && (other instanceof Variable)){
      Variable cast_other = (Variable) other;
      return cast_other.get_name().equals(_name);
    }
    return false;
  }

  @Override
  public int hashCode(){
    return this._name.hashCode();
  }

}
