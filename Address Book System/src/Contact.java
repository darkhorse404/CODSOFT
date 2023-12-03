import java.io.IOException;
import java.io.Serializable;
import java.util.*;
//all packages imported

public class Contact implements Serializable {
    // Declaring member data
    String name, email, address, contactrelation, dob;
    int age;
    long phonenumber[];

    Contact(String a, long b[], String c, String d, String e, String f) // Parameterized constructor
    {
        // Initialising member data for current object
        this.name = a;
        this.phonenumber = b;
        this.email = c;
        this.dob = d;
        this.address = e;
        this.contactrelation = f;

    }

    public String toString() // Returns the accepted member data in String type in a specific format to be
                             // later extracted from and utilised appropriately
    {
        return (name + " " + phonenumber + " " + email + " " + dob + " " + address + " " + contactrelation);

    }

}
