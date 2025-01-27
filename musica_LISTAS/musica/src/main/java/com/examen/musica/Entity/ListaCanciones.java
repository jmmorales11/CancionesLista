package com.examen.musica.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="lista-canciones")
public class ListaCanciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "canciones_id", unique = true)
    private Long cancionesid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCancionesid() {
        return cancionesid;
    }

    public void setCancionesid(Long cancionesid) {
        this.cancionesid = cancionesid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (!(obj instanceof ListaCanciones)){
            return false;
        }
        ListaCanciones o = (ListaCanciones) obj;
        return this.cancionesid !=null && this.cancionesid.equals(o.cancionesid);
    }
}
