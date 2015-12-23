/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes.test2;

import com.fundsaccess.data.Greeting;
import com.fundsaccess.routes.AbstractRouteSource;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
public class Route2Source extends AbstractRouteSource implements Route2Nameing{
    
    private final static String DIRECT_ROUTE2_INPUT1 = "direct:route2_input1";
    private final static String DIRECT_ROUTE2_INPUT2 = "direct:route2_input2";

    @Override
    public void configure() throws Exception {
        super.configure();
        
        rest("/api2").description("TestRoute1")
               .get("/").description("Sends a standard greet").outType(Greeting.class).to(DIRECT_ROUTE2_INPUT1);
        rest("/api2").description("TestRoute1")
               .get("/{name}").description("Sends a custom greeting").outType(Greeting.class).to(DIRECT_ROUTE2_INPUT2);
        
        from(DIRECT_ROUTE2_INPUT1).to(DIRECT_ROUTE2_TRANSFER1);
        from(DIRECT_ROUTE2_INPUT2).to(DIRECT_ROUTE2_TRANSFER2);
    }
}
