package agroSync.repository.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class IngredienteProveedor {
    private int idProveedor;
    private int idIngrediente;
    private float precio;
}