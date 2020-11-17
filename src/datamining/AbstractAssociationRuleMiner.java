package datamining;

import representation.BooleanVariable;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {

    private BooleanDatabase database;

    public AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }

    public BooleanDatabase getDatabase() {
        return database;
    }

    public void setDatabase(BooleanDatabase database) {
        this.database = database;
    }

    public static float frequency(Set<BooleanVariable> items, Set<Itemset> frequent){
        for(Itemset itemset: frequent){
            if(itemset.getItems().equals(items)){
                return itemset.getFrequency();
            }
        }
        return 0;
    }

    public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion,
                                   Set<Itemset> frequent){

        float frequencePremise=1;
        float frequenceConclusion=1;

        Set<BooleanVariable> premiseConclu=new HashSet<>();
        premiseConclu.addAll(conclusion);
        premiseConclu.addAll(premise);

        for(Itemset itemset: frequent){
            if(itemset.getItems().equals(premise)){
                frequencePremise=itemset.getFrequency();
            }else{
                if(itemset.getItems().equals(premiseConclu)){
                    frequenceConclusion=itemset.getFrequency();
                }
            }
        }

        if(conclusion.isEmpty()){
            return 1;
        }

        return frequenceConclusion/frequencePremise;
    }
}
