/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
@Named
@ApplicationScoped
public class BasicRoute extends RouteBuilder {

  

    @Override
    public void configure() throws Exception {

        restConfiguration()
            .component("netty4-http")
            .host("0.0.0.0")
            .port(9091)
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "false")
            .skipBindingOnErrorCode(false)            
            .apiContextPath("/api-doc")           
            .apiProperty("api.title", "Freetin UserInterface")
            .apiProperty("api.version", "1.0")
                 .apiProperty("schemas","http")
                 .apiProperty("cors", "true");    
    }

}
