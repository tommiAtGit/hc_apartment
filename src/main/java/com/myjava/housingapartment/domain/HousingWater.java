package com.myjava.housingapartment.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class HousingWater {
	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 16, columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID id;
	@OneToOne(targetEntity = HousingApartment.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "ApartmentUUID")
	private HousingApartment apartment;
	@Column(name = "CouldWater")
	private Double couldWater;
	@Column(name = "HotWater")
	private Double hotWater;
	@Column(name = "MeasureDate")
	private Date measurementDate;
}
