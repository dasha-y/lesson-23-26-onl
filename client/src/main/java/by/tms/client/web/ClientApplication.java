package by.tms.client.web;

import by.tms.client.console.ConsoleReader;
import by.tms.client.console.ConsoleWriter;
import by.tms.client.model.Operation;
import by.tms.client.storage.InMemoryOperationStorage;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ClientApplication {
    private static final InMemoryOperationStorage storage = new InMemoryOperationStorage();
     public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
//        JFrame frame = new JFrame("Client");
//
//        JButton jButton = new JButton("Test");
//        jButton.setBounds(100, 100, 100, 40);
//
//        jButton.addActionListener(e -> {
//            System.out.println("Hello world");
//        });
//
//        frame.add(jButton);
//
//        frame.setSize(400, 500);
//        frame.setLayout(null);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Gson gson = new Gson();


         ConsoleWriter consoleWriter = new ConsoleWriter();
         ConsoleReader consoleReader = new ConsoleReader();
         boolean proceed = true;
         while(proceed) {
             Operation operation = new Operation();

             consoleWriter.write("Enter num 1: ");
             double num1 = consoleReader.readDouble();
             consoleWriter.write("Enter num 2: ");
             double num2 = consoleReader.readDouble();
             consoleWriter.write("Enter operation: ");
             String type = consoleReader.readType();

             operation.setNum1(num1);
             operation.setNum2(num2);
             operation.setType(type);
             storage.save(operation);

             String json = gson.toJson(operation);

             HttpRequest request = HttpRequest.newBuilder()
                     .uri(new URI("http://localhost:8080/calc"))
                     .headers("Content-Type", "application/json")
                     .POST(HttpRequest.BodyPublishers.ofString(json))
                     .build();


             HttpClient httpClient = HttpClient.newHttpClient();
             HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
             Operation operation1 = gson.fromJson(response.body(), Operation.class);
             System.out.println(operation1);

             consoleWriter.write("Want see history?");
             Boolean isHistory = consoleReader.readBoolean();
             if(isHistory){

                 HttpRequest requestHistory = HttpRequest.newBuilder()
                         .uri(new URI("http://localhost:8080/history"))
                         .headers("Content-Type", "application/json")
                         .POST(HttpRequest.BodyPublishers.ofString(json))
                         .build();

                 HttpClient httpClientHistory = HttpClient.newHttpClient();
                 HttpResponse<String> responseHistory = httpClientHistory.send(requestHistory, HttpResponse.BodyHandlers.ofString());

                 List<Operation> history = gson.fromJson(responseHistory.body(), List.class);

                 System.out.println(history.toString());
             }

             consoleWriter.write("Want to continue?\n  true/false\n");
             proceed = consoleReader.readBoolean();



         }




     }
}
