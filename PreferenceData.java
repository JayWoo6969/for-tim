/*
This code, called PreferenceData, is like a big organizer for student-project preferences. It's
kind of like a digital filing cabinet where we can store information about students, their project
choices, and how much they prefer each project. We have methods to add students and projects,
set preferences between them, and get information about them. The cool part is, it can even read
all this data from a external file, making it super handy for managing student-project allocations. So
basically, it helps make sure students get assigned to the projects they like the most, making
everyone happy!
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreferenceData {

    // These variables store information about students, projects, and their preferences
    private final List<Student> students; // List to store student objects
    private final List<Project> projects; // List to store project objects
    private int[][] preferences; // 2D array to store preferences between students and projects

    // This enum helps us keep track of which part of the file we're reading
    private static enum ReadState {
        STUDENT_MODE, // State indicating that we're reading student information
        PROJECT_MODE, // State indicating that we're reading project information
        PREFERENCE_MODE, // State indicating that we're reading preference information
        UNKNOWN; // State indicating an unknown reading mode
    };

    // Constructor initialises lists for students and projects
    // Purpose: Constructor initialises lists for students and projects.
    // Input: None.
    // Output: None.
    public PreferenceData() {
        super();
        this.students = new ArrayList<Student>(); // Initialise an empty list of students
        this.projects = new ArrayList<Project>(); // Initialise an empty list of projects
    }

    // This method adds a student to the list
    // Purpose: Adds a student to the list of students.
    // Input: Student object representing the student to be added.
    // Output: None.
    public void addStudent(Student s) {
        this.students.add(s); // Add the provided student object to the list of students
    }

    // Overloaded method to add a student using a string
    // Purpose: Overloaded method to add a student using a string.
    // Input: String representing the student's information.
    // Output: None.
    public void addStudent(String s) {
        this.addStudent(Student.createStudent(s)); // Create a new student object from the provided string and add it to the list of students
    }

    // This method adds a project to the list
    // Purpose: Adds a project to the list of projects.
    // Input: Project object representing the project to be added.
    // Output: None.
    public void addProject(Project p) {
        this.projects.add(p); // Add the provided project object to the list of projects
    }

    // Overloaded method to add a project using a string
    // Purpose: Overloaded method to add a project using a string.
    // Input: String representing the project's information.
    // Output: None.
    public void addProject(String p) {
        this.addProject(Project.createProject(p)); // Create a new project object from the provided string and add it to the list of projects
    }

    // This method creates a matrix to store student-project preferences
    // Purpose: Creates a matrix to store student-project preferences.
    // Input: None.
    // Output: None.
    public void createPreferenceMatrix() {
        this.preferences = new int[this.students.size()][this.projects.size()]; // Initialize the preference matrix with the appropriate dimensions based on the number of students and projects
    }

    // This method sets a preference for a student and project
    // Purpose: Sets a preference for a student and project.
    // Input: Student object, Project object, and integer preference value.
    // Output: None.
    public void setPreference(Student s, Project p, int preference) {
        this.preferences[this.students.indexOf(s)][this.projects.indexOf(p)] = preference; // Set the preference value in the preference matrix at the corresponding student and project indices
    }

    // This method sets a preference at a specific row and column in the preference matrix
    // Purpose: Sets a preference at a specific row and column in the preference matrix.
    // Input: Row index, column index, and integer preference value.
    // Output: None.
    public void setPreference(int row, int column, int preference) {
        this.preferences[row][column] = preference; // Set the preference value in the preference matrix at the specified row and column indices
    }

    // This method sets preferences for a specific row in the preference matrix
    // Purpose: Sets preferences for a specific row in the preference matrix.
    // Input: Row index and array of preference values.
    // Output: None.
    public void setPreferenceRow(int row, String[] prefValues) {
        for (int j = 0; j < prefValues.length; j++) {
            this.preferences[row][j] = Integer.parseInt(prefValues[j]); // Parse and set the preference values in the specified row of the preference matrix
        }
    }

    // These methods provide access to the students, projects, and preferences
    // Purpose: Retrieves the list of students.
    // Input: None.
    // Output: List<Student> containing students' information.
    public List<Student> getStudents() {
        return students; // Return the list of students
    }

    // Purpose: Retrieves the list of projects.
    // Input: None.
    // Output: List<Project> containing projects' information.
    public List<Project> getProjects() {
        return projects; // Return the list of projects
    }

    // Purpose: Retrieves the preference matrix.
    // Input: None.
    // Output: 2D array containing preferences between students and projects.
    public int[][] getPreferences() {
        return preferences; // Return the preference matrix
    }

    // This method creates a string representation of the PreferenceData object
    // Purpose: Creates a string representation of the PreferenceData object.
    // Input: None.
    // Output: String representation of the object.
    @Override
    public String toString() {
        return "PreferenceData [students=" + students + ", projects=" + projects + ", preferences="
                + Arrays.toString(preferences) + "]"; // Return a string representation containing information about the students, projects, and preferences
    }

    // This method reads preference data from a file and creates a PreferenceData object
    // Purpose: Reads preference data from a file and creates a PreferenceData object.
    // Input: String representing the file path.
    // Output: PreferenceData object containing preference data.
    static PreferenceData readData(String inputFile) {

        PreferenceData prefs = new PreferenceData(); // Create a new PreferenceData object to store the preference data
        
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            
            ReadState state = ReadState.UNKNOWN; // Initialize the reading state as unknown
            int row = 0; // Initialize the row index for setting preferences in the matrix

            // Read lines from the file until the end using a while loop
            while (reader.ready()) {
                String line = reader.readLine(); // Read the next line from the file
                switch (line.trim()) {
                    case "Students:":
                        state = ReadState.STUDENT_MODE; // Set the reading state to indicate that we're reading student information
                        break;
                    case "Projects:":
                        state = ReadState.PROJECT_MODE; // Set the reading state to indicate that we're reading project information
                        break;
                    case "Preferences:":
                        prefs.createPreferenceMatrix(); // Create the preference matrix
                        state = ReadState.PREFERENCE_MODE; // Set the reading state to indicate that we're reading preference information
                        break;
                    default:
                        switch (state) {
                            case STUDENT_MODE:
                                prefs.addStudent(line); // Add the student information to the list of students
                                break;
                            case PROJECT_MODE:
                                prefs.addProject(line); // Add the project information to the list of projects
                                break;
                            case PREFERENCE_MODE:
                                prefs.setPreferenceRow(row, line.split(",")); // Set the preferences for the current row in the preference matrix
                                row++; // Move to the next row
                                break;
                            default:
                                throw new PreferenceFormatException(line); // Throw an exception for an unknown reading state
                        }
                }

            }

            // Close the reader
            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error opening preferences file. File does not exist as specified.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading from file.");
            e.printStackTrace();
        } catch (PreferenceFormatException e) {
            System.err.println("Preference file in incorrect format. I can't tell which section I'm in.");
            System.err.println("Line being read: " + e.getCurrentLine());
            e.printStackTrace();
        }

        return prefs; // Return the PreferenceData object containing the preference data
    }

    // This method gets a preference at a specific row and column in the preference matrix
    // Purpose: Retrieves a preference at a specific row and column in the preference matrix.
    // Input: Row index and column index.
    // Output: Integer preference value.
    public int getPreference(int i, int j) {
        return this.preferences[i][j]; // Return the preference value at the specified row and column indices
    }
    
    // This method gets the number of students
    // Purpose: Retrieves the number of students.
    // Input: None.
    // Output: Integer representing the number of students.
    public int numStudents() {
        return this.students.size(); // Return the number of students in the list
    }
    
    // This method gets the number of projects
    // Purpose: Retrieves the number of projects.
    // Input: None.
    // Output: Integer representing the number of projects.
    public int numProjects() {
        return this.projects.size(); // Return the number of projects in the list
    }
}

