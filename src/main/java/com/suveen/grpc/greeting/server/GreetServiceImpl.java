package com.suveen.grpc.greeting.server;

import com.suveen.grpc.proto.greet.GreetManyTimesRequest;
import com.suveen.grpc.proto.greet.GreetManyTimesResponse;
import com.suveen.grpc.proto.greet.GreetRequest;
import com.suveen.grpc.proto.greet.GreetResponse;
import com.suveen.grpc.proto.greet.GreetServiceGrpc;
import com.suveen.grpc.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

  @Override
  public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {

    // Extract the fields needed
    Greeting greeting = request.getGreeting();
    String firsName = greeting.getFirstName();

    String result = "Hello " + firsName;

    // Greate the response
    GreetResponse response = GreetResponse.newBuilder()
        .setResult(result)
        .build();

    // Send the response
    responseObserver.onNext(response);

    // Complete RPC call
    responseObserver.onCompleted();
  }

  @Override
  public void greetManyTimes(GreetManyTimesRequest request,
      StreamObserver<GreetManyTimesResponse> responseObserver) {
    String firstName = request.getGreetingOrBuilder().getFirstName();

    try {
      for (int i = 0; i <= 10; i++) {
        String result = "Hello " + firstName + ", response number = " + i;
        GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder().setResult(result)
            .build();

        responseObserver.onNext(response);
        Thread.sleep(1000l);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      responseObserver.onCompleted();
    }
  }
}

