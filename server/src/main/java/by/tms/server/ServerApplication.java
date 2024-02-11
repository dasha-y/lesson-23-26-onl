package by.tms.server;

import by.tms.server.web.CalculatorHttpHandler;
import by.tms.server.web.HistoryHttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8080), 1);
        httpServer.createContext("/calc", new CalculatorHttpHandler());
        httpServer.createContext("/history", new HistoryHttpHandler());
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        httpServer.start();
    }
}
