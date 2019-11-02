INSERT INTO cinema.actor(firstName, lastName) VALUES ("Daniel", "Radcliffe");
INSERT INTO cinema.actor(firstName, lastName) VALUES ("Rupert", "Grint");
INSERT INTO cinema.actor(firstName, lastName) VALUES ("Emma", "Watson");

INSERT INTO cinema.movie(title) VALUES ("Harry Potter and the Philosopher's Stone");

INSERT INTO cinema.character(actor_id, movie_id, completeName) VALUES (1, 1, "Harry Potter");
INSERT INTO cinema.character(actor_id, movie_id, completeName) VALUES (2, 1, "Ron Weasley");
INSERT INTO cinema.character(actor_id, movie_id, completeName) VALUES (3, 1, "Hermione Granger");
