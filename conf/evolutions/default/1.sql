# --- !Ups

create table Profile (
  id                        bigint not null auto_increment,
  first_name                varchar(255),
  last_name                 varchar(255),
  summary                   varchar(255),
  experience                varchar(255),
  constraint pk_company primary key (id))
;

insert into Profile values(1, 'Ivan', 'Voloshyn', 'developer', '2 years java, 1 year .net');
insert into Profile values(2, 'Taras', 'Kochiv', 'PM', '5 years PM, 7 years c++');
insert into Profile values(3, 'Nazar', 'Pavliv', 'BI', '2 years SQL, 1 year java');
insert into Profile values(4, 'Oleg', 'Novak', 'QA', '2 years QA');