package com.ainur.controller;

import com.ainur.model.Event;
import com.ainur.model.EventDate;
import com.ainur.model.TheDayEvents;
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
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            return new ResponseEntity(sqlRepository.getTheMonthEvents(eventDate), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }







    @RequestMapping("/add-event")
    @PostMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity <TheMonthEvents> addEvent(HttpServletRequest request) {
        try {
            TheDayEvents events = gson.fromJson(request.getReader(), TheDayEvents.class);
            sqlRepository.addEvent(events);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            return new ResponseEntity(headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }


}