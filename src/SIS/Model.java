package SIS;

public abstract class Model {

    String name;
    String ID;
    String username;
    String password;

    public Model(){
        name = "Example";
        ID = "x00000000";
        username = "User";
        password = "password";
    }
    public Model(String name, String ID, String username, String password) {
        this.name = name;
        this.ID = ID;
        this.username = username;
        this.password = password;
    }


    public String getName() {
        return name;
    }
    public void setName(String _n) {
        name = _n;
    }
    public String getId() {
        return ID;
    }
    public void setId(String id) {
        ID = id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}