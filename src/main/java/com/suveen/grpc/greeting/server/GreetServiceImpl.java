package com.suveen.grpc.greeting.server;

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
}
