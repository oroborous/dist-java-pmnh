package edu.wctc.pmnh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class PmnhApplication implements WebMvcConfigurer {
    // WebMvcConfigurer interface contains callback methods that
    // can be overridden to customize the default behavior of
    // Spring MVC
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html

    public static void main(String[] args) {
        SpringApplication.run(PmnhApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // Defaults requests to originating from the US
        // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/i18n/SessionLocaleResolver.html
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Accept-Language
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        // Allows for changing the locale with every request by
        // reading the defined request parameter
        // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/i18n/LocaleChangeInterceptor.html
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Create an interceptor bean and put it into use
        registry.addInterceptor(localeChangeInterceptor());
    }

}
