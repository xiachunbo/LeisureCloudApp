package com.drops.config;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public class SpringValueCacheMap {
    public static final Multimap<String, SpringValue> map = LinkedListMultimap.create();
}