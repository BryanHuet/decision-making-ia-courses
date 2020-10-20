package datamining;

import java.util.*;

public class Apriori extends AbstractItemsetMiner{

  public Apriori(BooleanDatabase database){
    super(database);
  }



  public Set<Itemset> frequentSingletons(float frequence){
    //ici on recup juste les singletons
    /*
    for(BooleanVariable var: this.getDataBase().getItems()){

    }
*/

    return null
  }

  public String toString(){
    return "| Apriori, avec Database : "+this.getDataBase();
  }

}
