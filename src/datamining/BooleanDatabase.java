package datamining;
import java.util.*;
import representation.BooleanVariable;
public class BooleanDatabase{

  private Set<BooleanVariable> _items;
  private List<Set<BooleanVariable>> _transactions;


  public BooleanDatabase(Set<BooleanVariable> items){
    _items=items;
    _transactions=new ArrayList<>();
  }


  public Set<BooleanVariable> getItems(){
    return _items;
  }
  public List<Set<BooleanVariable>> getTransactions(){
    return _transactions;
  }
  public void add(Set<BooleanVariable> transaction){
    _transactions.add(transaction);
  }
/*
  public String toString(){
    return "BooleanDatabase, avec items : "+_items+ " et transaction : "+_transactions;
  }
*/

}
