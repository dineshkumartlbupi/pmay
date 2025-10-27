package com.aparsh.api.pmay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.filters.AcceptAllFileListFilter;
import org.springframework.integration.sftp.dsl.Sftp;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
@IntegrationComponentScan
public class SftpIntegrationConfig {

    @Value("${sftp.host}")
    private String sftpHost;
    @Value("${sftp.port}")
    private int sftpPort;
    @Value("${sftp.user}")
    private String sftpUser;
    @Value("${sftp.password}")
    private String sftpPassword;
    @Value("${sftp.remote-dir}")
    private String sftpRemoteDir;

    @Bean
    public DefaultSftpSessionFactory sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost(sftpHost);
        factory.setPort(sftpPort);
        factory.setUser(sftpUser);
        factory.setPassword(sftpPassword);
        factory.setAllowUnknownKeys(true);
        return factory;
    }

    @Bean
    public SftpInboundFileSynchronizer sftpInboundFileSynchronizer(DefaultSftpSessionFactory sf) {
        SftpInboundFileSynchronizer sync = new SftpInboundFileSynchronizer(sf);
        sync.setDeleteRemoteFiles(false);
        sync.setRemoteDirectory(sftpRemoteDir);
        sync.setFilter(new AcceptAllFileListFilter<>());
        return sync;
    }

    @Bean
    public MessageChannel sftpChannel() {
        return new DirectChannel();
    }

    @Bean
    public org.springframework.integration.core.MessageSource<?> sftpMessageSource(SftpInboundFileSynchronizer sync) {
        return Sftp.inboundAdapter(sync)
                .localDirectory(new java.io.File(System.getProperty("java.io.tmpdir") + "/pmay-sftp"))
                .autoCreateLocalDirectory(true)
                .get();
    }

    @Bean
    public org.springframework.integration.dsl.IntegrationFlow sftpInboundFlow(org.springframework.integration.core.MessageSource<?> sftpMessageSource) {
        return IntegrationFlows.from(sftpMessageSource, c -> c.poller(p -> p.fixedDelay(30000)))
                .channel(sftpChannel())
                .handle(message -> {
                    // Here we receive a File (java.io.File) downloaded from remote SFTP
                    Object payload = message.getPayload();
                    System.out.println("SFTP file arrived: " + payload);
                    // TODO: call a service to process XML and write ACK back to SFTP remote dir
                })
                .get();
    }
}
