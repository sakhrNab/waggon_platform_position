package de.db.waggons_platform_case_study.config;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * This class is a custom implementation of Spring's KeyGenerator interface.
 * It is used to generate unique cache keys for Spring's Cacheable annotation.
 *
 * The generated keys follow the pattern: {SimpleClassName}.{MethodName}{ParamValues},
 * where SimpleClassName is the name of the class whose method is being cached,
 * MethodName is the name of the method being cached, and ParamValues are the
 * string representations of the parameters passed to the method.
 *
 * For array parameters, it uses Arrays.deepToString to generate a string representation.
 * For other objects, it uses Objects.toString. Null parameters are represented as the
 * string "null".
 *
 * This key generation strategy ensures that the cache key is unique for each unique
 * combination of a method and its input parameters.
 */

public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder key = new StringBuilder();
        key.append(target.getClass().getSimpleName());
        key.append(".");
        key.append(method.getName());
        for (Object param : params) {
            if (param != null) {
                if (param.getClass().isArray()) {
                    key.append(Arrays.deepToString((Object[]) param));
                } else {
                    key.append(Objects.toString(param, null));
                }
            } else {
                key.append("null");
            }
        }
        return key.toString();
    }
}
