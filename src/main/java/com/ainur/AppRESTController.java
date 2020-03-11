package com.ainur;

import com.ainur.model.Date;
import com.ainur.model.MonthEventsList;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
public class AppRESTController {

    private Gson gson;

    public AppRESTController() {
        gson = new Gson();
    }



    @RequestMapping("/get-month-events")
    @PostMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity <MonthEventsList> getMonthEvents(HttpServletRequest request) {
        try {
            Date date = gson.fromJson(request.getReader(), Date.class);
            System.out.println(date.getDate().toString());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");

            SQLRepository sqlRepository = new SQLRepository();




            MonthEventsList list = new MonthEventsList();


            return new ResponseEntity(list.getArrayList(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }


}