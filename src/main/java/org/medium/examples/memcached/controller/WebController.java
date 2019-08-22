package org.medium.examples.memcached.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;

import org.medium.examples.memcached.entity.Person;
import org.medium.examples.memcached.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("person")
public class WebController {

    @Autowired
    private PersonService personService;

    @GetMapping()
    @Throttling(type = ThrottlingType.RemoteAddr, limit = 10, timeUnit = TimeUnit.SECONDS)
    public Person getPerson(@RequestParam String id) {

        Date startTime = Calendar.getInstance().getTime();
        Person person = personService.getPerson(id);
        Date endTime = Calendar.getInstance().getTime();
        log.info(
            "Time taken for the request: " + (endTime.getTime() - startTime.getTime()) + "ms");

        return person;
    }

}
