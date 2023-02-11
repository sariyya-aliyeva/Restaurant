create table contact_us
(
    id int4 GENERATED ALWAYS AS IDENTITY,
    phone_number   VARCHAR(100),
    email VARCHAR(150),
    address VARCHAR(400),
    CONSTRAINT contact_us_pkey PRIMARY KEY (id)
);