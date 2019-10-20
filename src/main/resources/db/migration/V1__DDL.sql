CREATE TABLE public.bus_stop (
    timestamp bigint,
    line_id varchar(4),
    direction char(1),
    journey_pattern_id varchar(8), -- possibly null
    time_frame timestamp,
    vehicle_journey_id BIGINT,
    operator varchar(3),
    congestion boolean,
    latitude numeric,
    longitude numeric,
    delay int,
    block_id int,
    vehicle_id int,
    stop_id varchar(8), -- possibly null
    at_bus_stop boolean
);


