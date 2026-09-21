package vn.iotstar.example2.mapper;

import org.mapstruct.Mapper;
import vn.iotstar.example2.dto.UserHeaderView;
import vn.iotstar.example2.entity.AppUser;
@Mapper(componentModel = "spring") public interface UserMapper { UserHeaderView toHeaderView(AppUser user); }
