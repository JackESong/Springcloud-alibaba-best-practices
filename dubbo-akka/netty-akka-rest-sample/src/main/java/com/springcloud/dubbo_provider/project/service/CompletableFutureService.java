package com.springcloud.dubbo_provider.project.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.springcloud.dubbo_provider.project.akka.SpringExtension;
import com.springcloud.dubbo_provider.project.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CompletableFutureService {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    public CompletableFuture<Message> get(String payload, Long id) {
        CompletableFuture<Message> completableFuture = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("workerActor", completableFuture), "worker-actor");
        workerActor.tell(new Message(payload, id), null);
        return completableFuture;
    }
}
