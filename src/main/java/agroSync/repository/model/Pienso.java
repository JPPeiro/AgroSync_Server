package agroSync.repository.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Pienso {
    private int id;
    private String nombre;
    private String composicion;

    /**
     * Compara si el objeto dado es igual a este Pienso.
     *
     * @param o el objeto a comparar
     * @return true si el objeto es igual a este Pienso, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pienso pienso = (Pienso) o;
        return id == pienso.id;
    }

    /**
     * Calcula el valor hash para este Pienso.
     *
     * @return el valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}