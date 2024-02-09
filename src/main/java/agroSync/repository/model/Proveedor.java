package agroSync.repository.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Proveedor {
    private int id;
    private String nombre;

    /**
     * Compara si el objeto dado es igual a este Proveedor.
     *
     * @param o el objeto a comparar
     * @return true si el objeto es igual a este Proveedor, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return id == proveedor.id;
    }

    /**
     * Calcula el valor hash para este Proveedor.
     *
     * @return el valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}