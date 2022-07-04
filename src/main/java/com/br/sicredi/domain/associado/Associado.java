package com.br.sicredi.domain.associado;

import com.br.sicredi.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Associado extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String nome;


}