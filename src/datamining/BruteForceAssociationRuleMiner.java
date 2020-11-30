package datamining;

import representation.BooleanVariable;

import java.util.*;

public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {

    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database);
    }


    //inspiré de https://stackoverflow.com/questions/4640034/calculating-all-of-the-subsets-of-a-set-of-numbers
    private static Set<Set<BooleanVariable>> powerSet(Set<BooleanVariable> candidat) {
        Set<Set<BooleanVariable>> sets = new HashSet<>();
        sets.add(new HashSet<>());
        for (BooleanVariable value : candidat) {
            final Set<Set<BooleanVariable>> newSets = new HashSet<>(sets);
            for (Set<BooleanVariable> set : sets) {
                Set<BooleanVariable> newSet = new HashSet<>(set);
                newSet.add(value);
                newSets.add(newSet);
            }
            sets = newSets;
        }
        return sets;
    }


    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> candidat) {

        Set<Set<BooleanVariable>> candidatesPremises = powerSet(candidat);
        candidatesPremises.remove(new HashSet<>());
        candidatesPremises.remove(candidat);
        return candidatesPremises;
    }

    @Override
    public Set<AssociationRule> extract(float minFrequency, float minConfidence) {
        Set<AssociationRule> associations = new HashSet<>();
        Set<Itemset> motifFrequents = new Apriori(this.getDatabase()).extract(minFrequency);
        for (Itemset motif : motifFrequents) {
            for (Set<BooleanVariable> premise : allCandidatePremises(motif.getItems())) {
                Set<BooleanVariable> conclusion = new HashSet<>();
                for (BooleanVariable var : motif.getItems()) {
                    if (!premise.contains(var)) {
                        conclusion.add(var);
                    }
                }
                AssociationRule newRule = new AssociationRule(premise, conclusion, frequency(motif.getItems(), motifFrequents),
                        confidence(premise, conclusion, motifFrequents));
                if (newRule.getFrequency() >= minFrequency && newRule.getConfidence() >= minConfidence) {
                    associations.add(newRule);
                }
            }
        }
        return associations;
    }
}
