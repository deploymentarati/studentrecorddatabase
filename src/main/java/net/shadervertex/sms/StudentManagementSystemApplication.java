package net.shadervertex.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.shadervertex.sms.entity.Student;
import net.shadervertex.sms.repository.StudentRepository;

@SpringBootApplication
@MapperScan("net.shadervertex.sms.mapper") // Adjust the package to scan for MyBatis mappers
@ComponentScan({"net.shadervertex.sms.repository", "net.shadervertex.sms.service", "net.shadervertex.sms.controller"})
public class StudentManagementSystemApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Ensure StudentRepository is properly initialized and available for autowiring
        if (studentRepository != null) {
            // Create and save sample students
            Student student1 = new Student("Archana", "Sandbhor", "archana.sandbhorsv@gmail.com");
            studentRepository.save(student1);

            Student student2 = new Student("kishor", "dhangar", "kishor.dhangarsv@gmail.com");
            studentRepository.save(student2);

            Student student3 = new Student("yogita", "shidore", "yogita.shidoresv@gmail.com");
            studentRepository.save(student3);
        } else {
            // Handle the case when StudentRepository is not initialized
            System.out.println("StudentRepository is not initialized. Check your configuration.");
        }
    }
}
