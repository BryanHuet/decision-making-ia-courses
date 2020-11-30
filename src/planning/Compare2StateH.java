package planning;

import java.util.*;

import representation.*;

public class Compare2StateH implements Comparator<Map<Variable, Object>> {

    private final Map<Map<Variable, Object>, Float> dist;
    private final Map<Map<Variable, Object>, Float> value;

    public Compare2StateH(Map<Map<Variable, Object>, Float> dist, Map<Map<Variable, Object>, Float> value) {
        this.dist = dist;
        this.value = value;
    }

    @Override
    public int compare(Map<Variable, Object> act1, Map<Variable, Object> act2) {
        if ((dist.get(act1) + value.get(act1)) > (dist.get(act2) + value.get(act2))) {
            return 1;
        } else {
            if ((dist.get(act1) + value.get(act1)) < (dist.get(act2) + value.get(act2))) {
                return -1;
            }
        }
        return 0;
    }

}
