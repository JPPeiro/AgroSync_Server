package agroSync.repository.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Ingrediente {
    private int id;
    private String nombre;
    private String cantidad;

    /**
     * Compara si el objeto dado es igual a este Ingrediente.
     *
     * @param o el objeto a comparar
     * @return true si el objeto es igual a este Ingrediente, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente ingrediente = (Ingrediente) o;
        return id == ingrediente.id;
    }

    /**
     * Calcula el valor hash para este Ingrediente.
     *
     * @return el valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}