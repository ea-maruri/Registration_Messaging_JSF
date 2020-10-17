package com.corejsf.ws;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;

/**
 * @author EAMT on 8/10/2020
 */
@ApplicationScoped
@ServerEndpoint("/push")
public class ServerWebSocket {

    private Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void open(Session session) {
        System.out.println("Session opened ==>");
        sessions.add(session);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("new message ==> " + message);
        try {
            for (int c = 0; c < 100; c++) {
                for (Session s : sessions) {
                    s.getBasicRemote().sendText("{\"value\" : \"" + (c + 1) + "\"}");
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
