package app.controller;

import MVCFramework.annotation.Controller;
import MVCFramework.annotation.GetMapping;
import MVCFramework.annotation.PostMapping;
import MVCFramework.controller.AbstractController;
import app.model.RegisterModel;
import app.service.UserService;
import httpServer.data.response.HttpResponse;
import httpServer.data.response.RedirectResponse;

@Controller
public class UsersController extends AbstractController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public HttpResponse register() {
        return view("register");
    }

    @PostMapping("/users/register")
    public HttpResponse doRegister(RegisterModel model) {
        userService.createUser(model.getUsername(), model.getEmail(), model.getPassword());
        this.signIn(userService.getUser(model.getUsername(), model.getPassword()), model.getUsername());
        return new RedirectResponse("/users/login");
    }

    @GetMapping("/users/login")
    public HttpResponse login() {
        return view("login");
    }

    @PostMapping("/users/login")
    public HttpResponse doLogin(String username, String password) {
        this.signIn(userService.getUser(username, password), username);
        return new RedirectResponse("/");
    }

    @GetMapping("/users/logout")
    public HttpResponse logout() {
        this.signOut(getUserId());
        return new RedirectResponse("/");
    }

}
