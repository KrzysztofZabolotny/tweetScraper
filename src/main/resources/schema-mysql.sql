create table tweet
(
    id integer not null,
    name varchar(255) not null,
    searched_by_hashtag varchar(255) not null,
    internal_tweet_id  varchar(255) not null,
    post_date varchar(255) not null,
    post_time varchar(255) not null,
    username varchar(255) not null,
    content varchar(255) not null,
    primary key(id)
);