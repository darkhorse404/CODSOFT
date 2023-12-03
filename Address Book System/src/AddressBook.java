import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
//all packages imported

public class AddressBook {

    // Declaring certain member data
    ArrayList<Contact> aList = new ArrayList<Contact>();
    ListIterator listi = null;

    // Sub methods to be utilised in other methods for various purpose
    public String[] deletearrayelement(String x[], int index) { // Method to remove an element from an array
        String newarr[] = new String[x.length - 1];
        for (int i = 0, k = 0; i < x.length; i++) {
            if (i != index) {
                newarr[k] = x[i];
                k++;
            }
        }
        return newarr;
    }

    public boolean InputisnotEmpty(String x) { // Method to check if any input field is left empty
        if (x.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean emailcheck(String x) { // Method to check if email input is valid
        if (x.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInteger(String x) { // Method to check if certain input fields are integers
        try {
            int y = Integer.parseInt(x);
            if (y < 0) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            // TODO: handle exception
            return false;
        }
    }

    public boolean isLong(String x) { // Method to check if certain input fields are long and contain more than 10
                                      // digits
        try {
            Long y = Long.parseLong(x);
            if (x.length() < 10) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            // TODO: handle exception
            return false;
        }
    }

    public boolean dobcheck(String dob) { // Method to check if input field Date of Birth has input in correct format
        boolean result = false;
        String date = "", month = "", year = "";
        if (dob.contains("/")) {
            date = dob.substring(0, dob.indexOf("/"));
            month = dob.substring(dob.indexOf("/") + 1, dob.lastIndexOf("/"));
            year = dob.substring(dob.lastIndexOf("/") + 1);

        } else if (dob.contains(".")) {
            date = dob.substring(0, dob.indexOf("."));
            month = dob.substring(dob.indexOf(".") + 1, dob.lastIndexOf("."));
            year = dob.substring(dob.lastIndexOf(".") + 1);
        }
        if (isInteger(date) && isInteger(month) && isInteger(year)) {
            if ((Integer.parseInt(date) > 0 && Integer.parseInt(date) <= 31)
                    && (Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 12))
                result = true;

        }
        return result;
    }

    public boolean InputValidation(String a, String[] b, String c, String d, String e, String f) { // To use
                                                                                                   // certain
                                                                                                   // methods
                                                                                                   // and check
                                                                                                   // if
                                                                                                   // enterred
                                                                                                   // input is
                                                                                                   // valid or
                                                                                                   // not and
                                                                                                   // perform
                                                                                                   // accordingly
        String inputvalues[] = new String[] { a, c, d, e, f };
        boolean x = true, y = true, z = true;
        for (int i = 0; i < inputvalues.length; i++) {
            if (InputisnotEmpty(inputvalues[i]) == false) {
                x = false;
            }

        }

        if (b != null) {
            for (int i = 0; i < b.length; i++) {
                if (isLong(b[i]) == false)
                    z = false;
            }
        } else {
            x = false;
        }

        if ((x == false) || (z == false) || (dobcheck(d) == false) || (emailcheck(c) == false)) {
            System.out.println("\nINVALID INPUT ERROR :-");
            y = false;
        }
        if (x == false)
            System.out.println("Some Contact detail fields have been left empty. All fields must be filled up.");
        if (z == false)
            System.out.println(
                    "Field:'Phone Number(s)' can only accept numbers containing a minimum of 10 digits. Please enter data in correct format.");
        if (emailcheck(c) == false)
            System.out.println(
                    "Field:'E-mail address' can only accept valid an E-mail address. Please enter data in correct format.");
        if (dobcheck(d) == false)
            System.out.println(
                    "Field:'Date of Birth' can only accept date in the specified format. Please enter data in correct format.");
        if (y == false)
            System.out.println("\nPlease re-enter Contact's details:-");

        return y;
    }

    // Final Member Methods to be directly used in Main function
    public void ADDContact() // To add a new Contact
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Contact's details:-");
        String tname, phonenumber[], email, dob, contactrelation, address;
        phonenumber = null;
        int numph = 0;
        do {
            System.out.println("Name:");
            tname = sc.nextLine();
            System.out.print("Phone Number(s):\nEnter number of Phone Numbers you want to add to this Contact:");
            numph = sc.nextInt();
            while ((numph < 0)) {
                System.out.println(
                        "Invalid input. Please re-enter number of Phone Numbers you want to add to this Contact:");
                numph = sc.nextInt();
            }
            if (numph > 0) {
                phonenumber = new String[numph];
                for (int i = 0; i < numph; i++) {
                    System.out.print("Phone Number " + (i + 1) + ":");
                    phonenumber[i] = sc.next();
                }
            }

            System.out.println("E-mail address:");
            email = sc.next();
            System.out.println("Date of Birth(DD/MM/YYYY):");
            dob = sc.next();
            sc.nextLine();
            System.out.println("Address:");
            address = sc.nextLine();
            System.out.println("Contact Relation:");
            contactrelation = sc.nextLine();
        } while (InputValidation(tname, phonenumber, email, dob, address, contactrelation) == false);

        long[] longphonenumber = new long[phonenumber.length];
        for (int i = 0; i < phonenumber.length; i++) {
            longphonenumber[i] = Long.parseLong(phonenumber[i]);
        }

        aList.add(new Contact(tname, longphonenumber, email, dob, address, contactrelation));
        System.out.println();
    }

    public Contact SEARCHContact(String searchname) { // Method to Search for a Contact
        listi = aList.listIterator();
        while (listi.hasNext()) {
            Contact searchContact = (Contact) listi.next();
            if (searchContact.name.equals(searchname)) {
                System.out.println("Found Record:-");
                System.out.println("Name:" + searchContact.name + "\nPhone Number(s):");
                for (int i = 0; i < searchContact.phonenumber.length; i++) {
                    System.out.println("    " + searchContact.phonenumber[i]);
                }
                System.out.println("Email:" + searchContact.email + "\nDate of Birth(DD/MM/YYYY):"
                        + searchContact.dob + "\nAddress:" + searchContact.address + "\nContact Relation:"
                        + searchContact.contactrelation);
                return searchContact;
            }
        }
        return null;
    }

    public void EDITContact(Contact searchcontact) { // Method to Edit a Contact's details
        Scanner ac = new Scanner(System.in); // Initialising the Scanner class
        String newname, newphonenumber[], newemail, newdob, newaddress, newcontactrel;
        newphonenumber = new String[searchcontact.phonenumber.length];
        System.out.println("Updating Contact details ...");
        do {
            System.out.println(
                    "User is requested to Enter new details at every step or press 0 to Skip.");
            System.out.println("Enter new Name:");
            newname = ac.nextLine();
            if (newname.equals("0")) {
                newname = searchcontact.name;
            }
            System.out.println(
                    "Enter new Phone Number(s):\nEnter a New number OR Press 0 to Keep Old number OR Press 1 to Delete the number.");
            for (int i = 0, k = i; i < searchcontact.phonenumber.length; i++) {
                System.out
                        .print("Old Phone Number " + ":" + searchcontact.phonenumber[i] + "\nNew Phone Number " + ":");
                newphonenumber[k] = ac.nextLine();
                if (newphonenumber[k].equals("0")) {
                    newphonenumber[k] = Long.toString(searchcontact.phonenumber[i]);
                }
                if (newphonenumber[k].equals("1")) {
                    newphonenumber = deletearrayelement(newphonenumber, k);
                    k--;
                }
            }
            System.out.println("Enter new E-mail address:");
            newemail = ac.next();
            if (newemail.equals("0")) {
                newemail = searchcontact.email;
            }
            System.out.println("Enter new Date of Birth(DD/MM/YYYY):");
            newdob = ac.next();
            if (newdob.equals("0")) {
                newdob = searchcontact.dob;
            }
            System.out.println("Enter new Address:");
            newaddress = ac.nextLine();
            if (newaddress.equals("0")) {
                newaddress = searchcontact.address;
            }
            System.out.println("Enter new Contact Relation:");
            newcontactrel = ac.nextLine();
            if (newcontactrel.equals("0")) {
                newcontactrel = searchcontact.contactrelation;
            }
        } while (InputValidation(newname, newphonenumber, newemail, newdob, newaddress, newcontactrel) == false);
        long[] newlongphonenumber = new long[newphonenumber.length];
        for (int i = 0; i < newphonenumber.length; i++) {
            newlongphonenumber[i] = Long.parseLong(newphonenumber[i]);
        }
        listi.set(new Contact(newname, newlongphonenumber, newemail, newdob, newaddress, newcontactrel));
    }

    public void DELETEContact() { // Method to delete a particular Contact
        boolean quit2 = true;
        Scanner dc = new Scanner(System.in); // Initialising the Scanner class
        do {
            System.out.println("Do you Confirm to Delete this Contact? (Y/N)");
            String ch3 = dc.next(); // Input the user's choice

            switch (ch3) {
                case "Y": // Case where user confirms to delete the selected Contact
                    listi.remove();

                    break;

                case "N": // Case where user confirms not to delete the selected Contact

                    break;

                default: // Default Case where user makes an invalid choice
                    System.out
                            .println("Invalid choice. Please select one of the given options.");
                    quit2 = false;
                    break;
            }
        } while (quit2 == false);

    }

    public void DISPLAYALLContacts() { // Method to display all Contacts
        listi = aList.listIterator();
        while (listi.hasNext()) {
            Contact searchContact = (Contact) listi.next();
            System.out.println("Name:" + searchContact.name + "\nPhone Number(s):");
            for (int i = 0; i < searchContact.phonenumber.length; i++) {
                System.out.println("    " + searchContact.phonenumber[i]);
            }
            System.out.println("Email:" + searchContact.email + "\nDate of Birth(DD/MM/YYYY):"
                    + searchContact.dob + "\nAddress:" + searchContact.address + "\nContact Relation:"
                    + searchContact.contactrelation);
        }
    }

    // Main function
    public static void main(String[] args) throws Exception {

        AddressBook obj = new AddressBook();// Object creation

        // Calling file constructor and creating a new file/referencing to the existing
        // file, and also declaring object input and output streams
        File datafile = new File(
                "./src/ContactDatabase.txt");
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        if (datafile.isFile()) { // To check if file exists already
            BufferedReader bfr = new BufferedReader(new FileReader(datafile));
            if (bfr.readLine() != null) // To check if existing file is not empty
            {
                // if file exists and is not empty, file is read and accepted in the arraylist
                ois = new ObjectInputStream(new FileInputStream(datafile));
                obj.aList = (ArrayList<Contact>) ois.readObject();
                ois.close();
            }
            bfr.close();
        }

        // creating the console based interface
        System.out.println();
        System.out.println(" * * *      A D D R E S S    B O O K    S Y S T E M      * * *");
        System.out.println();
        System.out.println(
                "Welcome to the Address Book System.\nThe user may choose from the following Main Menu of actions. All the information and records enterred by the user is stored in a file named 'ContactDatabase.txt' to ensure that data is not deleted while re-running the programme. This file need not to be created by the user before use as the programme itself creates the file in the current directory.");
        boolean quit = false;
        Scanner main = new Scanner(System.in); // Initialising the Scanner class
        do {

            System.out.println("\nMAIN MENU :-");
            System.out.println("Press 1 to ADD new Contact/Contacts");
            System.out.println("Press 2 to SEARCH for an existing Contact and VIEW/EDIT details");
            System.out.println("Press 3 to DELETE an existing Contact");
            System.out.println("Press 4 to DISPLAY All Contacts");
            System.out.println("Press 5 to EXIT the System");

            System.out.print("Enter your choice:");
            int ch = main.nextInt(); // Input the user's choice
            System.out.println();

            // Switch-case block for navigation
            switch (ch) {

                case 1: // Case where user chooses to add a new Contact
                    Scanner sa = new Scanner(System.in); // Initialising the Scanner class
                    System.out.println("Adding new Contact(s) ...");
                    System.out.print("Enter number of Contact(s) you want to Add at once:");
                    int sno = sa.nextInt();
                    for (int i = 1; i <= sno; i++) {
                        obj.ADDContact();
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(datafile));
                    oos.writeObject(obj.aList);
                    oos.close();
                    System.out.println("Contact record Added successfully ...");
                    break;

                case 2: // Case where user chooses to search for a Contact
                    if (datafile.isFile()) {
                        System.out.print("Enter name to Search:");
                        Scanner ac = new Scanner(System.in); // Initialising the Scanner class
                        String searchname = ac.nextLine(); // Input name to search
                        Contact searchcontact = obj.SEARCHContact(searchname);
                        if (searchcontact != null) {
                            boolean quit1 = false;
                            do {
                                System.out.println();
                                System.out.println("Press 1 to Edit any infromation about this Contact");
                                System.out.println("Press 0 to Get Back to Main Menu");
                                System.out.print("Enter your choice:");
                                int ch2 = ac.nextInt(); // Input the user's choice
                                System.out.println();

                                switch (ch2) {
                                    case 1: // Case where user chooses to edit details of the selected Contact
                                        obj.EDITContact(searchcontact);

                                        oos = new ObjectOutputStream(new FileOutputStream(datafile));
                                        oos.writeObject(obj.aList);
                                        oos.close();
                                        System.out.println("Contact details Updated successfully ...");
                                        quit1 = true;
                                        break;

                                    case 0: // Case where user chooses to not edit details of the selected Contact
                                            // and get back to main menu
                                        quit1 = true;
                                        break;

                                    default: // Default Case where user makes an invalid choice
                                        System.out
                                                .println("Invalid choice. Please select one of the given options.");
                                        break;
                                }
                            } while (quit1 == false);

                        } else {
                            System.out.println("Sorry, Contact record Not Found");
                        }
                    } else {
                        System.out.println("Sorry, File Not Found");
                    }
                    break;

                case 3: // Case where user chooses to delete a particular Contact
                    if (datafile.isFile()) {
                        obj.listi = obj.aList.listIterator();
                        System.out.print("Enter name to Delete:");
                        Scanner dc = new Scanner(System.in); // Initialising the Scanner class
                        String searchname = dc.nextLine(); // Input name to search
                        Contact searchContact = obj.SEARCHContact(searchname);
                        if (searchContact != null) {
                            obj.DELETEContact();
                            oos = new ObjectOutputStream(new FileOutputStream(datafile));
                            oos.writeObject(obj.aList);
                            oos.close();
                            System.out.println("Contact record Deleted successfully ...");
                        } else {
                            System.out.println("Sorry, Contact record Not Found");
                            break;
                        }

                    } else {
                        System.out.println("Sorry, File Not Found");
                    }
                    break;

                case 4: // Case where user chooses to display all Contacts
                    System.out.println("Displaying All Contact records ...");
                    obj.DISPLAYALLContacts();
                    break;

                case 5: // Case where user chooses to exit the system
                    System.out.println("Exiting the system ...");
                    quit = true;
                    break;

                default: // Default Case where user makes an invalid choice
                    System.out.println("Invalid choice. Please select one of the given options.");
                    break;
            }

        } while (quit == false);
        main.close(); // Closing 'main' scanner
    }
}
