package com.santiago.grpc.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcWebProxyStarter implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(GrpcWebProxyStarter.class);
    private static final String BINARY_PATH = "/usr/local/bin/grpcwebproxy";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Path bin = Paths.get(BINARY_PATH);
        if (!Files.exists(bin)) {
            log.warn("grpcwebproxy binary not found at {} — skipping automatic proxy start. Install grpcwebproxy or run a proxy (Envoy/grpcwebproxy) separately for gRPC-Web support.", BINARY_PATH);
            return;
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(BINARY_PATH,
                "--backend_addr=127.0.0.1:9090",
                "--run_http_server=true",
                "--http_addr=127.0.0.1:8080",
                "--allow_all_origins");
            pb.inheritIO();
            pb.start();
            log.info("Started grpcwebproxy from {} (dev helper).", BINARY_PATH);
        } catch (IOException e) {
            log.warn("Failed to start grpcwebproxy from {}: {}. Please start a proxy manually (grpcwebproxy or Envoy).", BINARY_PATH, e.getMessage());
        }
    }
}