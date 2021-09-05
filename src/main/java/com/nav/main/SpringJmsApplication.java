package com.nav.main;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nav")
public class SpringJmsApplication {

	public static void main(String[] args) throws Exception {

		ActiveMQServer activeMQServer = ActiveMQServers.newActiveMQServer(
				new ConfigurationImpl().setPersistenceEnabled(false).setJournalDirectory("target/data/journal")
						.setSecurityEnabled(false).addAcceptorConfiguration("invm", "vm://0"));

		activeMQServer.start();

		SpringApplication.run(SpringJmsApplication.class, args);
	}

}
