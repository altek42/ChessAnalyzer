CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user" (
    id UUID DEFAULT uuid_generate_v4() primary key,
    username varchar(64),
    email varchar(320),
    chess_id UUID
);

CREATE TABLE game (
    id UUID primary key,
    user_id UUID NOT NULL,
    CONSTRAINT fk_game_user
        FOREIGN KEY(user_id)
            REFERENCES "user"(id)
);
