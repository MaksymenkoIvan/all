package com.company;

public class EditName implements Action {
    private Model model = new Model();
    @Override
    public void execute() {
        model.edit();
    }
}
