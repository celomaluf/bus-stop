package com.daimler.exercise.db;

import com.daimler.exercise.db.dao.BusStopDataSource;
import com.daimler.exercise.db.handler.OperatorHandler;
import com.daimler.exercise.db.handler.VehicleHandler;
import com.daimler.exercise.db.handler.VehicleTracesHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.vertx.core.*;
import lombok.extern.log4j.Log4j;
import org.flywaydb.core.Flyway;

import static com.daimler.exercise.http.util.EventBusEnum.*;

/**
 *  The entry point of the Database Worker Verticle
 */
@Log4j
public class DatabaseReaderWorkerVerticle extends AbstractVerticle {

    private BusStopDataSource busStopDataSource;
    private HikariDataSource dataSource;
    private static final String DB_USERNAME = "marcelo_teixeira";
    private static final String DB_PASSWORD = "abc123";
    private static final String DB_NAME = "daimler";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/".concat(DB_NAME);

    public DatabaseReaderWorkerVerticle() {
        System.setProperty("user.timezone", "GMT");
        final HikariConfig config = new HikariConfig();
        config.setPoolName("daimler-exercise-db-worker-jdbc-pool");
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        config.setConnectionTimeout(2000L);
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(4);
        HikariDataSource dataSource = new HikariDataSource(config);
        this.dataSource = dataSource;
    }

    @Override
    public void start(Promise<Void> promise) {
        try {
            initializeDataSources();
        } catch (Exception e) {
            log.error("Unable to connect to Database. Exiting!");
            vertx.close();
        }

        executeDatabaseMigrations();

        vertx.eventBus().consumer(OPERATORS_BY_TS.getName(), new OperatorHandler<>(busStopDataSource));
        vertx.eventBus().consumer(VEHICLES_BY_TS_OPERATOR.getName(), new VehicleHandler<>(busStopDataSource));
        vertx.eventBus().consumer(VEHICLES_BY_TS_OPERATOR_AT_STOP.getName(), new VehicleHandler<>(busStopDataSource));
        vertx.eventBus().consumer(TRACES_BY_TS_VEHICLE.getName(), new VehicleTracesHandler<>(busStopDataSource));

        promise.complete();
    }

    private void initializeDataSources() {
        this.busStopDataSource = new BusStopDataSource(dataSource);
    }

    @Override
    public void stop() {
        log.info("DB Worker verticle stopped");
        this.dataSource.close();
        this.vertx.close();
    }

    private void executeDatabaseMigrations() {
        log.info("Starting DB migrations for " + DB_URL);
        Flyway flyway = Flyway.configure().dataSource(DB_URL, DB_USERNAME, DB_PASSWORD).load();
        int migrationsApplied = flyway.migrate();
        log.info("Applied " + migrationsApplied + " migrations to DB");
    }

    public static void main(String[] args ) {
        Vertx.clusteredVertx(new VertxOptions(), results -> {
            if (results.succeeded()) {
                Vertx vertx = results.result();
                vertx.deployVerticle(DatabaseReaderWorkerVerticle.class.getName(),
                        new DeploymentOptions().setWorker(true));
            }
        });
    }
}
