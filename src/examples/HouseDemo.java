package examples;

import datamining.AssociationRule;
import datamining.BruteForceAssociationRuleMiner;
import datamining.Database;
import planning.*;
import representation.*;
import solvers.BacktrackSolver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HouseDemo {
    public static void main(String[] args) {
        essaiePetiteMaison();
        essaiePlanification();
        essaieDatamining();
    }

    public static void essaiePetiteMaison() {

        System.out.println("---------- BacktrackSolver sur une maison de 9m2 ----------\n");

        Set<Object> pieces = new HashSet<>();

        HouseExample maison = new HouseExample(9, 9);


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

        //System.out.println("---------------------------------------------------------------");
    }

    public static void essaiePlanification() {
        System.out.println("\n------- Planification des pieces sur une petite maison -------\n");

        Set<Object> pieces = new HashSet<>();
        HouseExample maison = new HouseExample(10, 15);
        Set<Action> actions = new HashSet<>();
        Map<Variable, Object> solution = new HashMap<>();
        Map<Variable, Object> goalState = new HashMap<>();

        pieces.add("Chambre");
        pieces.add("Salon");
        pieces.add("Toilettes");
        pieces.add("Cuisine");
        pieces.add("Salle de Bain");
        pieces.add("Chambre 2");

        for (int i = 0; i < pieces.size(); i++) {
            Variable piece = new Variable("piece " + i, pieces);
            maison.addVariable(piece);
            solution.put(piece, null);
            goalState.put(piece, "Chambre");
            for (int j = 0; j < pieces.size(); j++) {
                for (int k = 0; k < pieces.size(); k++) {
                    for (Object p : pieces) {
                        Map<Variable, Object> precondition = new HashMap<>();
                        precondition.put(piece, null);
                        Map<Variable, Object> effet = new HashMap<>();
                        effet.put(piece, p);
                        actions.add(new BasicAction(precondition, effet, 5));
                    }
                }
            }
        }

        Goal goal = new BasicGoal(goalState);
        Heuristic heuristic = new Heuristic() {
            @Override
            public float estimate(Map<Variable, Object> state) {
                float value = 0;
                for (Map.Entry<Variable, Object> entry : state.entrySet()) {
                    if (entry.getValue() == null) {
                        value += 10;
                    }
                }
                return value;
            }
        };

        AStarPlanner as = new AStarPlanner(solution, actions, new DifferenceGoal(), heuristic);
        for (Action act : as.plan()) {
            System.out.println(act);
        }

        //System.out.println("---------------------------------------------------------------");
    }

    public static void essaieDatamining() {
        System.out.println("\n-------- Extraction de regle sur des petites maisons ---------\n");

        Set<Variable> variables = new HashSet<>();
        Set<Object> pieces = new HashSet<>();
        Database base = new Database(variables);
        Variable piece11 = new Variable("Piece (1,1) ", pieces);
        Variable piece12 = new Variable("Piece (1,2) ", pieces);
        Variable piece01 = new Variable("Piece (0,1) ", pieces);
        Variable piece02 = new Variable("Piece (0,2) ", pieces);

        pieces.add("Chambre 1");
        pieces.add("Salon");
        pieces.add("Toilettes");
        pieces.add("Cuisine");
        pieces.add("Salle de Bain");
        pieces.add("Chambre 2");

        variables.add(piece11);
        variables.add(piece12);
        variables.add(piece01);
        variables.add(piece02);

        for (int i = 0; i < 100; i++) {
            Map<Variable, Object> instance = new HashMap<>();
            instance.put(piece11, "Chambre 1");
            instance.put(piece12, "Chambre 2");
            Map<Variable, Object> instance2 = new HashMap<>();
            instance2.put(piece01, "Salle de Bain");
            instance2.put(piece02, "Cuisine");
            if (i % 10 == 0) {
                instance.put(piece11, "Chambre 1");
                instance.put(piece12, "Salle de Bain");
                instance2.put(piece01, "Cuisine");
                instance2.put(piece02, "Chambre 2");

            }
            base.add(instance);
            base.add(instance2);
        }

        BruteForceAssociationRuleMiner bf = new BruteForceAssociationRuleMiner(base.propositionalize());

        System.out.println("Regles de frequence et confiance minimum de 40% :");
        for (AssociationRule regle : bf.extract((float) 0.4, (float) 0.4)) {
            System.out.println(regle);
        }

        System.out.println("\nRegle de frequence et confiance minimum de 0.1% :");
        for (AssociationRule regle : bf.extract((float) 0.001, (float) 0.001)) {
            System.out.println(regle);
        }


        System.out.println("---------------------------------------------------------------");


    }
}
