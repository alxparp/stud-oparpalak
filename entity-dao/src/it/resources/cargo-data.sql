delete from cargo;
DELETE FROM TRANSPORTS;
DELETE FROM PRODUCERS;
insert into producers(id, name) VALUEs (producers_seq.nextval, 'BMW Corp.');
INSERT INTO TRANSPORTS(id, name, price, availability, producer_id)
  VALUEs (transports_seq.nextval,'BMW', 2500, 1, producers_seq.currval);
insert into cargo (id, body_type) VALUEs (transports_seq.currval, 'Лесовоз');