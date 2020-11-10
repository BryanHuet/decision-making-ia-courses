package datamining;

import representation.BooleanVariable;

import java.util.*;

public class Apriori extends AbstractItemsetMiner implements ItemsetMiner{

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

  public static SortedSet<BooleanVariable> combine (SortedSet<BooleanVariable> ensembleItem1 ,SortedSet<BooleanVariable> ensembleItem2){

    SortedSet<BooleanVariable> ensembleItem3 = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);

    if(ensembleItem1.size()==ensembleItem2.size()) {
      if (!ensembleItem1.isEmpty() && !ensembleItem2.isEmpty()) {
        if (ensembleItem1.last() == ensembleItem2.last()) {
          return null;
        }
        if(ensembleItem1.headSet(ensembleItem1.last()).equals(ensembleItem2.headSet(ensembleItem2.last()))){
          ensembleItem3.addAll(ensembleItem1);
          ensembleItem3.addAll(ensembleItem2);
          return ensembleItem3;
        }
      }
    }
    return null;
  }
  public static  boolean allSubsetsFrequent(Set<BooleanVariable> ensembleItem, Collection<SortedSet<BooleanVariable>> collectionItem){
    HashSet<BooleanVariable> ensemble = new HashSet<>();
    for(BooleanVariable var : ensembleItem){
      for(BooleanVariable var2 : ensembleItem){
        if(var!=var2){
          ensemble.add(var2);
        }
        if(! collectionItem.contains(ensemble)){
          return false;
        }
      }
      ensemble.clear();
    }
    return true;
  }



  public String toString(){
    return "| Apriori, avec Database : "+this.getDataBase();
  }

  @Override
  public Set<Itemset> extract(float frequence) {

    Set<Itemset> ensembleItem = new HashSet<>();


    List<SortedSet<BooleanVariable>> listFrequent = new ArrayList<>();
    SortedSet<BooleanVariable> booleanVariables=new TreeSet<>(AbstractItemsetMiner.COMPARATOR);

    for(Itemset itemset:this.frequentSingletons(frequence)){
      listFrequent.add((SortedSet<BooleanVariable>) itemset.getItems());
      if (Apriori.allSubsetsFrequent(itemset.getItems(),listFrequent)){
        return null;

      }


    }

    return ensembleItem;
  }
}
