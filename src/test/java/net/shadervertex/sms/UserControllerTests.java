package net.shadervertex.sms;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import net.shadervertex.sms.controller.UserController;
import net.shadervertex.sms.dto.UserDto;
import net.shadervertex.sms.entity.User;
import net.shadervertex.sms.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testLogin_GET() {
        ModelAndView modelAndView = userController.login();
        assert modelAndView.getViewName().equals("login");
        assert modelAndView.getModel().containsKey("user");
        assert modelAndView.getModel().get("user") instanceof UserDto;
    }

    @Test
    public void testLogin_POST_Successful() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        User mockedUser = new User();
        when(userService.login("testUser", "testPassword")).thenReturn(mockedUser);

        String redirectUrl = userController.login(userDto, bindingResult);
        assert redirectUrl.equals("redirect:/students");
    }

    @Test
    public void testLogin_POST_Failed() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        when(userService.login("testUser", "testPassword")).thenReturn(null);

        String redirectUrl = userController.login(userDto, bindingResult);
        assert redirectUrl.equals("redirect:/login");
    }
}
