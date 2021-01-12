package switch2020.project.controllers;

import switch2020.project.model.Application;
import switch2020.project.model.CategoryMap;
import switch2020.project.model.StandardCategory;
import switch2020.project.services.CategoryService;

import java.util.List;


public class GetStandardCategoriesTreeController {
    private Application App;


    public GetStandardCategoriesTreeController(Application app) {
        this.App = app;
    }

    public boolean getStandardCategoriesList() {
        CategoryService categoryService = this.App.getCategoryService();
        try {
            CategoryMap standardCategory = categoryService.getStandardCategoriesDTOList();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
