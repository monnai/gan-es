package com.gu.ganes.repository;

import com.gu.ganes.entity.Subway;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 瓦力.
 */
public interface SubwayRepository extends CrudRepository<Subway, Long> {
    List<Subway> findAllByCityEnName(String cityEnName);
}
