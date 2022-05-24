package SIS.Adminstrator;

import SIS.Controller;
import SIS.Model;
import SIS.View;

public class AdministratorController extends Controller{
    private AdministratorModel model;
    private AdministratorView view;

    public void run(){
        view.getView();
    }

    public AdministratorController() {
        model = new AdministratorModel();
        view = new AdministratorView();
    }

    AdministratorController(AdministratorModel m, AdministratorView v) {
        model = m;
        view = v;
    }

    public AdministratorController(String name, String id, String username, String password) {
        model = new AdministratorModel(name,id, username,password);
        view = new AdministratorView();
    }
    public AdministratorView view() {
        return view;
    }

    public AdministratorModel getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = (AdministratorModel) model;
    }

}