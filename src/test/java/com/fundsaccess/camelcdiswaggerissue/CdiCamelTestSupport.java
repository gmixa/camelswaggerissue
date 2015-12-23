/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.camelcdiswaggerissue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.TypeLiteral;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.BeforeClass;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
public class CdiCamelTestSupport  extends CamelTestSupport {

    protected CdiContainer cdiContainer;

    protected static final String SERVER_URL = "http://localhost:9091/";
  
    @BeforeClass
    public static void init() {       
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();
        final ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContexts();
        CdiCamelContext localContext = new CdiCamelContext();
        Instance<BeanManager> beanmanager = new Instance<BeanManager>() {
            @Override
            public Instance<BeanManager> select(Annotation... qualifiers) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <U extends BeanManager> Instance<U> select(Class<U> subtype, Annotation... qualifiers) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <U extends BeanManager> Instance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isUnsatisfied() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isAmbiguous() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void destroy(BeanManager instance) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Iterator<BeanManager> iterator() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public BeanManager get() {
                    return cdiContainer.getBeanManager();
            }
        };
        localContext.setBeanManager(beanmanager);
        return localContext;
    }

    @Override
    protected RouteBuilder[] createRouteBuilders() {
        final Set<Bean<?>> beans = cdiContainer.getBeanManager().getBeans(RouteBuilder.class);
        final Set<RouteBuilder> routeBuilders = new HashSet<>(beans.size());
        beans.stream().forEach( b -> {        
            RouteBuilder builder = (RouteBuilder) cdiContainer.getBeanManager().getReference(b, RouteBuilder.class, null);
            routeBuilders.add(builder);
        });
        return routeBuilders.toArray(new RouteBuilder[0]);

    }

    @Override    
    public void tearDown() throws Exception {
        super.tearDown();
        cdiContainer.shutdown();
    }
    
    protected final String convertResponseToString(CloseableHttpResponse response) throws IOException {
        String responseString;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity()
                .getContent(), StandardCharsets.UTF_8))) {
            responseString = "";
            String line;
            while ((line = in.readLine()) != null) {
                responseString += line;
            }
        }
        return responseString;
    }

}
