package com.erpnext.framework.web.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;


@Configuration
@EnableWebMvc
@ComponentScan("com.erpnext.**.endpoint")
public class RestApiMvcConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(getJsonConverter());
		converters.add(getXmlConverter());
    }
	
	/**
     * Setup a simple strategy: use all the defaults and return JSON by default when not sure. 
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
    
    @Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
    
    private HttpMessageConverter<?> getJsonConverter() { 
        return new MappingJackson2HttpMessageConverter(getObjectMapper(false));
    }
    
    /**
     * Subclasses might override this method to use JAXB natively for XML serialization by
     * {@code return new Jaxb2RootElementHttpMessageConverter()}
     * @see {@link #getObjectMapper(boolean)}
     */
    private HttpMessageConverter<?> getXmlConverter() {
        return new MappingJackson2XmlHttpMessageConverter(getObjectMapper(true));
    }
    
    private ObjectMapper getObjectMapper(boolean useXml) {
        Jackson2ObjectMapperBuilder builder = getObjectMapperBuilder();
        TypeFactory factory = TypeFactory.defaultInstance();
        if (useXml) {
            return builder.createXmlMapper(true).build().setTypeFactory(factory);
        } else {
            return builder.build().setTypeFactory(factory);
        }
    }
    
    private Jackson2ObjectMapperBuilder getObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
            // Ensure JAXB annotations get picked up
            .findModulesViaServiceLoader(true)
            // Enable/disable some features
            .featuresToEnable(new Object[]{DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY})
            .featuresToDisable(new Object[]{SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED});
    }
}
