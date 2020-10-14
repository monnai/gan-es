package com.gu.ganes.repository;

import com.gu.ganes.entity.SubwayStation;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 瓦力.
 */
public interface SubwayStationRepository extends CrudRepository<SubwayStation, Long> {
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
