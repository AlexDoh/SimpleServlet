package com.odmytrenko.servlet;

import com.google.common.base.MoreObjects;

import java.util.HashMap;
import java.util.Map;

public class Request {

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    private final String method;
    private final String uri;
    private final Map<String, String[]> parameters = new HashMap<>();

    public Request(Map<String, String[]> parameters, String method, String uri) {
        this.method = method.toUpperCase();
        this.uri = uri;
        if (parameters != null) {
            parameters.keySet().forEach(c -> {
                Object value = parameters.get(c);
                if (value != null) {
                    this.parameters.put(c, (String[]) value);
                }
            });
        }

    }

    public static Request create(String method, String url) {
        return new Request(null, method, url);
    }

    public String getParameter(String param) {
        return parameters.get(param)[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (!method.equals(request.method)) return false;
        if (!uri.equals(request.uri)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + uri.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(method)
                .addValue(uri)
                .toString();
    }
}
