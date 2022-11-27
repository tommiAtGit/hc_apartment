package com.myjava.housingapartment.domain;

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
@Table(name="housing_apartment")
public class HousingApartment {
	
	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="ApartmentUUID",length = 16, columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID apartmentUUID;
	@Column(name="CooperativeUUID")
	private UUID cooperativeUUID;
	@Column(name="UserUUID")
	private UUID userUUID;
	@Column(name="Apartment")
	private String apartment;
	
}
