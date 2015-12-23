/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes.test1;

import com.fundsaccess.data.Greeting;
import com.fundsaccess.routes.AbstractRouteSource;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
public class Route1Source extends AbstractRouteSource implements Route1Nameing {

    private final static String DIRECT_ROUTE1_INPUT1 = "direct:route1_input1";
    private final static String DIRECT_ROUTE1_INPUT2 = "direct:route1_input2";

    @Override
    public void configure() throws Exception {
        super.configure();
        
        rest("/api").description("TestRoute1")
               .get("/").description("Sends a standard greet").outType(Greeting.class).to(DIRECT_ROUTE1_INPUT1);
        rest("/api").description("TestRoute1")
               .get("/{name}").description("Sends a custom greeting").outType(Greeting.class).to(DIRECT_ROUTE1_INPUT2);
        
        from(DIRECT_ROUTE1_INPUT1).to(DIRECT_ROUTE1_TRANSFER1);
        from(DIRECT_ROUTE1_INPUT2).to(DIRECT_ROUTE1_TRANSFER2);
    }

}
