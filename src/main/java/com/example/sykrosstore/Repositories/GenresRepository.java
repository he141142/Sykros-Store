package com.example.sykrosstore.Repositories;

import com.example.sykrosstore.entities.Genres;
import com.example.sykrosstore.entities.Role;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository  extends CrudRepository<Genres, Long> {
}
