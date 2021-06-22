package com.bolsadeideas.springboot.vehicle.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vmt_county")
public class VMTCounty implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "county_vmt")
	private int countyVMT;
	
	@Column(name = "baseline_jan_vmt")
	private int baselineJanVMT;
	
	@Column(name = "percent_change_from_jan")
	private double percChangeJan;
	
	@Column(name = "mean7_county_vmt")
	private double mean7CountyVMT;
	
	@Column(name = "mean7_percent_change_from_jan")
	private double mean7PercChangeJan;	
	
	@Column(name = "date_at_low")
	private Date dateAtLow;
	
	@Column(name = "mean7_county_vmt_at_low")
	private double mean7CountyVMTLow;
	
	@Column(name = "percent_change_from_low")
	private double percChangeLow;
	
	@NotNull(message= "no puede estar vacio o nulo")
	@ManyToOne()
	@JoinColumn(name="id_county")
	private County county;

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCountyVMT() {
		return countyVMT;
	}

	public void setCountyVMT(int countyVMT) {
		this.countyVMT = countyVMT;
	}

	public int getBaselineJanVMT() {
		return baselineJanVMT;
	}

	public void setBaselineJanVMT(int baselineJanVMT) {
		this.baselineJanVMT = baselineJanVMT;
	}

	public double getPercChangeJan() {
		return percChangeJan;
	}

	public void setPercChangeJan(double percChangeJan) {
		this.percChangeJan = percChangeJan;
	}

	public double getMean7CountyVMT() {
		return mean7CountyVMT;
	}

	public void setMean7CountyVMT(double mean7CountyVMT) {
		this.mean7CountyVMT = mean7CountyVMT;
	}

	public double getMean7PercChangeJan() {
		return mean7PercChangeJan;
	}

	public void setMean7PercChangeJan(double mean7PercChangeJan) {
		this.mean7PercChangeJan = mean7PercChangeJan;
	}

	public Date getDateAtLow() {
		return dateAtLow;
	}

	public void setDateAtLow(Date dateAtLow) {
		this.dateAtLow = dateAtLow;
	}

	public double getMean7CountyVMTLow() {
		return mean7CountyVMTLow;
	}

	public void setMean7CountyVMTLow(double mean7CountyVMTLow) {
		this.mean7CountyVMTLow = mean7CountyVMTLow;
	}

	public double getPercChangeLow() {
		return percChangeLow;
	}

	public void setPercChangeLow(double percChangeLow) {
		this.percChangeLow = percChangeLow;
	}
	
	
	

}
