package com.tsapov.bookkeeping.web;

import com.tsapov.bookkeeping.entity.Project;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bk")
public class BookkeepingController {

    @GetMapping
    public String mainPage(){
        return "/bookkeeping";
    }
}
