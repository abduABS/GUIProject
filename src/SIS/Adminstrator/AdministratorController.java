package SIS.Adminstrator;

import SIS.Controller;
import SIS.Model;
import SIS.View;

public class AdministratorController extends Controller {
    private AdministratorModel model;
    private AdministratorView view;

    public AdministratorController() {
        model = new AdministratorModel();
        view = new AdministratorView();
    }

    AdministratorController(AdministratorModel m, AdministratorView v) {
        model = m;
        view = v;
    }

    public View view() {
        return view;
    }


    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel() {

    }

}