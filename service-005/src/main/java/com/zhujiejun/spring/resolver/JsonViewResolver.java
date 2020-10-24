package com.zhujiejun.spring.resolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

public class JsonViewResolver extends AbstractCachingViewResolver {

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        return new MappingJackson2JsonView();
    }
}
