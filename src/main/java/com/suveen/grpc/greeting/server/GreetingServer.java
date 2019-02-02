package com.suveen.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello I'm gRPC server");

        Server server = ServerBuilder.forPort(50051)
                .build();

        // Starting the server
        server.start();

        // Wait for server termination
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received shutdown request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        // Terminate server
        server.awaitTermination();
    }
}
