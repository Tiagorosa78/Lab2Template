package pt.pa.graph;

/**
 *
 * @author patricia.macedo
 */
public interface Edge<E,V> {
    public E element()throws InvalidEdgeException;
    public Vertex<V>[] vertices();
}
