package com.br.sicredi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	private OffsetDateTime cadastro;

}
