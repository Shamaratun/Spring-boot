package com.meme.first.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins= "*")
public class DummyController {
    @GetMapping("/hello")  //API endpoint
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
    @GetMapping("/info")
    public String  info(@RequestParam(name="aa") String my_name,@RequestParam (required = false)String my_fathers_name,
                        @RequestParam(value="bb") int Age)
    {
        return " My name is  "+ my_name +".  and  My fathers name is "+ my_fathers_name +". His age is "+Age;

    }
}
