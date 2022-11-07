package se.ifmo.ru.util;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.Node;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.model.health.ServiceHealth;
import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.ejb.api.FlatService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;

@ApplicationScoped
@Slf4j
public class JNDIUtils {
    private Context context;

    @Inject
    Consul consul;

    @PostConstruct
    public void init() {
        log.info("Initialized context for getting remote EJB");
        List<ServiceHealth> nodes = consul.healthClient().getHealthyServiceInstances("first-service-ejb").getResponse();
        Service service = nodes.get(0).getService();
        String providerUrl = "remote+http://" + service.getAddress() + ":" + service.getPort();
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, providerUrl);
        try {
            context = new InitialContext(jndiProperties);
        } catch (NamingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
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
