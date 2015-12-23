/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.camelcdiswaggerissue;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.main.Main;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
@Named
public final class Startup extends Main {

    @Inject
    Instance<BeanManager> beanmanager;

    @Override
    public void run() throws Exception {
        try {
            LOG.info(">> Starting Apache Camel...");

            enableHangupSupport();
            final Set<Bean<?>> beans = cdiContainer.getBeanManager().getBeans(RouteBuilder.class);
            final Set<RouteBuilder> routes = new HashSet<>(beans.size());
            beans.stream().forEach((bean) -> {
                routes.add((RouteBuilder) cdiContainer.getBeanManager().getReference(bean, RouteBuilder.class, null));
            });
            routes.stream().forEach(builder -> addRouteBuilder(builder));
            super.run();
        } finally {
            LOG.info(">> Apache Camel successfully started.");
        }
    }

    @Override
    protected CamelContext createContext() {
        CdiCamelContext context = new CdiCamelContext();
        context.setBeanManager(beanmanager);
        context.setUseMDCLogging(true);
        context.setStreamCaching(true);
        return context;
    }

    static CdiContainer cdiContainer;

    public static void main(String... args) throws Exception {
        LOG.info(">> Starting CDI...");
        cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();
        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContexts();
        LOG.info(">> CDI successfully started.");
        Main main = null;
        final Set<Bean<?>> beans = cdiContainer.getBeanManager().getBeans(Startup.class);
        for (final Bean<?> bean : beans) {
            main = (Startup) cdiContainer.getBeanManager().getReference(bean, Startup.class, null);
        }
        instance = main;
        if (main != null) {
            main.enableHangupSupport();
            main.run(args);
        }
    }

}
