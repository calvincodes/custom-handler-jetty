package com.github.calvincodes;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomHandlerJarPackaged extends HandlerWrapper {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("This is a custom JAR packaged handler");
        super.handle(target, baseRequest, request, response);
    }
}
