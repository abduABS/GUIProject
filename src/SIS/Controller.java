package SIS;

public abstract class Controller extends Thread{
    abstract  public View view();

    abstract public Model getModel();
    abstract public void setModel();
}
