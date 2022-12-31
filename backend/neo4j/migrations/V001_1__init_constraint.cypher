CREATE CONSTRAINT constraint_state_hash_unique IF NOT EXISTS FOR (n:STATE) REQUIRE n.hash IS UNIQUE;
