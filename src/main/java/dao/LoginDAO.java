package dao;

public interface LoginDAO {

    public boolean checkUser(String name, String password);

    public void addNewUser(String name, String password);

    public void deleteUser(String name);
}
