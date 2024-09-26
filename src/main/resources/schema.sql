CREATE TABLE IF NOT EXISTS albums (
    album_id SERIAL PRIMARY KEY,
    album_title varchar(30),
    album_artists varchar(50),
    release_date date,
    genre varchar(30),
    label varchar(30),
    artwork_url varchar(30)
);

CREATE TABLE IF NOT EXISTS records (
   record_id SERIAL PRIMARY KEY,
   title varchar(250) NOT NULL,
   artist varchar(30),
   album varchar(30) NOT NULL,
   release_date date,
   duration time,
   track_num INT NOT NULL,
   label varchar(50),
   album_id INT NOT NULL REFERENCES albums(album_id),
   producer varchar(30),
   lyricist varchar(30),
   cover_art varchar(30),
   all_credits varchar(300),
   language varchar(20),
   explicit_content varchar(1)
);

CREATE TABLE IF NOT EXISTS artists (
    artist_id SERIAL PRIMARY KEY,
    artist_name varchar(30) NOT NULL,
    genre varchar(30),
    bio varchar(300),
    image varchar(30),
    dob date NOT NULL,
    pob varchar(30),
    url varchar(30),
    awards varchar(40),
    influences varchar(100),
    label varchar(50),
    socials varchar(50),
    version int
);