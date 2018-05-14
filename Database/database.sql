create database iecproject;
use iecproject;

create table users(

	id integer not null primary key auto_increment,
    firstName varchar(15) not null,
    lastName varchar(15) not null,
    username varchar(15) unique not null,
	keyword varchar(10) not null
);

create table message(

	id integer not null primary key auto_increment,
    id_sender integer not null,
    id_recipient integer not null,
    message varchar(200),
    _schedule datetime not null,

	foreign key(id_sender) references users(id)

);