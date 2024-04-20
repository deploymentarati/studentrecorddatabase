package net.shadervertex.sms.mapper;

import net.shadervertex.sms.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsernameAndPassword(String username, String password);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUserById(Long id);

    User getUserById(Long id);
}
