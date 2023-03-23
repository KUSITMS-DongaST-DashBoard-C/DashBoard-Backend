package com.example.dashboardback.contents.life.dto.Res;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetFilteredLifeRes {
    private Long totalViewNum;
    private List<GetFilteredLife> getFilteredLifeResList;
}
