delete from cars;
DELETE FROM TRANSPORTS;
DELETE FROM PRODUCERS;
insert into producers(id, name) VALUEs (producers_seq.nextval, 'BMW Corp.');
INSERT INTO TRANSPORTS(id, name, price, availability, producer_id)
  VALUEs (transports_seq.nextval,'BMW', 2500, 1, producers_seq.currval);
insert into cars (id, mileage_km, engine_capacity) VALUEs (transports_seq.currval, 1000, 1500);