-- booking.customer_entity definition

-- Drop table

-- DROP TABLE booking.customer_entity;

CREATE TABLE booking.customer_entity (
	entity_type varchar(50) NULL,
	entity_code varchar(255) NOT NULL,
	entity_name varchar(100) NULL,
	created_by varchar(50) NOT NULL,
	created_at timestamptz NOT NULL,
	updated_by varchar(50) NULL,
	updated_at timestamptz NULL,
	CONSTRAINT customer_entity_pkey PRIMARY KEY (entity_code)
);


-- booking.customer_info definition

-- Drop table

-- DROP TABLE booking.customer_info;

CREATE TABLE booking.customer_info (
	customer_id bigserial NOT NULL,
	customer_number int8 NULL,
	is_active_ind bool NULL,
	first_name varchar(200) NULL,
	middle_name varchar(200) NULL,
	last_name varchar(200) NULL,
	preferred_name varchar(200) NULL,
	created_by varchar(50) NOT NULL,
	created_at timestamptz NOT NULL,
	updated_by varchar(50) NULL,
	updated_at timestamptz NULL,
	CONSTRAINT customer_info_pkey PRIMARY KEY (customer_id)
);


-- booking.status definition

-- Drop table

-- DROP TABLE booking.status;

CREATE TABLE booking.status (
	status_code varchar(150) NOT NULL,
	status varchar(255) NULL,
	status_desc varchar(255) NULL,
	CONSTRAINT status_pkey PRIMARY KEY (status_code)
);


-- booking.address definition

-- Drop table

-- DROP TABLE booking.address;

CREATE TABLE booking.address (
	address_id bigserial NOT NULL,
	address_line1 varchar(200) NULL,
	address_line2 varchar(200) NULL,
	address_line3 varchar(200) NULL,
	state_code int8 NULL,
	city_code int8 NULL,
	country_code int8 NULL,
	postal_code int8 NULL,
	fk_customer_id int8 NULL,
	is_primary_address bool NULL,
	created_by varchar(50) NOT NULL,
	created_at timestamptz NOT NULL,
	updated_by varchar(50) NULL,
	updated_at timestamptz NULL,
	CONSTRAINT address_pkey PRIMARY KEY (address_id),
	CONSTRAINT fk_customer_id FOREIGN KEY (fk_customer_id) REFERENCES booking.customer_info(customer_id)
);



-- booking.email definition

-- Drop table

-- DROP TABLE booking.email;

CREATE TABLE booking.email (
	email_id bigserial NOT NULL,
	email_address varchar(200) NULL,
	email_provider varchar(200) NULL,
	is_primary_email bool NULL,
	fk_customer_id int8 NULL,
	created_by varchar(50) NOT NULL,
	created_at timestamptz NOT NULL,
	updated_by varchar(50) NULL,
	updated_at timestamptz NULL,
	CONSTRAINT email_pkey PRIMARY KEY (email_id),
	CONSTRAINT fkcustomerid FOREIGN KEY (fk_customer_id) REFERENCES booking.customer_info(customer_id)
);


-- booking.phone definition

-- Drop table

-- DROP TABLE booking.phone;

CREATE TABLE booking.phone (
	phone_id bigserial NOT NULL,
	phone_number int8 NULL,
	phone_country_code int8 NULL,
	is_primary_phone bool NULL,
	fk_customer_id int8 NULL,
	created_by varchar(50) NOT NULL,
	created_at timestamptz NOT NULL,
	updated_by varchar(50) NULL,
	updated_at timestamptz NULL,
	CONSTRAINT phone_pkey PRIMARY KEY (phone_id),
	CONSTRAINT fk_customerid FOREIGN KEY (fk_customer_id) REFERENCES booking.customer_info(customer_id)
);


-- booking.questionnaire definition

-- Drop table

-- DROP TABLE booking.questionnaire;

CREATE TABLE booking.questionnaire (
	question_id bigserial NOT NULL,
	question_text varchar(255) NULL,
	question_desc varchar(255) NULL,
	possible_answers varchar(255) NULL,
	question_type varchar(255) NULL,
	question_datatype varchar(50) NULL,
	additional_spec jsonb NULL,
	is_main_mandatory bool NULL,
	question_code varchar(255) NOT NULL,
	parent_question_code varchar(255) NULL,
	fk_entity_code varchar(255) NOT NULL,
	question_version int4 NOT NULL,
	question_category varchar(50) NULL,
	is_active_ind bool NULL,
	column_order_id int4 NOT NULL,
	created_by varchar(50) NOT NULL,
	created_at timestamptz NOT NULL,
	updated_by varchar(50) NULL,
	updated_at timestamptz NULL,
	CONSTRAINT questionnaire_pkey PRIMARY KEY (question_id),
	CONSTRAINT fk_entity_code FOREIGN KEY (fk_entity_code) REFERENCES booking.customer_entity(entity_code)
);


-- booking.booking definition

-- Drop table

-- DROP TABLE booking.booking;

CREATE TABLE booking.booking (
	booking_id bigserial NOT NULL,
	requested_by varchar(50) NOT NULL,
	requestor_type varchar(50) NOT NULL,
	approved_by varchar(50) NULL,
	approved_at timestamptz NULL,
	created_by varchar(50) NOT NULL,
	created_at timestamptz NOT NULL,
	updated_by varchar(50) NULL,
	updated_at timestamptz NULL,
	fk_entity_code varchar not NULL,
	fk_status_code varchar not NULL,
	CONSTRAINT booking_pkey PRIMARY KEY (booking_id),
	CONSTRAINT fk_statuscode FOREIGN KEY (fk_status_code) REFERENCES booking.status(status_code)
);


-- booking.answers definition

-- Drop table

-- DROP TABLE booking.answers;

CREATE TABLE booking.answers (
	answer_id bigserial NOT NULL,
	question_code varchar(150) NULL,
	answer varchar(255) NULL,
	fk_booking_id int8 NOT NULL,
	CONSTRAINT answers_pkey PRIMARY KEY (answer_id),
	CONSTRAINT fk_booking_id FOREIGN KEY (fk_booking_id) REFERENCES booking.booking(booking_id)
);



--Questionnaire table insert values
insert into booking.questionnaire(Question_text,Question_datatype,Question_code,
fk_Entity_code,Question_version,Question_category,is_active_ind,
column_order_id,created_by,created_at) values
('Customer Name','String','QC-1000',1,20230911,'BOOKING',true,1,'Deepak',current_timestamp),
('Phone Number','String','QC-1001',1,20230911,'BOOKING',true,2,'Deepak',current_timestamp),
('Email','String','QC-1002',1,20230911,'BOOKING',false,3,'Deepak',current_timestamp),
('Vehicle Type','String','QC-1003',1,20230911,'BOOKING',true,4,'Deepak',current_timestamp),
('Vehicle Number','String','QC-1004',1,20230911,'BOOKING',true,5,'Deepak',current_timestamp),
('Address','Long','QC-1005',1,20230911,'BOOKING',false,11,'Deepak',current_timestamp),
('Booking Id','String','QC-1006',1,20230911,'BOOKING',true,6,'Deepak',current_timestamp),
('Package','Double','QC-1007',1,20230911,'BOOKING',false,7,'Deepak',current_timestamp),
('Total Amount','Double','QC-1008',1,20230911,'BOOKING',true,8,'Deepak',current_timestamp),
('Advance Paid','Double','QC-1009',1,20230911,'BOOKING',true,9,'Deepak',current_timestamp),
('Balance Amount','Double','QC-1010',1,20230911,'BOOKING',true,10,'Deepak',current_timestamp),
('Status','String','QC-1011',1,20230911,'BOOKING',true,12,'Deepak',current_timestamp);

--Entity table insert values
insert into booking.Customer_Entity(Entity_code,created_by,created_at) values
(1,'Deepak','2023-08-11 11:43:10.212 +0530');

--Answers table insert values
insert into booking.answers(Question_code,answer,fk_booking_id) values
('QC-1000','Gokul Krishnan',11),
('QC-1001','(+91)83-006-77386',11),
('QC-1003','Bus',11),
('QC-1004','TN33AZ1234',11),
('QC-1007','50000.00',11),
('QC-1009','5000.00',11),
('QC-1010','45000.00',11),
('QC-1011','In Progress',11),
('QC-1000','Deepak Pranav',12),
('QC-1001','(+91)99-625-67096',12),
('QC-1003','Bus',12),
('QC-1004','TN33AZ3456',12),
('QC-1007','150000.00',12),
('QC-1009','25000.00',12),
('QC-1010','125000.00',12),
('QC-1011','Pending',12),
('QC-1000','Sindhu',13),
('QC-1001','(+91)99-625-67096',13),
('QC-1003','Bus',13),
('QC-1004','TN42QA4242',13),
('QC-1007','125000.00',13),
('QC-1009','0',13),
('QC-1010','0',13),
('QC-1011','Draft',13);

insert into booking.status (status_code,status,status_desc) values('CMP','Completed','status is moved to complete');

--Booking table insert values
insert into booking.booking(requested_by,requestor_type,created_by,created_at,fk_entity_code,fk_status_code) values
('Gokul','agent','Gokul',current_timestamp,1,'CMP'),
('Deepak','agent','Deepak',current_timestamp,1,'CMP'),
('Sindhu','agent','Sindhu',current_timestamp,1,'CMP'),
('Dhanush','agent','Dhanush',current_timestamp,1,'CMP'),
('Dharanidharan','agent','Dharanidharan',current_timestamp,1,'CMP');

