package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;


import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Account entity
 * 
 * @author A525125
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = -3548441891975414771L;

	@Id
	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private String number;

	@Getter
	@Setter
	private String type;

	@Getter
	@Setter
	private BigDecimal balance;

	@Getter
	@Setter
	private Date creationDate;

	@Getter
	@Setter
	private boolean isActive;

}
