package com.github.calvincodes;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomHandlerWarPackaged extends AbstractHandler {

    @Override
    public void handle(String s, Request request,
                       HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        System.out.println("This is a custom WAR packaged handler");
    }
}
