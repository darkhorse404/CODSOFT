import java.io.IOException;
import java.io.Serializable;
import java.util.*;
//all packages imported

public class Student implements Serializable {
    // Declaring member data
    String sname, dob, address;
    int rollnumber, grade, age;
    long contactno;

    Student(String a, int b, int c, String d, int e, long f, String g) // Parameterized constructor
    {
        // Initialising member data for current object
        this.sname = a;
        this.grade = b;
        this.rollnumber = c;
        this.dob = d;
        this.age = e;
        this.contactno = f;
        this.address = g;
    }

    public String toString() // Returns the accepted member data in String type in a specific format to be
                             // later extracted from and utilised appropriately
    {
        return (sname + " " + grade + " " + rollnumber + " " + dob + " " + age + " " + contactno + " " + address);
    }

}
