drop table news;
drop table authors;


create table authors
(
    id          serial                  primary key,
    name        varchar(15)             not null,
    createddate timestamp default now() not null,
    updateddate timestamp               not null
);

create table news
(
    id          serial                  primary key,
    title       varchar(30)             not null,
    content     varchar(255)            not null,
    createddate timestamp default now() not null,
    updateddate timestamp               not null,
    authorId  integer,
    foreign key (authorId) references authors (id) on delete cascade
);
