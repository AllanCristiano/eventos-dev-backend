-- Ensure the pgcrypto extension is installed
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Create the event table with UUID generation
CREATE TABLE event (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    imgUrl VARCHAR(100) NOT NULL,
    eventUrl VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    remote BOOLEAN NOT NULL
);