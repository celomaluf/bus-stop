/*
 * This file is generated by jOOQ.
 */
package com.daimler.exercise.db.autogenerated.tables.records;


import com.daimler.exercise.db.autogenerated.tables.BusStop;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BusStopRecord extends TableRecordImpl<BusStopRecord> implements Record15<Long, String, String, String, Timestamp, Long, String, Boolean, BigDecimal, BigDecimal, Integer, Integer, Integer, String, Boolean> {

    private static final long serialVersionUID = -948409863;

    /**
     * Setter for <code>public.bus_stop.timestamp</code>.
     */
    public void setTimestamp(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.bus_stop.timestamp</code>.
     */
    public Long getTimestamp() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.bus_stop.line_id</code>.
     */
    public void setLineId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.bus_stop.line_id</code>.
     */
    public String getLineId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.bus_stop.direction</code>.
     */
    public void setDirection(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.bus_stop.direction</code>.
     */
    public String getDirection() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.bus_stop.journey_pattern_id</code>.
     */
    public void setJourneyPatternId(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.bus_stop.journey_pattern_id</code>.
     */
    public String getJourneyPatternId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.bus_stop.time_frame</code>.
     */
    public void setTimeFrame(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.bus_stop.time_frame</code>.
     */
    public Timestamp getTimeFrame() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>public.bus_stop.vehicle_journey_id</code>.
     */
    public void setVehicleJourneyId(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.bus_stop.vehicle_journey_id</code>.
     */
    public Long getVehicleJourneyId() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>public.bus_stop.operator</code>.
     */
    public void setOperator(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.bus_stop.operator</code>.
     */
    public String getOperator() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.bus_stop.congestion</code>.
     */
    public void setCongestion(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.bus_stop.congestion</code>.
     */
    public Boolean getCongestion() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>public.bus_stop.latitude</code>.
     */
    public void setLatitude(BigDecimal value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.bus_stop.latitude</code>.
     */
    public BigDecimal getLatitude() {
        return (BigDecimal) get(8);
    }

    /**
     * Setter for <code>public.bus_stop.longitude</code>.
     */
    public void setLongitude(BigDecimal value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.bus_stop.longitude</code>.
     */
    public BigDecimal getLongitude() {
        return (BigDecimal) get(9);
    }

    /**
     * Setter for <code>public.bus_stop.delay</code>.
     */
    public void setDelay(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.bus_stop.delay</code>.
     */
    public Integer getDelay() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>public.bus_stop.block_id</code>.
     */
    public void setBlockId(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.bus_stop.block_id</code>.
     */
    public Integer getBlockId() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>public.bus_stop.vehicle_id</code>.
     */
    public void setVehicleId(Integer value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.bus_stop.vehicle_id</code>.
     */
    public Integer getVehicleId() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>public.bus_stop.stop_id</code>.
     */
    public void setStopId(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.bus_stop.stop_id</code>.
     */
    public String getStopId() {
        return (String) get(13);
    }

    /**
     * Setter for <code>public.bus_stop.at_bus_stop</code>.
     */
    public void setAtBusStop(Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.bus_stop.at_bus_stop</code>.
     */
    public Boolean getAtBusStop() {
        return (Boolean) get(14);
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row15<Long, String, String, String, Timestamp, Long, String, Boolean, BigDecimal, BigDecimal, Integer, Integer, Integer, String, Boolean> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row15<Long, String, String, String, Timestamp, Long, String, Boolean, BigDecimal, BigDecimal, Integer, Integer, Integer, String, Boolean> valuesRow() {
        return (Row15) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return BusStop.BUS_STOP.TIMESTAMP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return BusStop.BUS_STOP.LINE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return BusStop.BUS_STOP.DIRECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return BusStop.BUS_STOP.JOURNEY_PATTERN_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return BusStop.BUS_STOP.TIME_FRAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field6() {
        return BusStop.BUS_STOP.VEHICLE_JOURNEY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return BusStop.BUS_STOP.OPERATOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field8() {
        return BusStop.BUS_STOP.CONGESTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field9() {
        return BusStop.BUS_STOP.LATITUDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field10() {
        return BusStop.BUS_STOP.LONGITUDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field11() {
        return BusStop.BUS_STOP.DELAY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field12() {
        return BusStop.BUS_STOP.BLOCK_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field13() {
        return BusStop.BUS_STOP.VEHICLE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return BusStop.BUS_STOP.STOP_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field15() {
        return BusStop.BUS_STOP.AT_BUS_STOP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getTimestamp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getLineId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getDirection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getJourneyPatternId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getTimeFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component6() {
        return getVehicleJourneyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getOperator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component8() {
        return getCongestion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component9() {
        return getLatitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component10() {
        return getLongitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component11() {
        return getDelay();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component12() {
        return getBlockId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component13() {
        return getVehicleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component14() {
        return getStopId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component15() {
        return getAtBusStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getTimestamp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getLineId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDirection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getJourneyPatternId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getTimeFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value6() {
        return getVehicleJourneyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getOperator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value8() {
        return getCongestion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value9() {
        return getLatitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value10() {
        return getLongitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value11() {
        return getDelay();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value12() {
        return getBlockId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value13() {
        return getVehicleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getStopId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value15() {
        return getAtBusStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value1(Long value) {
        setTimestamp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value2(String value) {
        setLineId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value3(String value) {
        setDirection(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value4(String value) {
        setJourneyPatternId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value5(Timestamp value) {
        setTimeFrame(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value6(Long value) {
        setVehicleJourneyId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value7(String value) {
        setOperator(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value8(Boolean value) {
        setCongestion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value9(BigDecimal value) {
        setLatitude(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value10(BigDecimal value) {
        setLongitude(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value11(Integer value) {
        setDelay(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value12(Integer value) {
        setBlockId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value13(Integer value) {
        setVehicleId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value14(String value) {
        setStopId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord value15(Boolean value) {
        setAtBusStop(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusStopRecord values(Long value1, String value2, String value3, String value4, Timestamp value5, Long value6, String value7, Boolean value8, BigDecimal value9, BigDecimal value10, Integer value11, Integer value12, Integer value13, String value14, Boolean value15) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BusStopRecord
     */
    public BusStopRecord() {
        super(BusStop.BUS_STOP);
    }

    /**
     * Create a detached, initialised BusStopRecord
     */
    public BusStopRecord(Long timestamp, String lineId, String direction, String journeyPatternId, Timestamp timeFrame, Long vehicleJourneyId, String operator, Boolean congestion, BigDecimal latitude, BigDecimal longitude, Integer delay, Integer blockId, Integer vehicleId, String stopId, Boolean atBusStop) {
        super(BusStop.BUS_STOP);

        set(0, timestamp);
        set(1, lineId);
        set(2, direction);
        set(3, journeyPatternId);
        set(4, timeFrame);
        set(5, vehicleJourneyId);
        set(6, operator);
        set(7, congestion);
        set(8, latitude);
        set(9, longitude);
        set(10, delay);
        set(11, blockId);
        set(12, vehicleId);
        set(13, stopId);
        set(14, atBusStop);
    }
}