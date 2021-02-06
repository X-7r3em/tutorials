package app.service;

public interface UserService {
    void createUser(String username, String email, String password);

    void changePassword(String username, String newPassword);

    String getUser(String username, String password);

    int countUsers();
}
