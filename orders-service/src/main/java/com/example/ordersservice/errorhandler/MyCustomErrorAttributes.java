package com.example.ordersservice.errorhandler;

import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;


    @Component
    public class MyCustomErrorAttributes extends DefaultErrorAttributes {

        @Override
        public Map<String, Object> getErrorAttributes(
                WebRequest webRequest, ErrorAttributeOptions options) {
            Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
            errorAttributes.put("locale", webRequest.getLocale()
                    .toString());
            errorAttributes.remove("error");
            return errorAttributes;
        }
    }

