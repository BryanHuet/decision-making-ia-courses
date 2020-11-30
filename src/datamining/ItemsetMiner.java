package datamining;

import java.util.*;

public interface ItemsetMiner {

    BooleanDatabase getDataBase();

    Set<Itemset> extract(float frequence);


}
