/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.extension.internal.metadata.extension;

import org.mule.module.extension.internal.metadata.extension.MetadataOperationsParent;
import org.mule.runtime.api.temporary.MuleMessage;
import org.mule.runtime.extension.api.annotation.metadata.Content;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.MetadataScope;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.introspection.metadata.NullMetadataResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestContentAndOutputResolverWithKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestContentAndOutputResolverWithoutKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestContentResolverWithKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestContentResolverWithoutKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestMultiLevelKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestMultiLevelPartialKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestOutputResolverWithKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestOutputResolverWithoutKeyResolver;
import org.mule.runtime.module.extension.internal.metadata.extension.resolver.TestResolverWithCache;

@MetadataScope(keysResolver = TestContentAndOutputResolverWithKeyResolver.class,
        contentResolver = TestContentAndOutputResolverWithKeyResolver.class,
        outputResolver = TestContentAndOutputResolverWithKeyResolver.class)
public class MetadataOperations extends MetadataOperationsParent
{

    @MetadataScope(keysResolver = TestContentResolverWithKeyResolver.class, contentResolver = TestContentResolverWithKeyResolver.class)
    public Object contentMetadataWithKeyId(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
        return null;
    }

    @MetadataScope(keysResolver = TestOutputResolverWithKeyResolver.class, outputResolver = TestOutputResolverWithKeyResolver.class)
    public Object outputMetadataWithKeyId(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
        return null;
    }

    @MetadataScope(keysResolver = TestContentAndOutputResolverWithKeyResolver.class, contentResolver = TestContentAndOutputResolverWithKeyResolver.class, outputResolver = TestContentAndOutputResolverWithKeyResolver.class)
    public Object contentAndOutputMetadataWithKeyId(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
        return null;
    }

    @MetadataScope(keysResolver = TestContentAndOutputResolverWithKeyResolver.class, contentResolver = TestContentAndOutputResolverWithKeyResolver.class, outputResolver = TestContentAndOutputResolverWithKeyResolver.class)
    public Object outputOnlyWithoutContentParam(@Connection MetadataConnection connection, @MetadataKeyId String type)
    {
        return null;
    }

    @MetadataScope(keysResolver = TestContentAndOutputResolverWithKeyResolver.class, contentResolver = TestContentAndOutputResolverWithKeyResolver.class, outputResolver = TestContentAndOutputResolverWithKeyResolver.class)
    public void contentOnlyIgnoresOutput(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
    }

    @MetadataScope(contentResolver = TestContentResolverWithoutKeyResolver.class)
    public Object contentMetadataWithoutKeyId(@Connection MetadataConnection connection, @Content Object content)
    {
        return null;
    }

    @MetadataScope(outputResolver = TestOutputResolverWithoutKeyResolver.class)
    public Object outputMetadataWithoutKeyId(@Connection MetadataConnection connection, @Content Object content)
    {
        return null;
    }

    @MetadataScope(contentResolver = TestContentAndOutputResolverWithoutKeyResolver.class, outputResolver = TestContentAndOutputResolverWithoutKeyResolver.class)
    public Object contentAndOutputMetadataWithoutKeyId(@Connection MetadataConnection connection, @Content Object content)
    {
        return null;
    }

    @MetadataScope(contentResolver = TestContentResolverWithoutKeyResolver.class)
    public void contentMetadataWithoutKeysWithKeyId(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
    }

    @MetadataScope(outputResolver = TestOutputResolverWithoutKeyResolver.class)
    public Object outputMetadataWithoutKeysWithKeyId(@Connection MetadataConnection connection, @MetadataKeyId String type)
    {
        return null;
    }

    @MetadataScope(outputResolver = TestResolverWithCache.class, contentResolver = TestResolverWithCache.class)
    public Object contentAndOutputCacheResolver(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
        return null;
    }

    public Object shouldInheritOperationResolvers(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
        return null;
    }

    @MetadataScope(contentResolver = TestResolverWithCache.class)
    public Object contentOnlyCacheResolver(@Connection MetadataConnection connection, @MetadataKeyId String type, @Content Object content)
    {
        return null;
    }

    @MetadataScope(keysResolver = TestResolverWithCache.class, outputResolver = TestResolverWithCache.class)
    public Object outputAndMetadataKeyCacheResolver(@Connection MetadataConnection connection, @MetadataKeyId String type)
    {
        return null;
    }

    @MetadataScope(keysResolver = TestMultiLevelKeyResolver.class, contentResolver = TestMultiLevelKeyResolver.class)
    public Object simpleMultiLevelKeyResolver(@Connection MetadataConnection connection, @MetadataKeyId LocationKey locationKey, @Content Object content)
    {
        return null;
    }

    @MetadataScope(keysResolver = TestMultiLevelPartialKeyResolver.class, contentResolver = TestMultiLevelKeyResolver.class)
    public String partialMultiLevelKeyResolver(@Connection MetadataConnection connection, @MetadataKeyId LocationKey locationKey, @Content Object content)
    {
        return String.format("%s|%s|%s", locationKey.getContinent(), locationKey.getCountry(), locationKey.getCity()) ;
    }

    @MetadataScope(outputResolver = NullMetadataResolver.class)
    public MuleMessage messageAttributesNullTypeMetadata()
    {
        return null;
    }

    @MetadataScope(outputResolver = NullMetadataResolver.class)
    public MuleMessage<Object, String> messageAttributesPersonTypeMetadata()
    {
        return null;
    }
}