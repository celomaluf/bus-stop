COPY bus_stop(timestamp,  line_id,
              direction, journey_pattern_id,
              time_frame, vehicle_journey_id,
              operator, congestion,
              latitude, longitude,
              delay, block_id,
              vehicle_id, stop_id,
              at_bus_stop)
    FROM '/var/lib/postgresql/data/siri.20121107.csv' DELIMITER ',';
