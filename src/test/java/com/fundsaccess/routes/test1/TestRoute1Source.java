/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundsaccess.routes.test1;

import com.fundsaccess.camelcdiswaggerissue.CdiCamelTestSupport;
import java.util.Arrays;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author gmixa@hq.branches.fundsaccess.eu
 */
public class TestRoute1Source extends CdiCamelTestSupport{
    
    
    @Test
    public void testHelloStandardGreeting() throws Exception{
         try (CloseableHttpClient result = HttpClients.custom().build();
                CloseableHttpResponse response = result.execute(new HttpGet(SERVER_URL + "/api/"))) {
            assertEquals(200, response.getStatusLine().getStatusCode());
            final String content = convertResponseToString(response);
            assertThat(content, is(not(nullValue())));
            assertThat(content.contains("8444646007"), is(true));
            assertThat(content.contains("70120400"), is(true));
        }
    }
    
    @Test
    public void testHelloCustomGreeting() throws Exception{
         try (CloseableHttpClient result = HttpClients.custom().build();
                CloseableHttpResponse response = result.execute(new HttpGet(SERVER_URL + "/api/"))) {
            assertEquals(200, response.getStatusLine().getStatusCode());
            final String content = convertResponseToString(response);
            assertThat(content, is(not(nullValue())));
            assertThat(content.contains("8444646007"), is(true));
            assertThat(content.contains("70120400"), is(true));
        }
    }
    
}
