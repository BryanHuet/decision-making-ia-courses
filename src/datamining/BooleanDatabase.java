package datamining;

import java.util.*;

import representation.BooleanVariable;

public class BooleanDatabase {

    private Set<BooleanVariable> items;
    private List<Set<BooleanVariable>> transactions;


    public BooleanDatabase(Set<BooleanVariable> items) {
        this.items = items;
        transactions = new ArrayList<>();
    }


    public Set<BooleanVariable> getItems() {
        return items;
    }

    public List<Set<BooleanVariable>> getTransactions() {
        return transactions;
    }

    public void add(Set<BooleanVariable> transaction) {
        transactions.add(transaction);
    }

    public String toString() {
        return "BooleanDatabase, avec items : " + this.items + " et transaction : " + this.transactions;
    }


}
