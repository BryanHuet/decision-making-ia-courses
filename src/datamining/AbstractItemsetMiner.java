package datamining;

import java.util.*;
import representation.BooleanVariable;
public abstract class AbstractItemsetMiner implements ItemsetMiner{

    private BooleanDatabase _base;
    public static final Comparator<BooleanVariable> COMPARATOR = (var1, var2) -> var1.getName().compareTo(var2.getName());

    public AbstractItemsetMiner(BooleanDatabase base){
      _base=base;
    }

    @Override
    public BooleanDatabase getDataBase(){
      return _base;
    }

    @Override
    public Set<Itemset> extract(float frequence){
      return null;
    }

    public float frequency(Set<BooleanVariable> items){
      float occurence=0;

      if(items.isEmpty()){
        return (float)1.0;
      }
      for(Set<BooleanVariable> i : _base.getTransactions()){
        if(i.containsAll(items)){
          occurence=occurence+1;
        }
      }
      return occurence/_base.getTransactions().size();
    }


}
