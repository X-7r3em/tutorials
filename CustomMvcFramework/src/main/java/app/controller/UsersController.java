package app.controller;

import MVCFramework.annotation.Controller;
import MVCFramework.annotation.GetMapping;
import MVCFramework.annotation.PostMapping;
import MVCFramework.controller.AbstractController;
import app.service.UserService;
import app.service.UserServiceImpl;
import httpServer.data.response.HttpResponse;
import httpServer.data.response.RedirectResponse;

@Controller
public class UsersController extends AbstractController {
    private UserService userService;

    public UsersController() {
        this.userService = new UserServiceImpl();
    }

    @GetMapping("/users/register")
    public HttpResponse register() {
        return view("register");
    }

    @PostMapping("/users/register")
    public HttpResponse doRegister() {
        String username = getHttpRequest().getParameters()
                .stream().filter(p -> p.getName().equals("username"))
                .findFirst().get().getValue();
        String password = getHttpRequest().getParameters()
                .stream().filter(p -> p.getName().equals("password"))
                .findFirst().get().getValue();
        String email = getHttpRequest().getParameters()
                .stream().filter(p -> p.getName().equals("email"))
                .findFirst().get().getValue();
        userService.createUser(username, email, password);
        this.signIn(userService.getUser(username, password), username);
        return new RedirectResponse("/users/login");
    }

    @GetMapping("/users/login")
    public HttpResponse login() {
        return view("login");
    }

    @PostMapping("/users/login")
    public HttpResponse doLogin() {
        String username = getHttpRequest().getParameters()
                .stream().filter(p -> p.getName().equals("username"))
                .findFirst().get().getValue();
        String password = getHttpRequest().getParameters()
                .stream().filter(p -> p.getName().equals("password"))
                .findFirst().get().getValue();
        this.signIn(userService.getUser(username, password), username);
        return new RedirectResponse("/");
    }

    @GetMapping("/users/logout")
    public HttpResponse logout() {
        this.signOut(getUserId());
        return new RedirectResponse("/");
    }

}
