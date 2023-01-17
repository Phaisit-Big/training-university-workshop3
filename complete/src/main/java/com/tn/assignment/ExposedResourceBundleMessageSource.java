package com.tn.assignment;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

public class ExposedResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {


    @Override
    protected Properties loadProperties(Resource resource, String fileName) throws IOException {

        //System.out.println("Loading... " + fileName);
        return super.loadProperties(resource, fileName);
    }

    /**
     * Gets all messages for presented Locale.
     * @param locale user request's locale
     * @return all messages
     */
    public Properties getProperties(Locale locale){
        return getMergedProperties(locale).getProperties();
    }
}