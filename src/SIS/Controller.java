package SIS;

public abstract class Controller implements Runnable{
    private boolean isAdmin;
    abstract public View view();

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    abstract public Model getModel();
    abstract public void setModel();
}
