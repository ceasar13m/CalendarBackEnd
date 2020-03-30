package com.ainur.controller;

import com.ainur.model.*;
import com.ainur.repository.SQLRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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


    /**
     *
     * @param request
     * @return events
     */
    @RequestMapping("/get-events")
    @GetMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity <Events> getMonthEvents(HttpServletRequest request) {
        try {
            Date date = new Date();
            date.setDate(new java.util.Date(gson.fromJson(request.getReader(), LongDate.class).getDate()));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            return new ResponseEntity(sqlRepository.getEvents(date), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }



    @RequestMapping("/get-events-counts")
    @GetMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity <Counts> getMonthCounts(HttpServletRequest request) {
        try {
            Date date = new Date();
            date.setDate(new java.util.Date(gson.fromJson(request.getReader(), LongDate.class).getDate()));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            return new ResponseEntity(sqlRepository.getEventsCounts(date), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }


    /**
     *
     * @param request
     * @return HttpStatus
     */

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


    /**
     *
     * @param request
     * @return HttpStatus
     */
    @RequestMapping("/delete-event")
    @PostMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity deleteEvent(HttpServletRequest request) {
        try {
            Event event = gson.fromJson(request.getReader(), Event.class);
            sqlRepository.deleteEvent(event);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            return new ResponseEntity(headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }


}