import java.util.List;
import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student\n2. View Students\n3. Update Student\n4. Delete Student\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Marks: ");
                    double marks = sc.nextDouble();
                    controller.addStudent(new Student(0, name, dept, marks));
                    System.out.println("Student added.");
                    break;

                case 2:
                    List<Student> students = controller.getAllStudents();
                    System.out.println("ID | Name | Department | Marks");
                    for (Student s : students) {
                        System.out.println(s.getStudentID() + " | " + s.getName() + " | " + s.getDepartment() + " | " + s.getMarks());
                    }
                    break;

                case 3:
                    System.out.print("Student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Department: ");
                    String newDept = sc.nextLine();
                    System.out.print("New Marks: ");
                    double newMarks = sc.nextDouble();
                    controller.updateStudent(new Student(updateId, newName, newDept, newMarks));
                    System.out.println("Student updated.");
                    break;

                case 4:
                    System.out.print("Student ID to delete: ");
                    int delId = sc.nextInt();
                    controller.deleteStudent(delId);
                    System.out.println("Student deleted.");
                    break;

                case 5:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
