CREATE TABLE confirmation_token (
    id           bigserial    NOT NULL,
    confirmed_at timestamp    NULL,
    created_at   timestamp    NOT NULL,
    expires_at   timestamp    NOT NULL,
    "token"      varchar(255) NOT NULL,
    user_id      int8         NOT NULL,
    CONSTRAINT confirmation_token_pkey PRIMARY KEY (id),
    CONSTRAINT fko9fl25wqyh7w7mnfkdqen1rcm FOREIGN KEY (user_id) REFERENCES public.user_detail(id)
);