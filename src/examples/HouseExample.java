package examples;

import representation.Constraint;
import representation.Variable;

import java.util.HashSet;
import java.util.Set;

public class HouseExample {
    private int largeur;
    private int longueur;
    private Set<Variable> variables;
    private Set<Constraint> constraints;

    public HouseExample(int largeur, int longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.constraints = new HashSet<>();
        this.variables = new HashSet<>();
    }

    public void addVariable(Variable v1) {
        variables.add(v1);
    }

    public void addConstaint(Constraint c1) {
        constraints.add(c1);
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    public String toString() {
        return "variables : " + this.variables + "\nconstraints : " + this.constraints;
    }

}
