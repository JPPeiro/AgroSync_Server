package agroSync.repository.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PedidoIngrediente {
    private int id;
    private int ingredienteId;
    private int proveedorId;
    private float cantidad;
    private float coste;

    /**
     * Compara si el objeto dado es igual a este pedidoIngrediente.
     *
     * @param o el objeto a comparar
     * @return true si el objeto es igual a este pedidoIngrediente, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoIngrediente pedidoIngrediente = (PedidoIngrediente) o;
        return id == pedidoIngrediente.id;
    }

    /**
     * Calcula el valor hash para este pedidoIngrediente.
     *
     * @return el valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}