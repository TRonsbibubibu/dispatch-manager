package com.yizhen.proxy;

import com.yizhen.proxy.dependence.*;
import com.yizhen.proxy.exception.BeansException;
import com.yizhen.proxy.proto.GreeterGrpc;
import com.yizhen.proxy.proto.HelloReply;
import com.yizhen.proxy.proto.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by trons on 2017/5/31.
 */
public class DispatchServer {


    private int port = 3001;
    private Server server;
    private Logger logger = LoggerFactory.getLogger(DispatchServer.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        final DispatchServer server = new DispatchServer();
        server.start();
        server.blockUntilShutdown();
    }

    private void start() throws IOException {

        /**
         * init the ioc container
         */
        Container ioc = new DefaultContainer();

        /**
         * 初始化组件
         */
        Component head = new ConfigComponent();
        head.setNext(new HikariCPComponent())
                .setNext(new MybatisComponent());
        try {
            do {
                head.handle(ioc);
                head = head.next();
            } while (head != null);
        } catch (BeansException e) {
            logger.warn(e.toString());
            return;
        }

        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();

        System.out.println("service start...");

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                DispatchServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // 实现 定义一个实现服务接口的类
    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {


        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            System.out.println("service:" + req.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}