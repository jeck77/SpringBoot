package com.edu.scan.filter;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);

        assertThat(beanA).isNotNull();

        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    // FilterType 옵션 5가지
    // 1. Annotation        : 기본값, 애노테이션을 인식해서 동작한다
    // 2. Assignable_type   : 지정한 타입과 자식 타입을 인식해서 동작한다.
    // 3. Aspectj           : Aspectj 패턴 사용
    // 4. Regex             : 정규 표현식
    // 5. Custom            : TypeFilter 이라는 인터페이스를 구현해서 처리
    // Component면 충분하기 떄문에[ includeFilters를 사용할 일은 거의 없다
    // excludeFilters는 여러가지 이유로 간혹 사용하긴 하지만 자주 사용하진 않는다.
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
