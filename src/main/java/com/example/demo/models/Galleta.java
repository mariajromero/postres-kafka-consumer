package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="galleta")
public class Galleta implements IDescuento{
    @Id
    @jakarta.persistence.Id
    private Integer id;
    private String sabor;
    private String tamaño;
    private boolean tieneGluten;
    private Float precio;

    @OneToOne(mappedBy = "galleta")
    private Pedido pedido;
    @Override
    public Float aplicarDescuento(Float precio) {

        return this.precio-(precio*0.15F);
    }

}
