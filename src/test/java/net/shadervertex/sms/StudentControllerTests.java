package net.shadervertex.sms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.shadervertex.sms.entity.Student;
import net.shadervertex.sms.service.StudentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when; 
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    private static final String END_POINT_PATH = "/students"; // Update the endpoint path

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService; // Mock the StudentService

    @Test
    public void testGetStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
               .andExpect(status().isOk());
    }
    
    @Test
    public void testGetStudentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH + "/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl(END_POINT_PATH)); // Update the expected redirected URL here
    }

   
   

    @Test
    public void testSaveStudent() throws Exception {
        // Mock the service method to return a saved student
        when(studentService.saveStudent(any(Student.class))).thenReturn(new Student());

        // Perform a POST request to the save endpoint with student data
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .param("firstName", "archanaarati")
                .param("lastName", "sandbhorpatil")
                .param("email", "archana.sandbhorsv@gmail.com"))
                .andExpect(status().is3xxRedirection()) // Expecting a redirect
                .andExpect(redirectedUrl("/students")); // Verify the redirect URL
    }
    
    
    
    @Test
    public void testUpdateStudentSuccess() throws Exception {
        // Mock the service method to return the existing student
        Student existingStudent = new Student("John", "Doe", "john.doe@example.com");
        when(studentService.getStudentById(1L)).thenReturn(existingStudent);

        // Perform a POST request to the update endpoint with student data
        mockMvc.perform(MockMvcRequestBuilders.post("/students/1")
                .param("firstName", "Jane")
                .param("lastName", "Doe")
                .param("email", "jane.doe@example.com"))
                .andExpect(status().is3xxRedirection()) // Expecting a redirect
                .andExpect(redirectedUrl("/students")); // Verify the redirect URL

        // Verify that the updateStudent method of StudentService is called with the correct parameters
        verify(studentService).updateStudent(existingStudent);
    }

    @Test
    public void testDeleteNonExistingStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PATH + "/{id}", 999L))
               .andExpect(status().isMethodNotAllowed()); // Expecting 405 Method Not Allowed
    }
    
    




}
