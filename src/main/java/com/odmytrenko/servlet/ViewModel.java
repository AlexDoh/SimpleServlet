package com.odmytrenko.servlet;

import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private final String view;
    private final Map<String, Object> attributes = new HashMap<>();
    private final Map<String, String> newCookie = new HashMap<>();
    private boolean hasCookie;

    public ViewModel(String view) {
        this.view = view;
    }

    public ViewModel addAttribute(String attribute, Object value) {
        attributes.put(attribute, value);
        return this;
    }

    public String getView() {
        return view;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Object getAttribute(String attribute) {
        return attributes.get(attribute);
    }

    public ViewModel addCookie(String name, String value) {
        hasCookie = true;
        newCookie.put(name, value);
        return this;
    }

    public boolean hasCookie() {
        return hasCookie;
    }

    public Map<String, String> getNewCookie() {
        return newCookie;
    }
}
