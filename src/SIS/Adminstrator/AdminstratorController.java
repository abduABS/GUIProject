package SIS.Adminstrator;

import SIS.Controller;

public class AdminstratorController extends Controller {
    private AdminstratorModel model;
    private AdminstratorView view;

    AdminstratorController() {

    }

    AdminstratorController(AdminstratorModel m, AdminstratorView v) {
        model = m;
        view = v;
    }

    public void getView() {
        view.getView();
    }

    @Override
    public void login() {

    }
}