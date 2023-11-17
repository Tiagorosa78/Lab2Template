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



}
