package org.owasp.webgoat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EndpointDocController {
    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public EndpointDocController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping(value="/endpointdoc", method=RequestMethod.GET)
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("endpointdoc");
        model.addObject("handlerMethods", this.handlerMapping.getHandlerMethods());
        return model;
    }
}