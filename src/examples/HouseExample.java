package examples;

import representation.Constraint;
import representation.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HouseExample {
    private int _largeur;
    private int _longueur;
    private Set<String> _pieces;
    private Set<Variable> _variables=new HashSet<>();
    private Set<Constraint> _constraints=new HashSet<>();

    public HouseExample(int largeur, int longueur, Set<String> pieces) {
        _largeur=largeur;
        _longueur=longueur;
        _pieces=pieces;
    }

    public void addVariable(Variable v1){
        _variables.add(v1);
    }
    public void addConstaint(Constraint c1){
        _constraints.add(c1);
    }
    public Set<Variable> getVariables(){
        return _variables;
    }

    public Set<Constraint> get_constraints() {
        return _constraints;
    }

}
