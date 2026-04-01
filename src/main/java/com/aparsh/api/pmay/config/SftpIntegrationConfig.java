package com.aparsh.api.pmay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.filters.AcceptAllFileListFilter;
import org.springframework.integration.sftp.dsl.Sftp;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@Profile("oracle")
@IntegrationComponentScan
public class SftpIntegrationConfig {

    @Value("${sftp.host}")
    private String sftpHost;
    @Value("${sftp.port:22}")
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
    public IntegrationFlow sftpInboundFlow(DefaultSftpSessionFactory sf, SftpInboundFileSynchronizer sync) {
        return IntegrationFlow.from(
                Sftp.inboundAdapter(sf, java.util.Comparator.comparing(File::getName))
                        .filter(new AcceptAllFileListFilter<>())
                        .remoteDirectory(sftpRemoteDir)
                        .localDirectory(new File("sftp_local"))
                        .autoCreateLocalDirectory(true),
                e -> e.poller(Pollers.fixedDelay(30000)))
                .channel(sftpChannel())
                .handle(message -> {
                    Object payload = message.getPayload();
                    System.out.println("SFTP file arrived: " + payload);
                })
                .get();
    }
}
