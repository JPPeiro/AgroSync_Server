package agroSync.repository.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Usuario {
    private int id;
    private String nombre;
    private String contraseña;
    private String permisos;

    /**
     * Compara si el objeto dado es igual a este Usuario.
     *
     * @param o el objeto a comparar
     * @return true si el objeto es igual a este Usuario, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    /**
     * Calcula el valor hash para este Usuario.
     *
     * @return el valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}