package planning;

import java.util.Map;

public interface Heuristic{

    float estimate(Map<Variable,Object>);

}