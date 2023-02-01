CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user" (
    id UUID DEFAULT uuid_generate_v4() primary key,
    username varchar(64) NOT NULL,
    password varchar(60) NOT NULL,
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

CREATE TABLE properties (
    id UUID DEFAULT uuid_generate_v4() primary key,
    name varchar(32) NOT NULL,
    "value" varchar(64) NOT NULL
);

CREATE OR REPLACE FUNCTION notify_table_changed() RETURNS TRIGGER AS $$
    BEGIN
        perform pg_notify('table_changed', quote_ident(TG_TABLE_NAME));
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER table_change
    AFTER INSERT OR UPDATE OR DELETE ON public."properties"
    FOR EACH ROW EXECUTE PROCEDURE notify_table_changed();
