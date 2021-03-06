package io.company.utils.mocks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to inject mocks during runtime.
 * Mocks are required to be json files.
 * <br>
 * Example: {@code @Mock({"path1", "path2", ...})}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {
    /**
     * Class that represents the mocked data.
     */
    Class<? extends MockDefinition>[] clazz();
}
