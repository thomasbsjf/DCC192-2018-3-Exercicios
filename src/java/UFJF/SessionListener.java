package UFJF;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
public class SessionListener implements HttpSessionListener {
 
    private static int sessoesAtivas;
 
    public SessionListener() {
        super();
    }
    public int getTotalActiveSession() {
        return sessoesAtivas;
    }
    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        sessoesAtivas += 1;
    }
    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        sessoesAtivas -= 1;
    }
}