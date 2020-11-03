package datamining;

import representation.BooleanVariable;

import java.util.*;

public class Apriori extends AbstractItemsetMiner{

  public Apriori(BooleanDatabase database){
    super(database);
  }



  public Set<Itemset> frequentSingletons(float frequence){

    Set<Itemset> frequent = new HashSet<>();
    for (BooleanVariable var : this.getDataBase().getItems()){
      HashSet<BooleanVariable> singleton = new HashSet<>();
      singleton.add(var);

      if(this.frequency(singleton)>=frequence){
        frequent.add(new Itemset(singleton,this.frequency(singleton)));
      }

    }
    return frequent;
  }

  public static SortedSet<BooleanVariable> combine (SortedSet<BooleanVariable> s1 ,SortedSet<BooleanVariable> s2){

    SortedSet<BooleanVariable> s3 = new TreeSet<>(COMPARATOR);

    if(s1.size()==s2.size()) {
      if (!s1.isEmpty() && !s2.isEmpty()) {
        if (s1.last() != s2.last()) {
          return null;
        }
      }
    }
    return null;
  }

  public String toString(){
    return "| Apriori, avec Database : "+this.getDataBase();
  }

  @Override
  public Set<Itemset> extract(float frequence) {
    return null;
  }
}
