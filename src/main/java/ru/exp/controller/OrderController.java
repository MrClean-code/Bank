package ru.exp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.exp.dao.OfferDAO;
import ru.exp.exception.dao.OfferDAOException;
import ru.exp.model.Offer;


import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OfferDAO offerDAO;

    @Autowired
    public OrderController(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    // all orders
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("order", offerDAO.index());
        return "listOrder6";
    }

    //new offer
    @GetMapping("/new")
    public String newOffer(Model model) {
        model.addAttribute("offer", new Offer());
        return "creditOffer6";
    }

    // add in DB
    @PostMapping()
    public String create(@ModelAttribute("offer")  @Valid Offer offer, BindingResult bindingResult) throws OfferDAOException {
        if (bindingResult.hasErrors()) {
            return "creditOffer6";
        }
        offerDAO.save(offer);
        return "redirect:/order";
    }
}
