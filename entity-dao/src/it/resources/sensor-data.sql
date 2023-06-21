delete from sensors;
delete from transports;
DELETE FROM PRODUCERS;
delete from sensor_transport;

ALTER SEQUENCE PRODUCERS_SEQ RESTART WITH 1;
ALTER SEQUENCE TRANSPORTS_SEQ RESTART WITH 1;
ALTER SEQUENCE SENSORS_SEQ RESTART WITH 1;

insert into producers(id, name) VALUEs (producers_seq.nextval, 'BMW Corp.');

INSERT INTO TRANSPORTS(id, name, price, availability, producer_id)
  VALUEs (transports_seq.nextval,'BMW', 2500, 1, producers_seq.currval);

insert into sensors(id, name) values(sensors_seq.nextval, 'velocity');

insert into sensor_transport(transport_id, sensor_id) values(transports_seq.currval, sensors_seq.currval);

INSERT INTO TRANSPORTS(id, name, price, availability, producer_id)
  VALUEs (transports_seq.nextval,'Mercedes', 2500, 1, producers_seq.currval);

insert into sensor_transport(transport_id, sensor_id) values(transports_seq.currval, sensors_seq.currval);


