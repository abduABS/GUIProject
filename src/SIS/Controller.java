package SIS;

public abstract class Controller implements Runnable{
    abstract  public View view();

    abstract public Model getModel();
    abstract public void setModel();
}
