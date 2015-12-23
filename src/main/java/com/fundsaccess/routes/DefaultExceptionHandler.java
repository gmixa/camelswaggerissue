/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.apache.camel.Exchange;
import org.slf4j.Logger;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
@Named
@ApplicationScoped
public class DefaultExceptionHandler {

    public void handle(int httpStatus, String reason, Exchange exchange, Logger log) throws Exception {
        final Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        if (log != null) {
            log.info(reason, ex);
        }
        
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, httpStatus);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");

    }
}
