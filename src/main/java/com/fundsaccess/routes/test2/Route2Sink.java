/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes.test2;

import com.fundsaccess.data.GoodbyeGreeter;
import com.fundsaccess.routes.AbstractRouteSink;
import javax.inject.Inject;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
public class Route2Sink extends AbstractRouteSink implements Route2Nameing {
    
    private final static String METHOD_DEFAULT_GREETING = "defaultGreeting()";
    private final static String METHOD_CUSTOM_GREETING = "customGreeting(${header.name})";
    
    @Inject
    private GoodbyeGreeter greeter;
    
    @Override
    public void configure() throws Exception{
        super.configure();
        from(DIRECT_ROUTE2_TRANSFER1).bean(greeter,METHOD_DEFAULT_GREETING);
        from(DIRECT_ROUTE2_TRANSFER2).bean(greeter,METHOD_CUSTOM_GREETING);
        
    }
    
}
