/*
 * This file is generated by jOOQ.
 */
package com.daimler.exercise.db.autogenerated;


import com.daimler.exercise.db.autogenerated.tables.BusStop;
import com.daimler.exercise.db.autogenerated.tables.FlywaySchemaHistory;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.bus_stop</code>.
     */
    public static final BusStop BUS_STOP = com.daimler.exercise.db.autogenerated.tables.BusStop.BUS_STOP;

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = com.daimler.exercise.db.autogenerated.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;
}