package agroSync.repository.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ComposicionPienso {
    private int idPienso;
    private int idIngrediente;
    private float cantidad;
}