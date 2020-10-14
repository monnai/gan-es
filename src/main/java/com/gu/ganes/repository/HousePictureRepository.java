package com.gu.ganes.repository;

import com.gu.ganes.entity.HousePicture;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 瓦力.
 */
public interface HousePictureRepository extends CrudRepository<HousePicture, Long> {
    List<HousePicture> findAllByHouseId(Long id);
}
