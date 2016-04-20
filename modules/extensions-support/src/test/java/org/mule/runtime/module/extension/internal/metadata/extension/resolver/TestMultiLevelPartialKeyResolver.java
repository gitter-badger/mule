/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.extension.internal.metadata.extension.resolver;

import static org.mule.runtime.api.metadata.MetadataKeyBuilder.newKey;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataKey;
import org.mule.runtime.api.metadata.MetadataResolvingException;

import java.util.Arrays;
import java.util.List;

public class TestMultiLevelPartialKeyResolver extends TestMultiLevelKeyResolver
{

    @Override
    public List<MetadataKey> getMetadataKeys(MetadataContext context) throws MetadataResolvingException, ConnectionException
    {
        MetadataKey america = newKey(AMERICA)
                .withDisplayName(AMERICA)
                .build();

        MetadataKey europe = newKey(EUROPE)
                .withDisplayName(EUROPE)
                .build();
        return Arrays.asList(europe, america);
    }

    @Override
    public MetadataKey getMetadataKeyChildren(MetadataContext context, MetadataKey partial) throws MetadataResolvingException, ConnectionException
    {
        MetadataKey key;
        if (partial.getId().equals(AMERICA))
        {
            key = newKey(AMERICA)
                    .withDisplayName(AMERICA)
                    .withChild(newKey(ARGENTINA)
                                       .withChild(newKey(BUENOS_AIRES))
                                       .withChild(newKey(LA_PLATA)))
                    .withChild(newKey(USA)
                                       .withDisplayName(USA_DISPLAY_NAME)
                                       .withChild(newKey(SAN_FRANCISCO)))
                    .build();
        }
        else
        {
            key = newKey(EUROPE)
                    .withDisplayName(EUROPE)
                    .withChild(newKey(FRANCE)
                                       .withChild(newKey(PARIS)))
                    .build();
        }

        return key;
    }

}
