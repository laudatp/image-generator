package image.controller;

import image.model.Model;
import image.view.View;

public interface Controller extends Features {

    void go(Model model);

    void setView(View view);

}
