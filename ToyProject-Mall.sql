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
	)
CREATE TABLE tb_product(
	proudct_idx INT PRIMARY KEY auto_increment
	,product_name VARCHAR(30) NOT null
	,product_price INT NOT null
	,product_catagory INT NOT null
	,product_img_path VARCHAR(100)
	,product_begin_date date
)
CREATE TABLE tb_product_category(
    category_idx INT PRIMARY KEY auto_increment
    ,category_main VARCHAR(20)	#1차카테고리
    ,category_sub VARCHAR(20)		#2차카테고리
)
CREATE TABLE tb_basket(
)