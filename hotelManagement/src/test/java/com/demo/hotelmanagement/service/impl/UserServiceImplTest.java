/*
 * package com.demo.hotelmanagement.service.impl;
 * 
 * import static org.mockito.ArgumentMatchers.any; import static
 * org.mockito.Mockito.verify; import static org.mockito.Mockito.when;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.junit.jupiter.MockitoExtension;
 * 
 * import com.demo.hotelmanagement.dao.UserDao; import
 * com.demo.hotelmanagement.dto.UserDto; import
 * com.demo.hotelmanagement.exception.InvalidCredentialsException; import
 * com.demo.hotelmanagement.model.User;
 * 
 * 
 * 
 * @ExtendWith(MockitoExtension.class) public class UserServiceImplTest {
 * 
 * User user;
 * 
 * @Mock UserDao userDao;
 * 
 * @InjectMocks UserServiceImpl userServiceImpl;
 * 
 * UserDto userDto;
 * 
 * @BeforeEach public void setUp() {
 * 
 * userDto=new UserDto(); userDto.setUserName("manasa");
 * userDto.setPassword("1234"); userDto.setLocation("kadapa");
 * userDto.setContactNumber("7364"); userDto.setAddress("hush");
 * 
 * 
 * }
 * 
 * @Test public void saveUser() { //given
 * when(userDao.save(any(User.class))).thenReturn(any(User.class));
 * 
 * //when userServiceImpl.addUser(userDto);
 * 
 * //then verify(userDao).save(any(User.class));
 * 
 * }
 * 
 * @Test public void authenticateTest1() throws InvalidCredentialsException {
 * //given User user=new User(); user.setUserName("manasa");
 * user.setPassword("1234"); } }
 */