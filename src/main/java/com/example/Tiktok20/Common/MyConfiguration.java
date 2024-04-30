package com.example.Tiktok20.Common;

import com.example.Tiktok20.DAO.StudentDAOImpl;
import com.example.Tiktok20.Database.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Scanner;

@Configuration
public class MyConfiguration {

    @Bean
    @Autowired
    public CommandLineRunner getRunner(StudentDAOImpl studentDAO){
        return runner -> {
            Scanner sc = new Scanner(System.in);
            //noinspection InfiniteLoopStatement
            while (true){
                printMenu();
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1){
                    // Gọi phương thức thêm sinh viên
                    addStudent(studentDAO);

                } else if (choice == 2){
                    // Tìm sinh viên
                    findStudent(studentDAO);

                } else if (choice == 3){
                    findStudentByName(studentDAO);

                } else if (choice == 4){
                    printAllStudent(studentDAO);

                } else if (choice == 5) {
                    updateStudentById(studentDAO);

                } else if (choice == 6) {
                    updateStudentByName(studentDAO);

                }

            }
        };
    }

    private void updateStudentByName(StudentDAOImpl studentDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        int rows = studentDAO.updateAllName(name);
        System.out.println("It have all " + name + " been updated. ");
    }



    private void updateStudentById(StudentDAOImpl studentDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Student student = studentDAO.getById(id);
        if (student == null){
            System.out.println("Not find a student ");
        } else {
           // Đoạn code lấy ra thông tin mới
            System.out.println("Enter new name: ");
            String name = sc.nextLine();
            System.out.println("Enter new age: ");
            Integer age = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter new email: ");
            String email = sc.nextLine();
            student.setName(name);
            student.setAge(age);
            student.setEmail(email);
            studentDAO.update(student);
        }
    }

    private void printAllStudent(StudentDAOImpl studentDAO) {
        List<Student> students = studentDAO.getAll();

        students.forEach(System.out::println
        );
    }

    private void findStudentByName(StudentDAOImpl studentDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        List<Student> students = studentDAO.getByName(name);
        if (students.isEmpty()){
            System.out.println("Not find a student by this name: ");
        }
         else students.forEach(
                System.out::println
        );
    }

    private void findStudent(StudentDAOImpl studentDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = sc.nextInt();
         Student student = studentDAO.getById(id);
        if (student == null){
            System.out.println("Not find a student: ");
        } else {
            System.out.println(student);
        }

    }


    public void addStudent(StudentDAOImpl studentDAO){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter age: ");
        Integer age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        Student student = new Student(name, age, email);

        studentDAO.save(student);
    }




    public void printMenu(){
        System.out.println("=======================================");
        System.out.println(
                """
                        MENU:
                        1. Thêm sinh viên
                        2. Tìm sinh viên
                        3. Tìm kiếm sinh viên theo tên
                        4. Hiển thị tất cả sinh viên
                        5. Cập nhật sinh viên dựa trên id
                        6.Cập nhật toàn bộ tên của sinh viên\s
                         Lựa chọn:
                        """
        );
    }

}
