package datamining;

import representation.BooleanVariable;
import representation.Variable;

import java.util.*;

public class Database {

    private Set<Variable> variables;
    private List<Map<Variable,Object>> instances;

    public Database(Set<Variable> variables) {
        this.variables = variables;
        this.instances =new ArrayList<>();
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public List<Map<Variable, Object>> getInstances() {
        return instances;
    }

    public void add(Map<Variable,Object> instance){
        this.instances.add(instance);
    }

    public Map<Variable,Map<Object, BooleanVariable>> itemTable(){
        Map<Variable,Map<Object, BooleanVariable>> table = new HashMap<>();
        for(Variable var : this.getVariables()){
            Map<Object,BooleanVariable> instance = new HashMap<>();
            if(var instanceof BooleanVariable){
                instance.put(true, (BooleanVariable) var);
                instance.put(false, null);
            }else{
                for(Object obj : var.getDomain()){
                    instance.put(obj,new BooleanVariable(var.getName()+obj.toString()));
                }
            }
            table.put(var,instance);
        }
        return table;
    }

    public BooleanDatabase propositionalize(){
        Set<BooleanVariable> construct = new HashSet<>();
        for (Map.Entry<Variable,Map<Object, BooleanVariable>> entry : this.itemTable().entrySet()) {
            construct.addAll(entry.getValue().values());
        }
        construct.remove(null);
        BooleanDatabase bd = new BooleanDatabase(construct);
        if(!this.getInstances().isEmpty()){
            for(Map<Variable,Object> instance : this.getInstances()){
                Set<BooleanVariable> transaction = new HashSet<>();
                for(Map.Entry<Variable,Object> entry : instance.entrySet()){
                    if(entry.getKey() instanceof BooleanVariable){
                        if((Boolean) entry.getValue()){
                        transaction.add((BooleanVariable) entry.getKey());
                        }
                    }else{
                        if(entry.getKey().getDomain().contains(entry.getValue())){
                            transaction.add(new BooleanVariable(entry.getKey().getName()+entry.getValue().toString()));
                        }
                    }
                }
                bd.add(transaction);
            }
        }
        return bd;
    }

}
