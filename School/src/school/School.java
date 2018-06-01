package school;

public class School {

    public static void main(String[] args) {
        Students jojo = new Students("Jojo", 9, 6, 140, 2000, 8, 10);
        jojo.getStudentInfo();
        
        Students sam = new Students("Sam", 11, 5, 180, 2000, 12, 25);
        sam.getStudentInfo();
        
        System.out.println("The total numer of students are: " + Students.numStudents + " Students");
    }
    
}
