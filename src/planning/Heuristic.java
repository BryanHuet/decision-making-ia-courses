package planning;

import java.util.Map;

import representation.*;

public interface Heuristic {

    float estimate(Map<Variable, Object> state);

}