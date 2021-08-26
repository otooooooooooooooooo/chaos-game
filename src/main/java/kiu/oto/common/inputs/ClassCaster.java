package kiu.oto.common.inputs;

public interface ClassCaster<A,B> {
    public B cast(A a);
}
