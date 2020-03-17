drop table if exists rating cascade ;
create table rating (id uuid not null, product_id uuid, reviewer varchar(255), stars int4 not null, primary key (id)) ;