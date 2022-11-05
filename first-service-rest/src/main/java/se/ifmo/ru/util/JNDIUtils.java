package se.ifmo.ru.util;

import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.ejb.api.FlatService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@ApplicationScoped
@Slf4j
public class JNDIUtils {
    private Context context;

    @PostConstruct
    public void init() throws NamingException {
        log.info("Initialized context for getting remote EJB");
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://first-service-ejb:8080");
        context = new InitialContext(jndiProperties);
    }

    public FlatService getFlatServiceEJBInstance() {
        log.info("Remote EJB requested");
        try {
            return (FlatService) context.lookup("ejb:/first-service-ejb/FlatServiceImpl!se.ifmo.ru.ejb.api.FlatService");
        } catch (NamingException e) {
            throw new RuntimeException("Error getting EJB instance", e);
        }

    }
}
