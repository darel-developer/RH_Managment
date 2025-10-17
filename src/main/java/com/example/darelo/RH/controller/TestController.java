package com.example.darelo.RH.controller;

import org.springframework.ui.Model;
import com.example.darelo.RH.model.TestEntity;
import com.example.darelo.RH.repository.TestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    private final TestRepository repo;

    public TestController(TestRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model){
        TestEntity test = new TestEntity();
        test.setMessage("connexion à la bd réussie");
        repo.save(test);
        model.addAttribute("message", test.getMessage());
        return "index";
    }
}
