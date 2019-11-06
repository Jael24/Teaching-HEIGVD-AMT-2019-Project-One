LOAD DATA INFILE "/var/lib/mysql-files/actors_cleaned.csv"
INTO TABLE cinema.actor
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE "/var/lib/mysql-files/clips_cleaned.csv"
INTO TABLE cinema.movie
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE "/var/lib/mysql-files/chars_cleaned.csv"
INTO TABLE cinema.character
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

