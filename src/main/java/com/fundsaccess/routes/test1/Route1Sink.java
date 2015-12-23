/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes.test1;

import com.fundsaccess.data.HelloGreeter;
import com.fundsaccess.routes.AbstractRouteSink;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
@Named
@ApplicationScoped
public class Route1Sink extends AbstractRouteSink implements Route1Nameing{
    
    private final static String METHOD_DEFAULT_GREETING = "defaultGreeting()";
    private final static String METHOD_CUSTOM_GREETING = "customGreeting(${header.name})";
    
    @Inject
    private HelloGreeter greeter;
    
    @Override
    public void configure() throws Exception{
        super.configure();
        from(DIRECT_ROUTE1_TRANSFER1).bean(greeter,METHOD_DEFAULT_GREETING);
        from(DIRECT_ROUTE1_TRANSFER2).bean(greeter,METHOD_CUSTOM_GREETING);
        
    }
    
}
