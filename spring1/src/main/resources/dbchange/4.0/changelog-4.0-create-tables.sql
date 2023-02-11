create table user_detail
(
    id int generated always as identity primary key ,
    first_name varchar(50),
    last_name varchar(50),
    username varchar(100),
    password varchar(256),
    account_Non_Expired boolean,
    account_Non_Locked boolean,
    CREDENTIALS_NON_EXPIRED boolean,
    enabled boolean
);

create table user_granted_authority(
    id int4 generated always as identity,
    authority varchar(50),
    user_id int
)