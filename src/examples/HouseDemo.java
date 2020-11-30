package examples;

import representation.*;
import solvers.BacktrackSolver;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HouseDemo {
    public static void main(String[] args) {
        essaiePetiteMaison();
    }

    public static void essaiePetiteMaison() {

        System.out.println("---------- BacktrackSolver sur une maison de 9m2 ----------");

        Set<Object> pieces = new HashSet<>();

        HouseExample maison = new HouseExample(10, 15);


        Variable dalleHumide = new BooleanVariable("Dalle humide");
        Variable attendre = new BooleanVariable("Attendre que la dalle seche");
        Variable dalleSeche = new BooleanVariable("Dalle seche");
        Variable murs = new BooleanVariable("Faire les murs");
        Variable toit = new BooleanVariable("Faire le toit");
        Variable piece1 = new Variable("piece 1", pieces);
        Variable piece2 = new Variable("piece 2", pieces);
        Variable piece3 = new Variable("piece 3", pieces);

        Constraint p1Dp2 = new DifferenceConstraint(piece1, piece2);
        Constraint p1Dp3 = new DifferenceConstraint(piece1, piece3);
        Constraint p2Dp3 = new DifferenceConstraint(piece2, piece3);

        Constraint dallesDiff = new DifferenceConstraint(dalleHumide, dalleSeche);
        Constraint dalleHumideMurs = new DifferenceConstraint(dalleHumide, murs);
        Constraint dalleHumideToit = new DifferenceConstraint(dalleHumide, toit);

        BinaryExtensionConstraint dalleHumideETattendre = new BinaryExtensionConstraint(dalleHumide, attendre);
        dalleHumideETattendre.addTuple(true, true);
        dalleHumideETattendre.addTuple(false, false);
        BinaryExtensionConstraint dalleSecheETattendre = new BinaryExtensionConstraint(dalleSeche, attendre);
        dalleSecheETattendre.addTuple(true, false);
        dalleSecheETattendre.addTuple(false, true);
        BinaryExtensionConstraint mursETtoit = new BinaryExtensionConstraint(murs, toit);
        mursETtoit.addTuple(false, false);
        mursETtoit.addTuple(true, false);
        mursETtoit.addTuple(true, true);


        pieces.add("Chambre");
        pieces.add("Salon");
        pieces.add("Toilettes");


        maison.addVariable(dalleHumide);
        maison.addVariable(attendre);
        maison.addVariable(dalleSeche);
        maison.addVariable(toit);
        maison.addVariable(murs);
        maison.addVariable(piece1);
        maison.addVariable(piece2);
        maison.addVariable(piece3);

        maison.addConstaint(dalleHumideETattendre);
        maison.addConstaint(dallesDiff);
        maison.addConstaint(dalleSecheETattendre);
        maison.addConstaint(dalleHumideMurs);
        maison.addConstaint(dalleHumideToit);
        maison.addConstaint(mursETtoit);
        maison.addConstaint(p1Dp2);
        maison.addConstaint(p2Dp3);
        maison.addConstaint(p1Dp3);

        BacktrackSolver bs = new BacktrackSolver(maison.getVariables(), maison.getConstraints());
        Map<Variable, Object> solution = bs.solve();

        if (solution != null) {
            System.out.println(dalleHumide.getName() + " : " + solution.get(dalleHumide) + "\n" +
                    attendre.getName() + " : " + solution.get(attendre) + "\n" +
                    dalleSeche.getName() + " : " + solution.get(dalleSeche) + "\n" +
                    murs.getName() + " : " + solution.get(murs) + "\n" +
                    toit.getName() + " : " + solution.get(toit) + "\n" +
                    piece1.getName() + " : " + solution.get(piece1) + "\n" +
                    piece2.getName() + " : " + solution.get(piece2) + "\n" +
                    piece3.getName() + " : " + solution.get(piece3));
        }

        System.out.println("---------------------------------------------------------------");
    }

    public static void essaiePlanification() {
        System.out.println("---------- BacktrackSolver sur une maison de 9m2 ----------");
        System.out.println("---------------------------------------------------------------");
    }
}
