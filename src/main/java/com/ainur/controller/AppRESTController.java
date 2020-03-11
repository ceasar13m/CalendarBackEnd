package com.ainur.controller;

import com.ainur.model.Event;
import com.ainur.model.EventDate;
import com.ainur.model.TheMonthEvents;
import com.ainur.repository.SQLRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
public class AppRESTController {

    private Gson gson;

    @Autowired
    private SQLRepository sqlRepository;


    public AppRESTController() {
        gson = new Gson();
    }



    @RequestMapping("/get-month-events")
    @GetMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity <TheMonthEvents> getMonthEvents(HttpServletRequest request) {
        try {
            EventDate eventDate = gson.fromJson(request.getReader(), EventDate.class);
            TheMonthEvents list = sqlRepository.getTheMonthEvents(eventDate);


            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
//            return new ResponseEntity(list.getArrayList(), headers, HttpStatus.OK);
            return new ResponseEntity(headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }







    @RequestMapping("/add-event")
    @PostMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity addEvent(HttpServletRequest request) {
        try {
            Event event = gson.fromJson(request.getReader(), Event.class);
            sqlRepository.addEvent(event);


            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            return new ResponseEntity(headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }


}