package com.poli.vet.repository;

import com.poli.vet.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMascotaRepository extends JpaRepository<Mascota, Integer> {
}
