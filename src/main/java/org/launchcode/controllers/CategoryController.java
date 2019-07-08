package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {
  @Autowired
  private CategoryDao categoryDao;

  @RequestMapping(value = "")
  public String index(Model model) {

    model.addAttribute("allcategories", categoryDao.findAll());
    model.addAttribute("title", "Categories");

    return "category/index";
  }

  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String add(Model model) {

    Category newcat = new Category();

    model.addAttribute("category", newcat);
    model.addAttribute("title", "Add Category");

    return "category/add";
  }

  @RequestMapping(value = "add", method = RequestMethod.POST)
  public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {

    if (errors.hasErrors()) {
      //model.addAttribute("title", "Add Cheese");
      /////////////////////////////////
      //model.addAttribute("categories", categoryDao.findAll());
      //////////////////////////////////

      return "category/add";
    }
    else {
      categoryDao.save(category);
      return "redirect:";
    }
  }
}
