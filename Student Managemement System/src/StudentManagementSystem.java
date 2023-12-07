import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
//All packages imported

public class StudentManagementSystem {

    // Declaring certain member data
    ArrayList<Student> aList = new ArrayList<Student>();
    ListIterator listi = null;

    // Sub methods to be utilised in other methods for various purpose
    public boolean InputisnotEmpty(String x) { // Method to check if any input field is left empty
        if (x.equals("")) {
            return false;
        } else {
            return true;
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

    public boolean InputValidation(String a, String b, String c, String d, String e, String f, String g) { // To use certain methods and check if enterred input is valid or not and perform accordingly
        String inputvalues[] = new String[] { a, b, c, d, e, f, g };
        boolean x = true, y = true;
        String printString = "";
        for (int i = 0; i < inputvalues.length; i++) {
            if (InputisnotEmpty(inputvalues[i]) == false) {
                x = false;
            }

        }

        if (isInteger(b) == false) {
            printString = printString + "'Grade'";
        }
        if (isInteger(c) == false) {
            if (printString != "")
                printString = printString + ",";
            printString = printString + "'Roll Number'";
        }
        if (isInteger(e) == false) {
            if (printString != "")
                printString = printString + ",";
            printString = printString + "'Age'";
        }
        if ((x == false) || (printString != "") || (isLong(f) == false) || (dobcheck(d) == false)) {
            System.out.println("\nINVALID INPUT ERROR :-");
            y = false;
        }
        if (x == false)
            System.out.println("Some Student detail fields have been left empty. All fields must be filled up.");

        if (printString != "")
            System.out.println("Field(s):" + printString
                    + " can only accept numeric characters as input. Please enter data in correct format.");
        if (dobcheck(d) == false)
            System.out.println(
                    "Field:'Date of Birth' can only accept date in the specified format. Please enter data in correct format.");
        if (isLong(f) == false)
            System.out.println(
                    "Field:'Contact Number' can only accept a number containing a minimum of 10 digits. Please enter data in correct format.");
        if (y == false)
            System.out.println("\nPlease re-enter Student's details:-");

        return y;
    }

    // Final Member Methods to be directly used in Main function
    public void ADDstudent() // To add a new student
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student's details:-");
        String tname, gradeinput, rollnumberinput, dob, ageinput, contactnoinput, address;
        do {
            System.out.println("Name:");
            tname = sc.nextLine();
            System.out.println("Grade:");
            gradeinput = sc.nextLine();
            System.out.println("Roll Number:");
            rollnumberinput = sc.nextLine();
            System.out.println("Date of Birth(DD/MM/YYYY):");
            dob = sc.nextLine();
            System.out.println("Age:");
            ageinput = sc.nextLine();
            System.out.println("Contact Number:");
            contactnoinput = sc.nextLine();
            System.out.println("Address:");
            address = sc.nextLine();
        } while (InputValidation(tname, gradeinput, rollnumberinput, dob, ageinput, contactnoinput, address) == false);

        int tgrade = Integer.parseInt(gradeinput);
        int trollnumber = Integer.parseInt(rollnumberinput);
        int age = Integer.parseInt(ageinput);
        long contactno = Long.parseLong(contactnoinput);

        aList.add(new Student(tname, tgrade, trollnumber, dob, age, contactno, address));
        System.out.println();
    }

    public Student SEARCHstudent(String searchname) { // Method to Search for a student
        listi = aList.listIterator();

        while (listi.hasNext()) {
            Student searchstudent = (Student) listi.next();
            if (searchstudent.sname.equals(searchname)) {
                System.out.println("Found Record:-");
                System.out.println("Name:" + searchstudent.sname + "    Grade:" + searchstudent.grade
                        + "   Roll Number:" + searchstudent.rollnumber + "   Date of Birth(DD/MM/YYYY):"
                        + searchstudent.dob + "    Age:" + searchstudent.age + "   Contact Number:"
                        + searchstudent.contactno + "    Address:" + searchstudent.address);
                return searchstudent;
            }
        }
        return null;
    }

    public void EDITstudent(Student searchstudent) { // Method to Edit a student's details
        Scanner ac = new Scanner(System.in); // Initialising the Scanner class
        String newname, newgrade, newrollno, newdob, newage, newcontactno, newaddress;
        System.out.println("Updating Student details ...");
        do {
            System.out.println(
                    "User is requested to Enter new details at every step or press 0 to Skip.");
            System.out.print("Enter new Name:");
            newname = ac.next();
            if (newname.equals("0")) {
                newname = searchstudent.sname;
            }
            System.out.print("Enter new Grade:");
            newgrade = ac.next();
            if (newgrade.equals("0")) {
                newgrade = Integer.toString(searchstudent.grade);
            }
            System.out.print("Enter new Roll Number:");
            newrollno = ac.next();
            if (newrollno.equals("0")) {
                newrollno = Integer.toString(searchstudent.rollnumber);
            }
            System.out.print("Enter new Date of Birth(DD/MM/YYYY):");
            newdob = ac.next();
            if (newdob.equals("0")) {
                newdob = searchstudent.dob;
            }
            System.out.print("Enter new Age:");
            newage = ac.next();
            if (newage.equals("0")) {
                newage = Integer.toString(searchstudent.age);
            }
            System.out.print("Enter new Contact Number:");
            newcontactno = ac.next();
            if (newcontactno.equals("0")) {
                newcontactno = Long.toString(searchstudent.contactno);
            }
            System.out.print("Enter new Address:");
            newaddress = ac.next();
            if (newaddress.equals("0")) {
                newaddress = searchstudent.address;
            }
        } while (InputValidation(newname, newgrade, newrollno, newdob, newage, newcontactno, newaddress) == false);

        listi.set(new Student(newname, Integer.parseInt(newgrade), Integer.parseInt(newrollno), newdob,
                Integer.parseInt(newage),
                Long.parseLong(newcontactno), newaddress));
    }

    public void DELETEstudent() { // Method to delete a particular student
        boolean quit2 = true;
        Scanner dc = new Scanner(System.in); // Initialising the Scanner class
        do {
            System.out.println("Do you Confirm to Delete this Student? (Y/N)");
            String ch3 = dc.next(); // Input the user's choice

            switch (ch3) {
                case "Y": // Case where user confirms to delete the selected student
                    listi.remove();

                    break;

                case "N": // Case where user confirms not to delete the selected student

                    break;

                default: // Default Case where user makes an invalid choice
                    System.out
                            .println("Invalid choice. Please select one of the given options.");
                    quit2 = false;
                    break;
            }
        } while (quit2 == false);

    }

    public void DISPLAYALLstudents() { // Method to display all Students
        listi = aList.listIterator();
        while (listi.hasNext()) {
            Student searchstudent = (Student) listi.next();
            System.out.println("Name:" + searchstudent.sname + "    Grade:" + searchstudent.grade
                    + "   Roll Number:" + searchstudent.rollnumber + "   Date of Birth(DD/MM/YYYY):"
                    + searchstudent.dob + "    Age:" + searchstudent.age + "   Contact Number:"
                    + searchstudent.contactno + "    Address:" + searchstudent.address);
        }
    }

    // Main function
    public static void main(String[] args) throws Exception {

        StudentManagementSystem obj = new StudentManagementSystem();// Object creation

        // Calling file constructor and creating a new file/referencing to the existing
        // file, and also declaring object input and output streams
        File datafile = new File(
                "./src/StudentDatabase.txt");
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        if (datafile.isFile()) { // To check if file exists already
            BufferedReader bfr = new BufferedReader(new FileReader(datafile));
            if (bfr.readLine() != null) // To check if existing file is not empty
            {
                // if file exists and is not empty, file is read and accepted in the arraylist
                ois = new ObjectInputStream(new FileInputStream(datafile));
                obj.aList = (ArrayList<Student>) ois.readObject();
                ois.close();
            }
            bfr.close();
        }

        // creating the console based interface
        System.out.println();
        System.out.println(" * * *      S T U D E N T    M A N A G E M E N T    S Y S T E M      * * *");
        System.out.println();
        System.out.println(
                "Welcome to the Student Management System.\nThe user may choose from the following Main Menu of actions. All the information and records enterred by the user is stored in a file named 'StudentDatabase.txt' to ensure that data is not deleted while re-running the programme. This file need not to be created by the user before use as the programme itself creates the file in the current directory.");
        boolean quit = false;
        Scanner main = new Scanner(System.in); // Initialising the Scanner class
        do {

            System.out.println("\nMAIN MENU :-");
            System.out.println("Press 1 to ADD new Student/Students");
            System.out.println("Press 2 to SEARCH for an existing Student and VIEW/EDIT details");
            System.out.println("Press 3 to DELETE an existing Student");
            System.out.println("Press 4 to DISPLAY All Students");
            System.out.println("Press 5 to EXIT the System");

            System.out.print("Enter your choice:");
            int ch = main.nextInt(); // Input the user's choice
            System.out.println();

            // Switch-case block for navigation
            switch (ch) {

                case 1: // Case where user chooses to add a new student
                    Scanner sa = new Scanner(System.in); // Initialising the Scanner class
                    System.out.println("Adding new Student(s) ...");
                    System.out.print("Enter number of Student(s) you want to Add at once:");
                    int sno = sa.nextInt();
                    for (int i = 1; i <= sno; i++) {
                        obj.ADDstudent();
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(datafile));
                    oos.writeObject(obj.aList);
                    oos.close();
                    System.out.println("Student record Added successfully ...");
                    break;

                case 2: // Case where user chooses to search for a student
                    if (datafile.isFile()) {
                        System.out.print("Enter name to Search:");
                        Scanner ac = new Scanner(System.in); // Initialising the Scanner class
                        String searchname = ac.nextLine(); // Input name to search
                        Student searchstudent = obj.SEARCHstudent(searchname);
                        if (searchstudent != null) {
                            boolean quit1 = false;
                            do {
                                System.out.println();
                                System.out.println("Press 1 to Edit any infromation about this Student");
                                System.out.println("Press 0 to Get Back to Main Menu");
                                System.out.print("Enter your choice:");
                                int ch2 = ac.nextInt(); // Input the user's choice
                                System.out.println();

                                switch (ch2) {
                                    case 1: // Case where user chooses to edit details of the selected student
                                        obj.EDITstudent(searchstudent);

                                        oos = new ObjectOutputStream(new FileOutputStream(datafile));
                                        oos.writeObject(obj.aList);
                                        oos.close();
                                        System.out.println("Student details Updated successfully ...");
                                        quit1 = true;
                                        break;

                                    case 0: // Case where user chooses to not edit details of the selected student
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
                            System.out.println("Sorry, Student record Not Found");
                        }
                    } else {
                        System.out.println("Sorry, File Not Found");
                    }
                    break;

                case 3: // Case where user chooses to delete a particular student
                    if (datafile.isFile()) {
                        obj.listi = obj.aList.listIterator();
                        System.out.print("Enter name to Delete:");
                        Scanner dc = new Scanner(System.in); // Initialising the Scanner class
                        String searchname = dc.nextLine(); // Input name to search
                        Student searchstudent = obj.SEARCHstudent(searchname);
                        if (searchstudent != null) {
                            obj.DELETEstudent();
                            oos = new ObjectOutputStream(new FileOutputStream(datafile));
                            oos.writeObject(obj.aList);
                            oos.close();
                            System.out.println("Student record Deleted successfully ...");
                        } else {
                            System.out.println("Sorry, Student record Not Found");
                            break;
                        }

                    } else {
                        System.out.println("Sorry, File Not Found");
                    }
                    break;

                case 4: // Case where user chooses to display all students
                    System.out.println("Displaying All Student records ...");
                    obj.DISPLAYALLstudents();
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
