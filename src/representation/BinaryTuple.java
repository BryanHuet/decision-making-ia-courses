package representation;

public class BinaryTuple {
    private Object _obj1;
    private Object _obj2;

    public BinaryTuple(Object obj1, Object obj2){
        _obj1=obj1;
        _obj2=obj2;
    }

    public Object get_obj1() {
        return _obj1;
    }

    public Object get_obj2() {
        return _obj2;
    }
}
