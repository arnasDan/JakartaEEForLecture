package arda.services.name;

import arda.services.Production;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Future;

@ApplicationScoped
@Production
@Default
public class PrimaryNameGenerator implements Serializable, INameGenerator {
    public Future<String> generateNickName() {
        System.out.println("Using default implementation");

        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        UUID uuid = UUID.randomUUID();
        final var generatedName = uuid.toString();
        return new AsyncResult<>(generatedName);
    }
}