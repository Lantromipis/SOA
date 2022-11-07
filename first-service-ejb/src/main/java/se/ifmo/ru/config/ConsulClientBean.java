package se.ifmo.ru.config;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Singleton
@Startup
@Slf4j
public class ConsulClientBean {
    private final String serviceName = "first-service-ejb";

    private AgentClient agentClient;
    private String serviceId;

    @Produces
    public Consul getClient() {
        return Consul
                .builder()
                .withHostAndPort(HostAndPort.fromParts("first-service-consul", 8500))
                .build();
    }

    @PostConstruct
    private void registerService() {
        agentClient = getClient().agentClient();

        serviceId = serviceName + UUID.randomUUID();
        Registration service = ImmutableRegistration.builder()
                .id(serviceId)
                .check(Registration.RegCheck.ttl(20L))
                .port(8080)
                .address("first-service-ejb")
                .name(serviceName)
                .build();

        agentClient.register(service);

        Timer myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            public void run() {
                try {
                    agentClient.pass(serviceId);
                } catch (NotRegisteredException e) {
                    log.error("Unable to checkout consul.", e);
                }
            }
        }, 0, 10000);
    }

    @PreDestroy
    private void deregisterService(){
        agentClient.deregister(serviceId);
    }
}
