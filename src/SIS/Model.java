package SIS;

public abstract class Model {

    private String name;
    private String ID;
    private String username;
    private String password;

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

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ("Name: " + name + "; ID: " + ID + "; username: " + username
         + "; password: "+ password);
    }
}