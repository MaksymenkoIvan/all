package com.company;

public class AddName implements Action {
    private Model model = new Model();
    @Override
    public void execute() {
        model.add();
    }
}
