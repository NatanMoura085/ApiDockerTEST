package com.docker.ApiDockerTEST.controllers;

import com.docker.ApiDockerTEST.service.CustomMetricService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    // private ElasticsearchOperations elasticsearchOperations;
    //  public HelloWorldController(ElasticsearchOperations elasticsearchOperations) {
    //    this.elasticsearchOperations = elasticsearchOperations;

    //  }

    private CustomMetricService customMetricService;

    public HelloWorldController(CustomMetricService customMetricService) {
        this.customMetricService = customMetricService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/sayHello")
    public String sayHello(Model model) {
        customMetricService.trackCustomMetric();
        // ElasticApm.startTransaction().setName("Natan minha api");
        model.addAttribute("message", "Hello World");
        // elasticsearchOperations.save(model);
        return "index";
    }
}
