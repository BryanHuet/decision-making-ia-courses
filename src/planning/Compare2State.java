package planning;

import java.util.*;

import representation.*;

public class Compare2State implements Comparator<Map<Variable, Object>> {

    Map<Map<Variable, Object>, Integer> dist;

    public Compare2State(Map<Map<Variable, Object>, Integer> dist) {
        this.dist = dist;
    }

    @Override
    public int compare(Map<Variable, Object> act1, Map<Variable, Object> act2) {
        if (dist.get(act1) > dist.get(act2)) {
            return 1;
        } else {
            if (dist.get(act1) < dist.get(act2)) {
                return -1;
            }
        }
        return 0;
    }

}
