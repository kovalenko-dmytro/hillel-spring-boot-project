ALTER TABLE users
ADD COLUMN
`enabled` int(11) DEFAULT NULL AFTER password;