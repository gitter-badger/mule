/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.test.integration.exceptions;

import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;

import java.util.HashMap;
import java.util.Map;

public class ExceptionStrategyMessagePropertiesTestCase extends FunctionalTestCase
{
    int numMessages = 100;
    
    @Override
    protected String getConfigResources()
    {
        return "org/mule/test/integration/exceptions/exception-strategy-message-properties.xml";
    }

    public void testException() throws Exception
    {
        Thread tester1 = new Tester();
        Thread tester2 = new Tester();
        tester1.start();
        tester2.start();
        
        MuleClient client = new MuleClient();
        MuleMessage msg;
        for (int i = 0; i < numMessages; ++i)
        {
            msg = client.request("vm://error", 5000);
            assertNotNull(msg);
            assertEquals("bar", msg.getProperty("foo"));
        }        
    }
    
    class Tester extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                MuleClient client = new MuleClient();
                
                Map props = new HashMap();
                props.put("foo", "bar");
                for (int i = 0; i < numMessages; ++i)
                {
                    client.dispatch("vm://in", "test", props);
                }    
            }
            catch (Exception e)
            {
                fail(e.getMessage());
            }
        }        
    };
}


