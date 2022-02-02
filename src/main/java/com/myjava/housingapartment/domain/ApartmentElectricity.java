package com.myjava.housingapartment.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="apartment_electricity")
public class ApartmentElectricity {
	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 16, columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID id;
	//@ManyToOne(targetEntity = HousingApartment.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//@JoinColumn(nullable = false, name = "ApartmentUUID")
	//private HousingApartment apartment;
	@Column(name = "Measurement")
	private Double measurement;
	@Column(name = "MeasureDate")
	private Timestamp measurementDate;
	
	
}
