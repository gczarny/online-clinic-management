create table address
(
    address_id SERIAL NOT NULL,
    country varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    postal_code varchar(10) NOT NULL,
    street varchar(255) NOT NULL,
    primary key (address_id)
);
