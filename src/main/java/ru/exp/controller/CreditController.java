package ru.exp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.exp.dao.DeptDAO;
import ru.exp.model.Dept;
import ru.exp.model.Person;

import javax.validation.Valid;


@Controller
@RequestMapping("/credit")
public class CreditController {
    private final DeptDAO deptDAO;

    @Autowired
    public CreditController(DeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }

    // all credit
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("credit", deptDAO.index());
        return "listCredit4";
    }

    // one person
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("dept", deptDAO.show(id));
        return "showDept";
    }

    // create new person
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("dept", new Dept());
        return "newCredit5";
    }

    // add in DB
    @PostMapping()
    public String create(@ModelAttribute("dept") @Valid Dept dept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newCredit5";
        }
        deptDAO.save(dept);
        return "redirect:/credit";
    }

    @GetMapping("/{id}/editDept")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("dept", deptDAO.show(id));
        return "editDept";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("dept") @Valid Dept dept, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "credit/{id}/editDept";
        }
        deptDAO.update(id, dept);
        return "redirect:/credit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        deptDAO.delete(id);
        return  "redirect:/credit";
    }
}
