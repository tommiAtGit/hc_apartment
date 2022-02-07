USE hc_apartmentdb;
DROP TABLE IF EXISTS housing_apartment;
DROP TABLE IF EXISTS apartment_electricity;
DROP TABLE IF EXISTS apartment_water;



CREATE TABLE housing_apartment
(
	ApartmentUUID 		BINARY(16) NOT NULL,
	CooperativeUUID		BINARY(16) NOT NULL,
	UserUUID 			BINARY(16) NOT NULL,
	Apartment 			char(10),
	PRIMARY KEY (ApartmentUUID)

)ENGINE=INNODB;

CREATE TABLE apartment_electricity
(
	id 					BINARY(16) NOT NULL,
	ApartmentUUID	 	BINARY(16),
	Measurement			DOUBLE NOT NULL,
	MeasureDate 		DATE,
	apartment_uuid		BINARY(16),
	 INDEX user_ind(apartment_uuid),
	        FOREIGN KEY (apartment_uuid) REFERENCES housing_apartment(ApartmentUUID)
	          ON DELETE CASCADE
	          ON UPDATE NO ACTION
	PRIMARY KEY (id)
)ENGINE=INNODB;

CREATE TABLE apartment_water
(
	id 				BINARY(16) NOT NULL,
	ApartmentUUID 	BINARY(16),
	CouldWater		DOUBLE NOT NULL,
	HotWater		DOUBLE NOT NULL,
	MeasureDate 	DATE,
	apartment_uuid		BINARY(16),
		 INDEX user_ind(apartment_uuid),
	        FOREIGN KEY (apartment_uuid) REFERENCES housing_apartment(ApartmentUUID)
	          ON DELETE CASCADE
	          ON UPDATE NO ACTION
	PRIMARY KEY (id)
)ENGINE=INNODB;

DESCRIBE housing_apartment;
DESCRIBE apartment_electricity;
DESCRIBE apartment_water;

SELECT * FROM housing_apartment;
SELECT * FROM apartment_electricity;
SELECT * FROM apartment_water;


