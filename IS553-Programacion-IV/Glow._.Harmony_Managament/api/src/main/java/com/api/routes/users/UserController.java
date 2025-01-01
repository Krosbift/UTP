package com.api.routes.users;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.api.routes.shared.models.user.UserModel;
import com.api.routes.users.dto.RegisterUserDto;
import com.api.routes.users.dto.UpdateUserDto;


@RestController
@RequestMapping("users")
@Tag(name = "Usuarios")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("find-user")
  @Operation(summary = "Buscar usuario por email")
  public UserModel findUser(@RequestParam String userEmail) {
    return userService.findUser(userEmail);
  }

  @GetMapping("find-all-users")
  @Operation(summary = "Buscar todos los usuarios")
  public List<UserModel> findAllUsers() {
    return userService.findAllUsers();
  }

  @PostMapping("register")
  @Operation(summary = "Registrar usuario")
  public UserModel registerUser(@RequestBody RegisterUserDto registerData) {
    return userService.registerUser(registerData);
  }

  @PatchMapping("update-user/{userId}")
  @Operation(summary = "Actualizar usuario")
  public UserModel updateUser(@RequestBody UpdateUserDto registerData, @PathVariable int userId) {
    return userService.updateUser(registerData, userId);
  }

  @PatchMapping("activate-user/{userId}")
  @Operation(summary = "Activar usuario")
  public int activateUser(@PathVariable int userId) {
    return userService.activateUser(userId);
  }

  @DeleteMapping("delete-user/{userId}")
  @Operation(summary = "Desactivar usuario")
  public int deleteUser(@PathVariable int userId) {
    return userService.deleteUser(userId);
  }
}
