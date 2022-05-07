package SIS;

public abstract class Model {

    String name;
    String ID;
    String username;
    String password;

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