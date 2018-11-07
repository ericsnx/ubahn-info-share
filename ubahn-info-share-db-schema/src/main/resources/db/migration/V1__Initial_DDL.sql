CREATE TABLE incidents (

    id uuid PRIMARY KEY NOT NULL,
	line VARCHAR(10) NOT NULL,
    user_name VARCHAR(250) NOT NULL,
    removed boolean NOT NULL,
    reported_at timestamp,
    updated_at timestamp

);