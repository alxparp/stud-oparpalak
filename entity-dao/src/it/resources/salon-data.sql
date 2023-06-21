delete from salons;
delete from transports;
DELETE FROM PRODUCERS;
insert into producers(id, name) VALUEs (producers_seq.nextval, 'BMW Corp.');
INSERT INTO TRANSPORTS(id, name, price, availability, producer_id)
  VALUEs (transports_seq.nextval,'BMW', 2500, 1, producers_seq.currval);
insert into salons(id, name, transport_id) values(salons_seq.nextval, 'Odesskiy', transports_seq.currval);