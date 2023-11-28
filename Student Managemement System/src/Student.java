import java.io.File;
import java.io.FileWriter;
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

// public void addS(String x, int y, int z) {
// String pathname = Integer.toString(z) + Integer.toString(y);
// File newfile = new File(pathname + ".txt");
// try {
// newfile.createNewFile();
// FileWriter writer = new FileWriter(newfile);
// try {
// writer.write("Name:" + x + "\n" + "Roll Number:" + y + "\n" + "Grade:" + z +
// "\n");
// writer.close();
// } catch (Exception e) {
// System.out.println("Sorry File could not be written");
// e.printStackTrace();
// }
// } catch (IOException e) {
// System.out.println("Sorry File could not be created");
// e.printStackTrace();
// }
// }
// public static void main(String[] args) throws Exception {
// System.out.println("Hello, World!");
// }