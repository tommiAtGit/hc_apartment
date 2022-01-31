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
	PRIMARY KEY (id),
	INDEX apartment_ind(ApartmentUUID),
            FOREIGN KEY (ApartmentUUID) REFERENCES housing_apartment(ApartmentUUID)
              ON DELETE NO ACTION
              ON UPDATE NO ACTION
)ENGINE=INNODB;

CREATE TABLE apartment_water
(
	id 				BINARY(16) NOT NULL,
	ApartmentUUID 	BINARY(16),
	CouldWater		DOUBLE NOT NULL,
	HotWater		DOUBLE NOT NULL,
	MeasureDate 	DATE,
	PRIMARY KEY (id),
	INDEX apartment_id(ApartmentUUID),
            FOREIGN KEY (ApartmentUUID) REFERENCES housing_apartment(ApartmentUUID)
              ON DELETE NO ACTION
              ON UPDATE NO ACTION
)ENGINE=INNODB;

DESCRIBE housing_apartment;
DESCRIBE apartment_electricity;
DESCRIBE apartment_water;

SELECT * FROM housing_apartment;
SELECT * FROM apartment_electricity;
SELECT * FROM apartment_water;


