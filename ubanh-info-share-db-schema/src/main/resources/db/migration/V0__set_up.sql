CREATE USER mobtail WITH password 'mobtail';

--CREATE SCHEMA mobtail;

--GRANT USAGE ON SCHEMA mobtail TO mobtail;
ALTER USER mobtail SET search_path = 'public';

-- ensure that user mobtail will have the needed privileges on new tables
ALTER DEFAULT PRIVILEGES
   IN SCHEMA PUBLIC
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLES
   TO mobtail;

-- ensure that user mobtail will have the needed privileges on new sequences
ALTER DEFAULT PRIVILEGES
   IN SCHEMA PUBLIC
GRANT USAGE ON SEQUENCES
   TO mobtail;

-- ensure that new functions will not have default privilege in public schema
ALTER DEFAULT PRIVILEGES
REVOKE EXECUTE ON FUNCTIONS
 FROM PUBLIC;

-- revoke default function privilege
REVOKE EXECUTE
   ON ALL FUNCTIONS IN SCHEMA PUBLIC
 FROM PUBLIC;