package Test.Pt.Pa.Model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Before;
import org.junit.Test;
import pt.pa.model.NetworkException;
import pt.pa.model.Person;
import pt.pa.model.UniversityNetwork;
public class UniversityNetworkTest {
    private UniversityNetwork universityNetwork;

    @Before
    public void setUp() {
        universityNetwork = new UniversityNetwork();

        Person student1 = new Person(1, "Student1", Person.PersonRole.STUDENT);
        Person student2 = new Person(2, "Student2", Person.PersonRole.STUDENT);
        Person teacher1 = new Person(3, "Teacher1", Person.PersonRole.TEACHER);

        universityNetwork.addPerson(student1);
        universityNetwork.addPerson(student2);
        universityNetwork.addPerson(teacher1);
    }

    @Test
    public void test_personDoesNotExists_whenEmptyGraph() throws NetworkException {
        UniversityNetwork a = new UniversityNetwork();

        assertFalse(a.personExists("Rodrigo"));
    }
    @Test
    public void test_Thrown_Exception_afterAddExistentPerson() {

        Person student1 = new Person(135, "Rodrigo", Person.PersonRole.STUDENT);
        universityNetwork.addPerson(student1);


        assertThrows(NetworkException.class, () -> universityNetwork.addPerson(student1));
    }

    @Test
    public void test_PersonExist_afterInsert(){

        Person student1 = new Person(135, "Rodrigo", Person.PersonRole.STUDENT);
        universityNetwork.addPerson(student1);

        assertTrue(universityNetwork.personExists(student1.getName()));
    }

    @Test
    public void test_isThrown_Exception_afterGetInvalidRelationship() {
        int validId = 1;
        int invalidId = 999;

        assertDoesNotThrow(() -> universityNetwork.addGroupRelationship("Group", validId, validId + 1));

        assertThrows(NetworkException.class,
                () -> universityNetwork.getRelationships(validId, invalidId));

    }


}
