package representation;

public class BinaryTuple {
    private Object obj1;
    private Object obj2;

    public BinaryTuple(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public Object getObj1() {
        return obj1;
    }

    public Object getObj2() {
        return obj2;
    }

    @Override
    public String toString() {
        return "BinaryTuple { " +
                "obj1= " + obj1 +
                ", obj2= " + obj2 +
                " } ";
    }

    @Override
    public boolean equals(Object other) {
        if ((this.obj1 != null) && (this.obj2 != null) && (other instanceof BinaryTuple)) {
            BinaryTuple cast_other = (BinaryTuple) other;
            return this.obj1.equals(cast_other.getObj1()) && this.obj2.equals(cast_other.getObj2());
        }
        return false;
    }

}
