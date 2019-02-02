package com.suveen.grpc.greeting.client;

import com.suveen.grpc.proto.calculator.CalculatorServiceGrpc;
import com.suveen.grpc.proto.calculator.SumRequest;
import com.suveen.grpc.proto.greet.GreetManyTimesRequest;
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

    System.out.println("Creating stubs");

    // DummyServiceBlockingStub is synchronized client
    // DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);

    // DummyServiceFututreStub is ASync
    // DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

    // Do something
    GreetServiceGrpc.GreetServiceBlockingStub greetService = GreetServiceGrpc
        .newBlockingStub(channel);
    Greeting greeting = Greeting.newBuilder().setFirstName("Suveen").setLastName("Vundavalli")
        .build();
    GreetRequest greetRequest = GreetRequest.newBuilder().setGreeting(greeting).build();
    GreetResponse response = greetService.greet(greetRequest);
    System.out.println(response.getResult());

    CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorSerice = CalculatorServiceGrpc
        .newBlockingStub(channel);
    SumRequest request = SumRequest.newBuilder().setFirstNumber(3).setSecondNumber(10).build();
    System.out.println(calculatorSerice.sum(request));

    System.out.println("Server streaming");
    GreetManyTimesRequest greetManyTimesRequest =
        GreetManyTimesRequest
            .newBuilder()
            .setGreeting(Greeting.newBuilder().setFirstName("Suveen"))
            .build();

    greetService
        .greetManyTimes(greetManyTimesRequest)
        .forEachRemaining(greetManyTimesResponse -> {
          System.out.println(greetManyTimesResponse.getResult());
        });
    System.out.println("Shutting down client channel");
    channel.shutdown();

  }
}
