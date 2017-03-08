package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transaction entity
 * 
 * @author A525125
 *
 */
@Data
@Entity(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Transaction implements Serializable {

	private static final long serialVersionUID = 706690724306325415L;

	@Id
	@Getter
	@Setter
	private String id;

	@ManyToOne
	@JoinColumn(name = "accountID")
	@Getter
	@Setter
	private Account account;

	@Getter
	@Setter
	private String number;

	@Getter
	@Setter
	private BigDecimal balance;

}
