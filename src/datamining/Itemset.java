package datamining;

import java.util.Set;
import representation.BooleanVariable;
public class Itemset{

  private Set<BooleanVariable> _items;
  private float _frequence;

  public Itemset(Set<BooleanVariable> items, float frequence){
    _items=items;
    _frequence=frequence;
  }

  public Set<BooleanVariable> getItems(){
    return _items;
  }
  public float getFrequency(){
    return _frequence;
  }


  public String toString(){
    return "ItemSet avec items : "+_items+" et frequence : "+_frequence;
  }

}
