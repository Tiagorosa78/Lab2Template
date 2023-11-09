package pt.pa.model;

import pt.pa.graph.Edge;
import pt.pa.graph.Graph;
import pt.pa.graph.GraphLinked;
import pt.pa.graph.InvalidVertexException;
import pt.pa.graph.Vertex;

/**
 *
 * @author patricia.macedo
 */
public class UniversityNetwork {

    private Graph<Person, Relationship> network;

    public UniversityNetwork() {
        network = new GraphLinked<>();
    }

    private Vertex<Person> findPerson(int id) {
        for (Vertex<Person> v : network.vertices()) {
            if (v.element().getId() == id) {
                return v;
            }
        }
        return null;
    }



}
