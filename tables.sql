create table address
(
  address_id int auto_increment
    primary key,
  province varchar(10) null,
  city varchar(30) null,
  country varchar(30) null,
  detail_address varchar(200) null,
  zip_code char(6) null
)ENGINE = INNODB CHARACTER SET utf8
;

create table cate
(
  cate_id int auto_increment
    primary key,
  cate_name varchar(20) null
)ENGINE = INNODB CHARACTER SET utf8
;

create table comment_table
(
  comment_table_id int auto_increment
    primary key,
  goods_id int null,
  username varchar(20) null,
  comment varchar(1000) null,
  rate FLOAT null,
  comment_date datetime null
)ENGINE = INNODB CHARACTER SET utf8
;

create index goods_id
  on comment_table (goods_id)
;

create index user_id
  on comment_table (username)
;

create table goods
(
  goods_id int auto_increment
    primary key,
  goods_name varchar(100) null,
  store_id int null,
  price float null,
  info varchar(1000) null,
  views_time int default '0' null,
  date date null,
  sold_number int default '0' null,
  count int null,
  picture varchar(100) null,
  promotion_id int null
)ENGINE = INNODB CHARACTER SET utf8
;

create index promotion_id
  on goods (promotion_id)
;

create table goods_cate
(
  goods_cate_id int auto_increment
    primary key,
  goods_id int null,
  cate_id int null
)ENGINE = INNODB CHARACTER SET utf8
;

create index cate_id
  on goods_cate (cate_id)
;

create index goods_id
  on goods_cate (goods_id)
;

create table order_form
(
  order_form_id int auto_increment
    primary key,
  goods_id int null,
  user_id int null,
  solder_id int null,
  unit_price float null,
  buy_number int null,
  address_id int null,
  phone varchar(11) null,
  date date null,
  pay_state bit default b'0' null,
  place_order bit default b'0' null,
  shipping_state int default 0 null
)ENGINE = INNODB CHARACTER SET utf8
;

create index address_id
  on order_form (address_id)
;

create index goods_id
  on order_form (goods_id)
;

create index user_id
  on order_form (user_id)
;

create index solder_id
  on order_form (solder_id)
;

create table promotion
(
  promotion_id int auto_increment
    primary key,
  name varchar(20) null,
  type tinyint null,
  discount float null,
  full float null,
  cut float null,
  buy int null,
  give int null,
  from_time datetime null,
  to_time datetime null,
  store_id int null,
  is_all_site bit default b'0' null
)ENGINE = INNODB CHARACTER SET utf8
;

create table role
(
  role_id int auto_increment
    primary key,
  name varchar(10) null
)ENGINE = INNODB CHARACTER SET utf8
;

create table store
(
  store_id int auto_increment
    primary key,
  user_id int null,
  store_name varchar(100) null,
  store_info varchar(1000) null,
  create_date datetime null,
  store_status int null,
  image varchar(200) null
)ENGINE = INNODB CHARACTER SET utf8
;

create table user
(
  user_id int auto_increment
    primary key,
  username varchar(20) null,
  nick_name varchar(20) null,
  password varchar(150) null,
  email varchar(30) null,
  phone varchar(11) null,
  name varchar(20) null,
  id_card varchar(60) null,
  role_id int null,
  sex bit null,
  is_authentication bit default b'0' null,
  create_date datetime null,
  has_store bit default b'0' null,
  image varchar(200) null,
  constraint login_name
  unique (username)
)ENGINE = INNODB CHARACTER SET utf8
;

create index role_id
  on user (role_id)
;

create table user_address
(
  user_address_id int auto_increment
    primary key,
  user_id int null,
  address_id int null
)ENGINE = INNODB CHARACTER SET utf8
;

create index address_id
  on user_address (address_id)
;

create index user_id
  on user_address (user_id)
;

INSERT INTO role (name) VALUE ("ROLE_SUPER");
INSERT INTO role (name) VALUE ("ROLE_ADMIN");
INSERT INTO role (name) VALUE ("ROLE_BUYER");
INSERT INTO role (name) VALUE ("ROLE_SELL");
INSERT INTO role (name) VALUE ("ROLE_USER");

INSERT INTO user(username, password, role_id) VALUE ("admin", "$2a$10$i6P0z/zwpipBHmW2DHNZqejuwyh4zbtYfvy8YRJdIBJQEnRMNs0RK", 1);

