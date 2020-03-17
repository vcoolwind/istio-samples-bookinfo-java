drop table if exists review cascade;

create table review (
   id uuid not null,
    product_id uuid,
    reviewer varchar(255),
    text varchar(255),
    primary key (id)
);
