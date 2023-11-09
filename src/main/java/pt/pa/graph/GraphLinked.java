package pt.pa.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author patricia.macedo
 */
public class GraphLinked<V, E> implements Graph<V, E> {

    private int nEdges;
    private HashMap<V, Vertex<V>> listVertices;

    public GraphLinked() {
        this.nEdges = 0;
        listVertices = new HashMap();
    }

    private MyVertex checkVertex(Vertex<V> p) throws InvalidVertexException {
        if (p == null) {
            throw new InvalidVertexException("WRONG vertex");
        }
        try {
            return (MyVertex) p;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("WRONG vertex");
        }
    }

    private MyEdge checkEdge(Edge<E, V> ed) throws InvalidEdgeException {
        if (ed == null) {
            throw new InvalidEdgeException("WRONG edge");
        }
        try {
            return (MyEdge) ed;
        } catch (ClassCastException e) {
            throw new InvalidEdgeException("WRONG edge");
        }
    }

    @Override
    public int numVertices() {
        return listVertices.size();

    }

    @Override
    public int numEdges() {
        return nEdges;
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return listVertices.values();

    }

    @Override
    public Iterable<Edge<E, V>> edges() {
        return setofEdges();
    }

    private Set<Edge<E, V>> setofEdges() {
        Set<Edge<E, V>> edges = new HashSet();
        for (Vertex<V> vertex : vertices()) {

            edges.addAll(checkVertex(vertex).listaEdges);
        }
        return edges;
    }

    @Override
    public V replace(Vertex<V> p, V elem) throws InvalidVertexException {
        if (!this.listVertices.containsValue(p)) {
            throw new InvalidVertexException("vertex does not exist");
        }

        MyVertex vertex = checkVertex(p);
        V elem1 = vertex.element();
        vertex.elem = elem;
        return elem1;
    }

    @Override
    public E replace(Edge<E, V> p, E elem) throws InvalidEdgeException {
        if (!existEdge(p)) {
            throw new InvalidEdgeException("Invalid Edge");
        }
        MyEdge edge = checkEdge(p);
        E elem1 = edge.element();
        edge.elem = elem;
        return elem1;
    }

    private boolean existEdge(Edge e) {
        return setofEdges().contains(e);
    }

    @Override
    public Iterable<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidEdgeException {
        if (!this.listVertices.containsValue(v)) {
            throw new InvalidVertexException("vertex does not exist");
        }
        return checkVertex(v).listaEdges;
    }

    
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        Vertex<V>[] vertices = checkEdge(e).vertices();
        if (vertices[0] == v) {
            return vertices[1];
        }
        if (vertices[1] == v) {
            return vertices[0];
        }
        throw new InvalidVertexException("Invalid vertex");
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        for (Edge<E, V> e : edges()) {
            MyEdge myedge = checkEdge(e);
            if (myedge.vertexIn == u && myedge.vertexOut == v
                    || myedge.vertexOut == u && myedge.vertexIn == v) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vertex<V> insertVertex(V elem) throws InvalidVertexException {
        if (listVertices.containsKey(elem)) {
            throw new InvalidVertexException(elem + "already exists ");
        }
        MyVertex vertex = new MyVertex(elem);
        listVertices.put(elem, vertex);
        return vertex;

    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E elem) throws InvalidVertexException {
        if (!listVertices.containsKey(u.element())) {
            throw new InvalidVertexException(u.element() + "not exists ");
        }
        if (!listVertices.containsKey(v.element())) {
            throw new InvalidVertexException(v.element() + "not exists ");
        }

     
        MyEdge edge = new MyEdge(elem, u, v);
        // coloca-lo nos vertices.
        checkVertex(u).listaEdges.add(edge);
        checkVertex(v).listaEdges.add(edge);
        return edge;
    }

    @Override
    public Edge<E, V> insertEdge(V elem1, V elem2, E o) throws InvalidVertexException {
        if ((!listVertices.containsKey(elem1)) || (!listVertices.containsKey(elem2))) {
            throw new InvalidVertexException("Invalid Vertex");
        }

        Vertex<V> v1 = listVertices.get(elem1);
        Vertex<V> v2 = listVertices.get(elem2);
        MyEdge e = new MyEdge(o, v1, v2);
        checkVertex(v1).listaEdges.add(e);
        checkVertex(v2).listaEdges.add(e);
        nEdges++;
        return e;
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        if (!listVertices.containsValue(v)) {
            throw new InvalidVertexException("not exists ");
        }
        MyVertex vertex = checkVertex(v);
        if (!vertex.listaEdges.isEmpty()) {
            throw new InvalidVertexException(" vertex has incident edges");
        }
        listVertices.remove(v);
        return v.element();

    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        if (!existEdge(e)) {
            throw new InvalidEdgeException("not exists ");
        }
        Vertex<V>[] vertices = checkEdge(e).vertices();
        checkVertex(vertices[0]).listaEdges.remove(e);
        checkVertex(vertices[1]).listaEdges.remove(e);
        nEdges--;
        return e.element();

    }

    
    
    private class MyVertex implements Vertex<V> {

        private V elem;
        private List<Edge<E, V>> listaEdges;

        public MyVertex(V elem) {
            this.elem = elem;
            this.listaEdges = new ArrayList<>();

        }

        @Override
        public V element() throws InvalidVertexException {
            if (elem == null) {
                throw new InvalidVertexException("vertex null");
            }
            return elem;
        }

    }

    private class MyEdge implements Edge<E, V> {

        private E elem;
        private Vertex<V> vertexIn, vertexOut;

        public MyEdge(E elem, Vertex<V> vertexIn, Vertex<V> vertexOut) {
            this.elem = elem;
            this.vertexIn = vertexIn;
            this.vertexOut = vertexOut;
        }

        @Override
        public E element() throws InvalidEdgeException {
            if (elem == null) {
                throw new InvalidEdgeException("edge null");
            }
            return elem;
        }

        @Override
        public Vertex<V>[] vertices() {
            Vertex[] vertices= new Vertex[2];
            vertices[0]=vertexIn;
            vertices[1]=vertexOut;
            return vertices;      
        }

    }

}
