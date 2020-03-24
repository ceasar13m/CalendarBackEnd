package com.ainur.controller;

import com.ainur.model.Counts;
import com.ainur.model.Event;
import com.ainur.model.Date;
import com.ainur.model.Events;
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
            Date date = gson.fromJson(request.getReader(), Date.class);
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
            Date date = gson.fromJson(request.getReader(), Date.class);
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