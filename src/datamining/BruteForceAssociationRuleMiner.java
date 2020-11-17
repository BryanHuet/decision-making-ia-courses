package datamining;

import representation.BooleanVariable;

import java.util.*;

public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner{

    public BruteForceAssociationRuleMiner(BooleanDatabase database){
        super(database);
    }


    //inspiré de https://stackoverflow.com/questions/4640034/calculating-all-of-the-subsets-of-a-set-of-numbers
    private static Set<Set<BooleanVariable>> powerSet(Set<BooleanVariable> candidat){
        Set<Set<BooleanVariable>> sets = new HashSet<>();
        sets.add(new HashSet<>());
        for(BooleanVariable value : candidat){
            final Set<Set<BooleanVariable>> newSets = new HashSet<>(sets);
            for(Set<BooleanVariable> set : sets){
                final Set<BooleanVariable> newSet = new HashSet<>(set);
                newSet.add(value);
                newSets.add(newSet);
            }
            sets=newSets;
        }
        return sets;
    }


    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> candidat){

        Set<Set<BooleanVariable>> candidatesPremises = powerSet(candidat);
        candidatesPremises.remove(new HashSet<>());
        candidatesPremises.remove(candidat);
        return candidatesPremises;
    }

    @Override
    public Set<AssociationRule> extract(float minFrequency, float minConfidence) {
        return null;
    }
}
