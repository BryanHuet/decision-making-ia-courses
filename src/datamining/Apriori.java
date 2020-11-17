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
        if (ensembleItem1.last().equals(ensembleItem2.last())) {
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
  public static boolean allSubsetsFrequent(Set<BooleanVariable> ensembleItem, Collection<SortedSet<BooleanVariable>> collectionItem){
    HashSet<BooleanVariable> ensemble = new HashSet<>(ensembleItem);
    for(BooleanVariable var : ensembleItem){
        ensemble.remove(var);
        if(! collectionItem.contains(ensemble)){
          return false;
        }
      ensemble.add(var);
    }
    return true;
  }



  public String toString(){
    return "| Apriori, avec Database : "+this.getDataBase();
  }

  @Override
  public Set<Itemset> extract(float frequence) {

    Set<Itemset> ensembleItem = new HashSet<>();
    //ensembleItem.add(new Itemset(new HashSet<>(),1.f)); //ajout ensemble vide n'est plus necessaire avec la nouvelle librairie


    List<SortedSet<BooleanVariable>> listFrequent = new ArrayList<>();

    for(Itemset itemset:this.frequentSingletons(frequence)) {
      ensembleItem.add(itemset);
      SortedSet<BooleanVariable> booleanVariables = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);

      booleanVariables.addAll(itemset.getItems());

      listFrequent.add(booleanVariables);

    }

    for(int i=2;i<=this.getDataBase().getItems().size();i++){
      List<SortedSet<BooleanVariable>> listFrequent2 = new ArrayList<>();
      for(int j=0; j<listFrequent.size();j++){
        for(int k=j+1;k<listFrequent.size();k++){
          SortedSet<BooleanVariable> beCombine = combine(listFrequent.get(j), listFrequent.get(k));
          if(beCombine!=null && allSubsetsFrequent(beCombine,listFrequent)){
            float freq = super.frequency(beCombine);
            if(freq>=frequence){
              ensembleItem.add(new Itemset(beCombine,freq));
              listFrequent2.add(beCombine);
            }
          }
        }
      }
      listFrequent=listFrequent2;
    }

    return ensembleItem;
  }
}
