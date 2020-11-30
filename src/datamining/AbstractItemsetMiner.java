package datamining;

import java.util.*;

import representation.BooleanVariable;

public abstract class AbstractItemsetMiner implements ItemsetMiner {

    private BooleanDatabase base;
    public static final Comparator<BooleanVariable> COMPARATOR = (var1, var2) -> var1.getName().compareTo(var2.getName());

    public AbstractItemsetMiner(BooleanDatabase base) {
        this.base = base;
    }

    @Override
    public BooleanDatabase getDataBase() {
        return this.base;
    }

    public float frequency(Set<BooleanVariable> items) {
        float occurence = 0;

        if (items.isEmpty()) {
            return (float) 1.0;
        }
        for (Set<BooleanVariable> i : this.base.getTransactions()) {
            if (i.containsAll(items)) {
                occurence = occurence + 1;
            }
        }
        return occurence / this.base.getTransactions().size();
    }

}
