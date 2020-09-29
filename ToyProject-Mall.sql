CREATE TABLE tb_user(
	user_idx INT PRIMARY KEY auto_increment
	, user_id VARCHAR(25) NOT null
	, user_password VARCHAR(100) NOT null
	, user_name VARCHAR(10) NOT null
	, user_rank VARCHAR(10) NOT null
	, user_nickname VARCHAR(10) NOT null
	, user_email VARCHAR(30)
	, user_post_number VARCHAR(6) NOT null
	, user_address VARCHAR(30) NOT null
	, user_address_detail VARCHAR(30)
	, user_phone_number VARCHAR(12) NOT null
	, user_begin_date date
	, user_grant VARCHAR(10) NOT null
	);
CREATE TABLE tb_product(
	product_idx INT PRIMARY KEY auto_increment
	,product_name VARCHAR(30) NOT null
	,product_price INT NOT null
	,product_category INT NOT NULL 
	,product_img_path VARCHAR(200)
	,product_img_name VARCHAR(100)
	,product_begin_date date
    ,FOREIGN KEY(product_category) REFERENCES tb_product_category(category)
);
CREATE TABLE tb_product_category(
    category_name VARCHAR(20)  NOT null
    ,category int	PRIMARY KEY NOT null#1차카테고리
    ,category_ref INT#2차카테고리
);
Insert Into tb_product_category(category_name,category,category_ref) values ("test1",100,null);
Insert Into tb_product_category(category_name,category,category_ref) values ("test1-1",101,100);
Insert Into tb_product_category(category_name,category,category_ref) values ("test1-2",102,100);
Insert Into tb_product_category(category_name,category,category_ref) values ("test2",200,null);
Insert Into tb_product_category(category_name,category,category_ref) values ("test2-1",201,200);
Insert Into tb_product_category(category_name,category,category_ref) values ("test2-2",202,200);

CREATE TABLE tb_cart(
	cart_idx int not null primary key auto_increment
    ,cart_user_idx varchar(20) not null
    ,cart_product_idx varchar(20) not null
    ,cart_product_amount int not null
    ,FOREIGN KEY(cart_user_id) REFERENCES tb_user(user_idx)
    ,FOREIGN KEY(cart_product_id) REFERENCES tb_product(product_idx)
);
