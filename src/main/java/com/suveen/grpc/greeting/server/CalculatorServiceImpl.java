package com.suveen.grpc.greeting.server;

import com.suveen.grpc.proto.calculator.CalculatorServiceGrpc;
import com.suveen.grpc.proto.calculator.SumResponse;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

  @Override
  public void sum(com.suveen.grpc.proto.calculator.SumRequest request,
      StreamObserver<com.suveen.grpc.proto.calculator.SumResponse> responseObserver) {

    SumResponse response = SumResponse.newBuilder()
        .setSum(request.getFirstNumber() + request.getSecondNumber()).build();

    responseObserver.onNext(response);

    responseObserver.onCompleted();


  }
}
