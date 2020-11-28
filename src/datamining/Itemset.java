package datamining;

import java.util.Set;
import representation.BooleanVariable;
public class Itemset{

  private Set<BooleanVariable> items;
  private float frequence;

  public Itemset(Set<BooleanVariable> items, float frequence){
    this.items =items;
    this.frequence =frequence;
  }

  public Set<BooleanVariable> getItems(){
    return this.items;
  }
  public float getFrequency(){
    return this.frequence;
  }


  public String toString(){
    return "ItemSet avec items : "+ this.items +" et frequence : "+ this.frequence;
  }

}
