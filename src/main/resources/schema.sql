drop table if exists post CASCADE;
drop table if exists user CASCADE;
create table post (id integer generated by default as identity, description varchar(255), user_id integer, primary key (id));
create table user (id integer generated by default as identity, date_of_birth timestamp, name varchar(50), primary key (id));
alter table post add constraint FK72mt33dhhs48hf9gcqrq4fxte foreign key (user_id) references user;