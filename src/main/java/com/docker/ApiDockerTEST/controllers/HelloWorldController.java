package com.docker.ApiDockerTEST.controllers;

import co.elastic.apm.api.ElasticApm;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    private ElasticsearchOperations elasticsearchOperations;
    public HelloWorldController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;

    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/sayHello")
    public String sayHello(Model model) {
        ElasticApm.startTransaction().setName("Natan minha api");
        model.addAttribute("message", "Hello World");
        elasticsearchOperations.save(model);
        return "index";
    }
}
