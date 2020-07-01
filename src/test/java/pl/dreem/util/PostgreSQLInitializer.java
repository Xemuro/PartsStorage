package pl.dreem.util;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLInitializer extends PostgreSQLContainer<PostgreSQLInitializer> {

    private static final String IMAGE_VERSION = "postgres:11.1";
    private static PostgreSQLInitializer container;

    private PostgreSQLInitializer() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLInitializer getInstance() {
        if (container == null) {
            container = new PostgreSQLInitializer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}