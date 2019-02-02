package com.suveen.grpc.greeting.client;

import com.suveen.grpc.proto.greet.GreetRequest;
import com.suveen.grpc.proto.greet.GreetResponse;
import com.suveen.grpc.proto.greet.GreetServiceGrpc;
import com.suveen.grpc.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");


        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");

        // DummyServiceBlockingStub is synchronized client
        // DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);

        // DummyServiceFututreStub is ASync
        // DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        // Do something
        GreetServiceGrpc.GreetServiceBlockingStub greetService= GreetServiceGrpc.newBlockingStub(channel);
        Greeting greeting = Greeting.newBuilder().setFirstName("Suveen").setLastName("Vundavalli").build();

        GreetRequest greetRequest = GreetRequest.newBuilder().setGreeting(greeting).build();

        GreetResponse response = greetService.greet(greetRequest);

        System.out.println(response.getResult());


        System.out.println("Shutting down client channel");
        channel.shutdown();


    }
}
