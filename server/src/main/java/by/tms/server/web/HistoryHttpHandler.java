package by.tms.server.web;

import by.tms.server.storage.InMemoryOperationStorage;
import by.tms.server.model.Operation;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HistoryHttpHandler implements HttpHandler {
    private final Gson gson = new Gson();
    private final InMemoryOperationStorage storage = new InMemoryOperationStorage();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Operation> all = storage.findAll();
        String json = gson.toJson(all);

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, json.length());


        PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
        printWriter.print(json);
        printWriter.close();

    }
}
