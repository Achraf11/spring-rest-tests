package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Transaction implements Serializable {

	private static final long serialVersionUID = 706690724306325415L;

	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private String accountId;

	@Getter
	@Setter
	private String number;

	@Getter
	@Setter
	private BigDecimal balance;
}
