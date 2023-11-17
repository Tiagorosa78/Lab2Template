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

        Person aluno1 = new Person(135, "Rodrigo", Person.PersonRole.STUDENT);
        Person aluno2 = new Person(215, "Cátia", Person.PersonRole.STUDENT);
        Person aluno3 = new Person(18, "Alberto", Person.PersonRole.STUDENT);
        Person aluno4 = new Person(231, "Rita", Person.PersonRole.STUDENT);
        Person aluno5 = new Person(2, "Ana", Person.PersonRole.STUDENT);
        Person aluno6 = new Person(131, "Pedro", Person.PersonRole.STUDENT);
        Person aluno7 = new Person(235, "Joao", Person.PersonRole.STUDENT);

        Person docente1 = new Person(35, "Pedro", Person.PersonRole.TEACHER);
        Person docente2 = new Person(31, "Ana", Person.PersonRole.TEACHER);

        universityNetwork.addPerson(aluno1);
        universityNetwork.addPerson(aluno2);
        universityNetwork.addPerson(aluno3);
        universityNetwork.addPerson(aluno4);
        universityNetwork.addPerson(aluno5);
        universityNetwork.addPerson(aluno6);
        universityNetwork.addPerson(aluno7);
        universityNetwork.addPerson(docente1);
        universityNetwork.addPerson(docente2);

        universityNetwork.addGroupRelationship("Colegas de Grupo, Algebra 1 ", aluno1.getId(), aluno2.getId());
        universityNetwork.addClassRelationship("Algebra Geral ", docente2.getId(), aluno1.getId());
        universityNetwork.addClassRelationship("Algebra Geral", docente2.getId(), aluno2.getId());
        universityNetwork.addClassRelationship("Analise Matematica", docente2.getId(), aluno3.getId());
        universityNetwork.addClassRelationship("Analise Matematica", docente2.getId(), aluno6.getId());
        universityNetwork.addGroupRelationship("Colegas De Tuna", aluno3.getId(), aluno4.getId());
        universityNetwork.addClassRelationship("PA", docente1.getId(), aluno6.getId());
        universityNetwork.addGroupRelationship("Colega de Grupo PA.1", aluno6.getId(), aluno7.getId());
        universityNetwork.addClassRelationship("PA", docente1.getId(), aluno7.getId());
        universityNetwork.addClassRelationship("PA", docente1.getId(), aluno3.getId());

        System.out.println(universityNetwork.toStringTeachersStudent());
        System.out.println(universityNetwork.getNumberOfStudents(35));
        System.out.println(universityNetwork.getMostPopularPerson().getName());

        universityNetwork.removeRelationships(docente2.getId(), aluno2.getId());
        System.out.println(universityNetwork.toStringTeachersStudent());
        System.out.println(universityNetwork.getNumberOfStudents(35));
        System.out.println(universityNetwork.getMostPopularPerson().getName());
    }


}

