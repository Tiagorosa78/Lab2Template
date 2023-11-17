package pt.pa.model;

import pt.pa.graph.Edge;
import pt.pa.graph.Graph;
import pt.pa.graph.GraphLinked;
import pt.pa.graph.InvalidVertexException;
import pt.pa.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

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

    public void addPerson(Person p) throws NetworkException {
        if (findPerson(p.getId()) != null) {
            throw new NetworkException("Person with ID " + p.getId() + " already exists in the network.");
        }
        network.insertVertex(p);
    }

    public void addGroupRelationship(String description, int idStudent1, int idStudent2) throws NetworkException {
        Vertex<Person> student1 = findPerson(idStudent1);
        Vertex<Person> student2 = findPerson(idStudent2);

        if (student1 == null || student2 == null) {
            throw new NetworkException("One or both students do not exist in the network.");
        }

        if (!student1.element().isRole(Person.PersonRole.STUDENT) || !student2.element().isRole(Person.PersonRole.STUDENT)) {
            throw new NetworkException("One or both IDs do not refer to students.");
        }

        Relationship groupRelationship = new Relationship(description, Relationship.RelRole.GROUP);
        network.insertEdge(student1, student2, groupRelationship);
    }

    public void addClassRelationship(String description, int idTeacher, int idStudent) throws NetworkException {
        Vertex<Person> teacher = findPerson(idTeacher);
        Vertex<Person> student = findPerson(idStudent);

        if (teacher == null || student == null) {
            throw new NetworkException("One or both persons do not exist in the network.");
        }

        if (teacher.element().getRole() != Person.PersonRole.TEACHER || student.element().getRole() != Person.PersonRole.STUDENT) {
            throw new NetworkException("One or both IDs do not refer to the correct type.");
        }

        Relationship classRelationship = new Relationship(description, Relationship.RelRole.CLASS);
        network.insertEdge(teacher, student, classRelationship);
    }

    public boolean personExists(String name) {
        for (Vertex<Person> v : network.vertices()) {
            if (v.element().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Person> getIsolated(){
        List<Person> isolatedPersons = new ArrayList<>();

        for(Vertex<Person> person : network.vertices()){
            if(network.incidentEdges(person) == null){
                isolatedPersons.add(person.element());
            }
        }
        return isolatedPersons;
    }

    public List<Relationship> getRelationships(int id, int id2) throws NetworkException {
        Vertex<Person> person1 = findPerson(id);
        Vertex<Person> person2 = findPerson(id2);

        if (person1 == null || person2 == null) {
            throw new NetworkException("Um ou ambos os IDs não são válidos.");
        }

        List<Relationship> relationships = new ArrayList<>();

        for (Edge<Relationship, Person> edge : network.incidentEdges(person1)) {
            if (edge.vertices()[0] == person2 || edge.vertices()[1] == person2) {
                relationships.add(edge.element());
            }
        }

        return relationships;
    }

    public String toStringTeachersStudent() {
        StringBuilder result = new StringBuilder();

        for (Vertex<Person> vertex : network.vertices()) {
            Person person = (Person) vertex.element();

            if (person.getRole() == Person.PersonRole.TEACHER) {
                result.append("Professor ").append(person.getName()).append("(").append(person.getId()).append(")\n");

                for (Edge<Relationship, Person> edge : network.incidentEdges(vertex)) {
                    Vertex<Person> studentVertex = network.opposite(vertex, edge);
                    Person student = (Person) studentVertex.element();

                    result.append("\tde ").append(getRelationships(person.getId(), student.getId())).append(" de Aluno ").append(student.getName()).append("(").append(student.getId()).append(")\n");
                }
            }
        }

        return result.toString();
    }
}
