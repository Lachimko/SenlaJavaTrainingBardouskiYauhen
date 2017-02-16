drop database if exists senla;
create database senla;
USE senla;
create table product(
maker varchar(10) not null,
model varchar(50) not null,
type varchar(50) not null,
primary key (model)
);
create table pc (
code int not null auto_increment,
model varchar(50) not null,
speed smallint not null,
ram smallint not null,
hd real not null,
cd varchar(10) not null,
price float,
primary key(code),
foreign key(model) references product(model) on delete cascade on update cascade
);
create table laptop(
code int not null auto_increment,
model varchar(50) not null,
speed smallint not null,
ram smallint not null,
hd real not null,
price float,
screen tinyint not null,
primary key(code),
foreign key(model) references product(model) on delete cascade on update cascade
); 
create table printer(
code int not null auto_increment,
model varchar(50) not null,
color char(1) not null,
type varchar(10) not null,
price float,
primary key(code),
foreign key(model) references product(model) on delete cascade on update cascade
);
#printers-models
insert into product values('HP', 'hp000001', 'printer');
insert into printer values (null, 'hp000001', 'y', 'Laser', 240.99);

insert into senla.product values('Canon', 'canon000002', 'printer');
insert into senla.printer values (null, 'canon000002', 'y', 'Laser', 215);

insert into senla.product values('Canon', 'canon000003', 'printer');
insert into senla.printer values (null, 'canon000003', 'y', 'Laser', 80);

insert into senla.product values('HP', 'hp000004', 'printer');
insert into senla.printer values (null, 'hp000004', 'y', 'Laser', 240.99);

insert into senla.product values('HP', 'hp000005', 'printer');
insert into senla.printer values (null, 'hp000005', 'y', 'Jet', 215);

insert into senla.product values('Canon', 'canon000006', 'printer');
insert into senla.printer values (null, 'canon000006', 'y', 'Jet', 80);

#laptops-models
insert into senla.product values ('ASUS', 'laptopmodel000001', 'laptop');
insert into senla.laptop values (null, 'laptopmodel000001', 2900, 512, 1000, 720, 15);

insert into senla.product value ('ASUS', 'asus000001', 'Laptop');
insert into senla.laptop value (null, 'asus000001', 2500, 512, 1000, 710, 15);

insert into senla.product value ('ASER', 'aser000001', 'Laptop');
insert into senla.laptop value (null, 'aser000001', 2500, 1000, 2000, 1125, 18);

insert into senla.product value ('ASER', 'aser000002', 'Laptop');
insert into senla.laptop value (null, 'aser000002', 2900, 512, 1000, 550, 15);

insert into senla.product value ('ASER', 'aser000003', 'Laptop');
insert into senla.laptop value (null, 'aser000003', 2450, 1000, 2000, 850, 18);

insert into senla.product value ('Intel', 'intel000001', 'Laptop');
insert into senla.laptop value (null, 'intel000001', 2100, 512, 500, 450, 15);

insert into senla.product value ('AMD', 'amd000001', 'Laptop');
insert into senla.laptop value (null, 'amd000001', 1900, 512, 500, 420, 15);


#pc-models
insert into product values('AMD', 'pc000001', 'PC');
insert into pc values(null, 'pc000001', 2800, 2048, 500, 12, 171);

insert into product values('Intel', 'pc000002', 'PC');
insert into pc values(null, 'pc000002', 2800, 2048, 200, 12, 198);

insert into product values('AMD', 'pc000003', 'PC');
insert into pc values(null, 'pc000003', 2900, 4096, 1000, 24, 280);

insert into product values('AMD', 'pc000004', 'PC');
insert into pc values(null, 'pc000004', 2400, 2048, 500, 12, 140);

insert into product values('Toshiba', 'toshiba000001', 'PC');
insert into pc values(null, 'toshiba000001', 2100, 4096, 1000, 24, 310);

insert into product values('Toshiba', 'toshiba000002', 'PC');
insert into pc values(null, 'toshiba000002', 2100, 2048, 500, 12, 185);

insert into product values('Canon', 'canon000001', 'PC');
insert into pc values(null, 'canon000001', 3500, 512, 500, 12, 185);