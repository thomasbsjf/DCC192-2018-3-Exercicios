package UFJF;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
public class SessionListener implements HttpSessionListener {
 
    private final AtomicInteger sessoesAtivas;
 
    public SessionListener() {
        super();
        sessoesAtivas = new AtomicInteger();
 
    }
    public int getTotalActiveSession() {
        return sessoesAtivas.get();
    }
    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        sessoesAtivas.incrementAndGet();
    }
    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        sessoesAtivas.decrementAndGet();
    }
}