package com.drops.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Configuration
public class SpringValueProcessor implements BeanPostProcessor {
    private final PlaceholderHelper placeholderHelper = new PlaceholderHelper();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("dataController")) {
            Class obj = bean.getClass();
            List<Field> fields = findAllField(obj);
            for (Field field : fields) {
                Value value = field.getAnnotation(Value.class);
                if (value != null) {
                    ///Set<String> keys = placeholderHelper.extractPlaceholderKeys(value.value());
                    //for (String key : keys) {
                        SpringValue springValue = new SpringValue("test.aaa", value.value(), bean, beanName, field, false);
                        SpringValueCacheMap.map.put("test.aaa", springValue);
                    //}
                }
            }
        }
        return bean;
    }

    private List<Field> findAllField(Class clazz) {
        final List<Field> res = new LinkedList<>();
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                res.add(field);
            }
        });
        return res;
    }
}