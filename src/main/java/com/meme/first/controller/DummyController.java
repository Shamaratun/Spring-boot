package com.meme.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DummyController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @GetMapping("/bye")
    public String bye(){
        return "chole ja";
    }
    @GetMapping("/square")
public int square(@RequestParam int number)
{
    return number*number;
}
    @GetMapping("/name")
    public String  name(@RequestParam String my_name,@RequestParam String my_fathers_name)
    {
        return " my name is  "+ my_name +"  and  my_fathers_name  "+ my_fathers_name ;

    }
}
