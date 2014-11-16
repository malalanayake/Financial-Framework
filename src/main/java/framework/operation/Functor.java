package framework.operation;

/**
 *
 * @author malalanayake
 */
public interface Functor<T, R> {

    public void compute(T data);

    public R getValue();
}
