package pt.pa;

import pt.pa.graph.Edge;
import pt.pa.graph.GraphLinked;
import pt.pa.graph.Vertex;
import pt.pa.model.Person;
import pt.pa.model.UniversityNetwork;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author amfs
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        UniversityNetwork universityNetwork = new UniversityNetwork();

        Person student1 = new Person(135, "Rodrigo", Person.PersonRole.STUDENT);
        Person student2 = new Person(215, "Cátia", Person.PersonRole.STUDENT);
        Person student3 = new Person(18, "Alberto", Person.PersonRole.STUDENT);
        Person student4 = new Person(231, "Rita", Person.PersonRole.STUDENT);
        Person student5 = new Person(2, "Ana", Person.PersonRole.STUDENT);
        Person student6 = new Person(131, "Pedro", Person.PersonRole.STUDENT);
        Person student7 = new Person(235, "Joao", Person.PersonRole.STUDENT);

        Person docente1 = new Person(35, "Pedro", Person.PersonRole.TEACHER);
        Person docente2 = new Person(31, "Ana", Person.PersonRole.TEACHER);

        universityNetwork.addPerson(student1);
        universityNetwork.addPerson(student2);
        universityNetwork.addPerson(student3);
        universityNetwork.addPerson(student4);
        universityNetwork.addPerson(student5);
        universityNetwork.addPerson(student6);
        universityNetwork.addPerson(student7);
        universityNetwork.addPerson(docente1);
        universityNetwork.addPerson(docente2);

        universityNetwork.addGroupRelationship("Colegas de Grupo, Algebra 1 ", student1.getId(), student2.getId());
        universityNetwork.addClassRelationship("Algebra Geral ", docente2.getId(), student1.getId());
        universityNetwork.addClassRelationship("Algebra Geral", docente2.getId(), student2.getId());
        universityNetwork.addClassRelationship("Analise Matematica", docente2.getId(), student3.getId());
        universityNetwork.addClassRelationship("Analise Matematica", docente2.getId(), student6.getId());
        universityNetwork.addGroupRelationship("Colegas De Tuna", student3.getId(), student4.getId());
        universityNetwork.addClassRelationship("PA", docente1.getId(), student6.getId());
        universityNetwork.addGroupRelationship("Colega de Grupo PA.1", student6.getId(), student7.getId());
        universityNetwork.addClassRelationship("PA", docente1.getId(), student7.getId());
        universityNetwork.addClassRelationship("PA", docente1.getId(), student3.getId());

        System.out.println(universityNetwork.toStringTeachersStudent());
    }


}

