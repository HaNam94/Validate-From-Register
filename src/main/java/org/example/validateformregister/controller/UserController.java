package org.example.validateformregister.controller;

import org.example.validateformregister.model.User;
import org.example.validateformregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController {
    // Constants
    private final String EL_NAME = "user";
    private final String LIST_PAGE = EL_NAME + "/list";
    private final String CREATE_PAGE = EL_NAME + "/create";
    private final String EDIT_PAGE = EL_NAME + "/edit";
    private final String DELETE_PAGE = EL_NAME + "/delete";
    private final String VIEW_PAGE = EL_NAME + "/view";
    private final String REDIRECT_TO_LIST = "redirect:/" + EL_NAME;

    private final String LIST_MSG = "Total of " + EL_NAME + ": ";
    private final String CREATE_MSG = "Create new " + EL_NAME + " successfully.";
    private final String EDIT_MSG = "Update the " + EL_NAME + " successfully.";
    private final String DELETE_MSG = "Delete the " + EL_NAME + " successfully.";
    private final String DELETE_MANY_MSG = "Delete the selected " + EL_NAME + "(s) successfully.";

    @Qualifier("userServiceImplement")
    @Autowired
    UserService userService;

    @ModelAttribute("link")
    private String link() {
        return "/user/create";
    }

    @GetMapping
    public ModelAndView getListPage() {
        ModelAndView modelAndView = new ModelAndView(LIST_PAGE);
        modelAndView.addObject("eList", userService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreatePage() {
        ModelAndView modelAndView = new ModelAndView(CREATE_PAGE);
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView getCreated(@Validated @ModelAttribute User user, BindingResult bindingResult) {
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("/user/create");
        } else {
            ModelAndView modelAndView = new ModelAndView(LIST_PAGE);
            userService.save(user);
            modelAndView.addObject("eList", userService.findAll());
            modelAndView.addObject("msg", CREATE_MSG);
            return modelAndView;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView(EDIT_PAGE);
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView getEdited(@Validated @ModelAttribute User user, BindingResult bindingResult) {
        new User().validate(user, bindingResult);
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView(EDIT_PAGE);
        } else {
            userService.save(user);
            ModelAndView modelAndView = new ModelAndView(LIST_PAGE);
            modelAndView.addObject("eList", userService.findAll());
            modelAndView.addObject("msg", EDIT_MSG);
            return modelAndView;
        }
    }

    @GetMapping("/view/{id}")
    public ModelAndView getViewPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView(VIEW_PAGE);
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView getDeletePage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView(DELETE_PAGE);
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView getDeleted(@ModelAttribute User user) {
        userService.remove(user.getId());
        ModelAndView modelAndView = new ModelAndView(LIST_PAGE);
        modelAndView.addObject("eList", userService.findAll());
        modelAndView.addObject("msg", DELETE_MSG);
        return modelAndView;
    }

}
