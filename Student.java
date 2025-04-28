/* 
	This Java class, Student, seems to be a tool for keeping student information organised in a
	program. It's like a form where you can fill in details like the student's email, name, ID number, and
	session. When you want to add a new student, you use a special method to make sure all the info 
	gets filled in correctly. There's also a way to turn a student into a text format so you can easily see
	all their details. And if you need to find out just one piece of information about a student, like their
	email, there are methods to help you do that quickly. Basically, this class helps keep student data
	tidy and easy to work with in the program. 
*/

public class Student {

	// Attributes of a student
    private final String email; // Student's email address
    private final String name; // Student's name
    private final String studentNumber; // Student's identification number
    private final String session; // Student's session information

    // Constructor to create a Student object with specified attributes
    // Business Purpose: To initialize a new Student object with essential attributes for student management.
    // Input: The student's email, name, identification number, and session information.
    // Output: A new Student object with the specified attributes.
    private Student(String email, String name, String studentNumber, String session) {
        super(); // Call the constructor of the superclass (Object)
        this.email = email; // Initialise the email attribute
        this.name = name; // Initialise the name attribute
        this.studentNumber = studentNumber; // Initialise the studentNumber attribute
        this.session = session; // Initialise the session attribute
	}
	
	// Factory method to create a Student object with specified attributes
    // Business Purpose: To create a new Student object with essential details.
    // Input: The student's email, name, identification number, and session information.
    // Output: A new Student object with the specified attributes.
    public static Student createStudent(String email, String name, String studentNumber, String session) {
        return new Student(email, name, studentNumber, session); // Create and return a new Student object

	}
	
	// Factory method to create a Student object from a string description
    // Business Purpose: To create a new Student object from a string representation of student details.
    // Input: A string description containing the student's details separated by commas.
    // Output: A new Student object with the specified attributes parsed from the description.
    public static Student createStudent(String desc) {
        return createStudent(desc.split(",")); // Split the description string into parts and create a Student object

	}
	
	// Factory method to create a Student object from an array of string parts
    // Business Purpose: To create a new Student object from an array of string parts representing student details.
    // Input: An array of string parts containing the student's details.
    // Output: A new Student object with the specified attributes extracted from the parts array.
    public static Student createStudent(String[] parts) {
        // Extract attributes from the parts array
        String email = parts[0].trim(); // Trim removes leading and trailing whitespace
        String name = parts[1].trim();
        String studentNumber = parts[2].trim();
        String session = parts[3].trim();

        // Create and return a new Student object using the extracted attributes
        return createStudent(email, name, studentNumber, session);
	}

	// Method to get a string representation of the Student object
    // Business Purpose: To generate a descriptive string representation of a Student object.
    // Input: None.
    // Output: A string containing the email, name, studentNumber, and session of the Student object.
    @Override
    public String toString() {
        return "Student [email=" + email + ", name=" + name + ", studentNumber=" + studentNumber + ", session="
                + session + "]"; // Return a string containing the attributes of the Student object
	}

	// Getter method to retrieve the email attribute
    // Business Purpose: To retrieve the email address of a Student.
    public String getEmail() {
        return email; // Return the email attribute
	}

	// Getter method to retrieve the name attribute
    // Business Purpose: To retrieve the name of a Student.
    public String getName() {
        return name; // Return the name attribute
	}

	// Getter method to retrieve the studentNumber attribute
    // Business Purpose: To retrieve the identification number of a Student.
    public String getStudentNumber() {
        return studentNumber; // Return the studentNumber attribute
	}

	// Getter method to retrieve the session attribute
    // Business Purpose: To retrieve session information of a Student.
    public String getSession() {
        return session; // Return the session attribute
	}
	
	
	
}

