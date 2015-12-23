/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes;

import javax.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
public class AbstractRouteSource extends RouteBuilder{
    
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Inject
    private DefaultExceptionHandler defaultExceptionHandler;

    @Override
    public void configure() throws Exception {
        onException(RuntimeException.class).handled(true)
                .process(exchange -> {
                    defaultExceptionHandler.handle(501, "Front Exception", exchange, log);
                });
    }
    
}
