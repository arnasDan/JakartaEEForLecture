package arda.services.name;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Future;

@ApplicationScoped
@Alternative
public class NameGenerator implements Serializable, INameGenerator {
    public Future<String> generateNickName() {
        System.out.println("Using alternative implementation");

        try {
            Thread.sleep(5000); // Simulate intensive work
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        UUID uuid = UUID.randomUUID();
        final var generatedName = uuid.toString();
        return new AsyncResult<>(generatedName);
    }
}
