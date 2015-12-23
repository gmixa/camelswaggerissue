/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
@Named
@ApplicationScoped
public class GoodbyeGreeter {
    
    public Greeting defaultGreeting(){
        return new Greeting("Good bye world!");
    }
    
    public Greeting customGreeting(final String name){
        final String message = "Good bye "+name+"!";
        return new Greeting(message);
    }
    
}
