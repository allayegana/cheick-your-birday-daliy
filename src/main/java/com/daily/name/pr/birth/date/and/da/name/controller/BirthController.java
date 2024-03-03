package com.daily.name.pr.birth.date.and.da.name.controller;


import com.daily.name.pr.birth.date.and.da.name.model.BirtEntity;
import com.daily.name.pr.birth.date.and.da.name.repo.SendRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class BirthController {


    @Autowired
    private SendRepository repository;

    @GetMapping("index")
    public ModelAndView getBirth(@Valid BirtEntity birtEntity) {
        ModelAndView tv = new ModelAndView("index");
        return tv;
    }


    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView sauve(@Valid BirtEntity birtEntity, BindingResult result, RedirectAttributes attributes) throws ParseException {
        if (result.hasErrors()) {
            return getBirth(birtEntity);
        } else {
            ModelAndView mv = new ModelAndView("redirect:/index");

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(birtEntity.getJour());
            birtEntity.setJour(String.valueOf(date).substring(0, 3));

            if (birtEntity.getJour().equals("Sun")) {
                birtEntity.setJour("SUNDAY");
            } else if (birtEntity.getJour().equals("Sat")) {
                birtEntity.setJour("Saturday");
            } else if (birtEntity.getJour().equals("Fri")) {
                birtEntity.setJour("FRIDAY");
            } else if (birtEntity.getJour().equals("Wed")) {
                birtEntity.setJour("Wednesday");
            } else if (birtEntity.getJour().equals("Tue")) {
                birtEntity.setJour("Tuesday");
            } else if (birtEntity.getJour().equals("Thu")) {
                birtEntity.setJour("Thursday");
            } else{
                birtEntity.setJour("MONDAY");
        }
            repository.save(birtEntity);
            attributes.addFlashAttribute("mensagem","you were born on **" + birtEntity.getJour() + "**");

            return mv;
        }
    }

//    @GetMapping("/invoice")
//    public ModelAndView invoice() {
//        ModelAndView mv = new ModelAndView("invoice");
//       var birtEntity =  repository.findAll();
//        mv.addObject("birtEntity", birtEntity);
//        return mv;
//    }
}


