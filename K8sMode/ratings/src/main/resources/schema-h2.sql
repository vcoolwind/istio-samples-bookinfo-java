drop table rating if exists ;
create table rating (id varchar(255) not null, product_id varchar(255), reviewer varchar(255), stars integer not null, primary key (id));
